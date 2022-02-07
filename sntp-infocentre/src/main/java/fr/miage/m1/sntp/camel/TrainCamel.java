package fr.miage.m1.sntp.camel;

import fr.miage.m1.sntp.measurement.TrainPositionData;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.influxdb.InfluxDbConstants;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Session;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class TrainCamel extends RouteBuilder {
    public static final String RETENTION_POLICY = "autogen";
    public static final String JMS_TRAIN_INFOCENTRE = "jms:train/infocentre/";

    private static final Logger logger = LoggerFactory.getLogger(TrainCamel.class);

    @Inject
    ConnectionFactory connectionFactory;

    @ConfigProperty(name = "influx.database", defaultValue = "sntp")
    String influxDatabase;

    @Override
    public void configure() throws Exception {
        from("jms:queue/train/position").unmarshal().jacksonxml(TrainPositionData.class).process(exchange -> {
            TrainPositionData trainData = exchange.getIn().getBody(TrainPositionData.class);
            long millisecondTime = Instant.now().toEpochMilli();
            Point point = Point.measurementByPOJO(TrainPositionData.class)
                    .time(millisecondTime, TimeUnit.MILLISECONDS)
                    .addFieldsFromPOJO(trainData)
                    .build();
            logger.info(point.toString());
            exchange.getIn().setBody(point);
            exchange.getIn().setHeader(InfluxDbConstants.DBNAME_HEADER, influxDatabase);
            exchange.getIn().setHeader(InfluxDbConstants.RETENTION_POLICY_HEADER, RETENTION_POLICY);
        }).to("influxdb://influxDbBean?databaseName=" + influxDatabase);
    }

    public void envoyerMessageAuTrain(Integer numeroDeTrain, String message) {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            context.createProducer().send(context.createQueue(JMS_TRAIN_INFOCENTRE + numeroDeTrain), message);
        }
    }
}
