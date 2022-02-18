package fr.miage.m1.sntp.camel;

import fr.miage.m1.sntp.measurement.TrainPositionData;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.influxdb.InfluxDbConstants;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.influxdb.dto.Point;

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
    public static final String JMS_TRAIN_INFOCENTRE = "jms:%s/train/infocentre/%s";
    public static final String QUEUE_TRAIN_POSITION = "jms:%s/queue/train/position";
    public static final String URL_INFLUX_DB = "influxdb://influxDbBean?databaseName=%s";
    public static final String INFLUX_DATABASE = "influx.database";
    public static final String JMS_PREFIX = "fr.miage.m1.sntp.jmsPrefix";
    @Inject
    ConnectionFactory connectionFactory;

    @ConfigProperty(name = INFLUX_DATABASE, defaultValue = "sntp")
    String influxDatabase;

    @ConfigProperty(name = JMS_PREFIX, defaultValue = "SNTP")
    String prefix;

    @Override
    public void configure() {
        from(String.format(QUEUE_TRAIN_POSITION, prefix)).unmarshal().jacksonxml(TrainPositionData.class).process(exchange -> {
            TrainPositionData trainData = exchange.getIn().getBody(TrainPositionData.class);
            long millisecondTime = Instant.now().toEpochMilli();

            Point point = Point.measurementByPOJO(TrainPositionData.class)
                    .time(millisecondTime, TimeUnit.MILLISECONDS)
                    .addFieldsFromPOJO(trainData)
                    .build();
            exchange.getIn().setBody(point);
            exchange.getIn().setHeader(InfluxDbConstants.DBNAME_HEADER, influxDatabase);
            exchange.getIn().setHeader(InfluxDbConstants.RETENTION_POLICY_HEADER, RETENTION_POLICY);
        }).to(String.format(URL_INFLUX_DB, influxDatabase));
    }

    public void envoyerMessageAuTrain(Integer numeroDeTrain, String message) {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            context.createProducer().send(context.createQueue(String.format(JMS_TRAIN_INFOCENTRE, prefix, numeroDeTrain)), message);
        }
    }
}
