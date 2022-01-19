package fr.miage.m1.sntp.camel;

import fr.miage.m1.sntp.configuration.InfluxDBConfig;
import fr.miage.m1.sntp.measurement.TrainPositionData;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.influxdb.InfluxDbConstants;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class TrainCamel extends RouteBuilder {
    public static final String RETENTION_POLICY = "autogen";
    private static final Logger logger = LoggerFactory.getLogger(TrainCamel.class);

    @Override
    public void configure() throws Exception {
        InfluxDBConfig influxConfig = new InfluxDBConfig();
        from("jms:queue/train/position").unmarshal().jacksonxml(TrainPositionData.class).process(exchange -> {
            TrainPositionData trainData = exchange.getIn().getBody(TrainPositionData.class);
            long millisecondTime = Instant.now().toEpochMilli();
            Point point = Point.measurementByPOJO(TrainPositionData.class)
                    .time(millisecondTime, TimeUnit.MILLISECONDS)
                    .addFieldsFromPOJO(trainData)
                    .build();
            logger.info(point.toString());
            exchange.getIn().setBody(point);
            exchange.getIn().setHeader(InfluxDbConstants.DBNAME_HEADER, influxConfig.getDatabaseName());
            exchange.getIn().setHeader(InfluxDbConstants.RETENTION_POLICY_HEADER, RETENTION_POLICY);
        }).to("influxdb://influxDbBean");
    }
}
