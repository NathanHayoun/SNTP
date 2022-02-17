package fr.miage.m1.sntp.camel;


import fr.miage.m1.sntp.dto.ReservationDTO;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {


    @ConfigProperty(name = "fr.miage.m1.sntp.jmsPrefix")
    String jmsPrefix;

    @Inject
    ReservationGateway reservationHandler;

    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        from("jms:" + jmsPrefix + "booking?exchangePattern=InOut")
                .log("reservation received: ${in.headers}")
                .unmarshal().json(ReservationDTO.class)
                .bean(reservationHandler, "book").marshal().json();
    }
}
