package fr.miage.m1.sntp.camel;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrainCamel extends RouteBuilder {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void configure() throws Exception {
        from("jms:train/infocentre/" + 2569).process(exchange -> logger.info(exchange.getMessage().toString()));
    }
}
