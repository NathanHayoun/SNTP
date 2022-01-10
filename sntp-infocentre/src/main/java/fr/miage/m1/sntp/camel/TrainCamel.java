package fr.miage.m1.sntp.camel;

import fr.miage.m1.sntp.measurement.TrainPositionData;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.influxdb.InfluxDbConstants;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class TrainCamel extends RouteBuilder {
    private static final Logger logger = LoggerFactory.getLogger(TrainCamel.class);
    //    @ConfigProperty(name = "influx.db")
    String influxDatabase = "sntp";

    @Override
    public void configure() throws Exception {
        logger.error(influxDatabase);
        from("jms:queue/train/position").unmarshal().jacksonxml(TrainPositionData.class).process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                TrainPositionData trainData = exchange.getIn().getBody(TrainPositionData.class);
                long millisecondTime = Instant.now().toEpochMilli();
                Point point = Point.measurementByPOJO(TrainPositionData.class)
                        .time(millisecondTime, TimeUnit.MILLISECONDS)
                        .addFieldsFromPOJO(trainData)
                        .build();
                logger.info(point.toString());
                exchange.getIn().setBody(point);
                exchange.getIn().setHeader(InfluxDbConstants.DBNAME_HEADER, influxDatabase);
                exchange.getIn().setHeader(InfluxDbConstants.RETENTION_POLICY_HEADER, "autogen");
            }
        }).to("influxdb://influxDbBean");
    }
}
