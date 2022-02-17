package fr.miage.m1.sntp.camel;


import fr.miage.m1.sntp.dto.ReservationDTO;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {


    public static final String JMS_PREFIX_CONFIG_KEY = "fr.miage.m1.sntp.jmsPrefix";
    public static final String RESERVATION_RECEIVED = "reservation received: ${in.headers}";
    public static final String METHOD = "book";
    @ConfigProperty(name = JMS_PREFIX_CONFIG_KEY)
    String jmsPrefix;

    @Inject
    ReservationGateway reservationHandler;

    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        from("jms:" + jmsPrefix + "booking?exchangePattern=InOut")
                .log(RESERVATION_RECEIVED)
                .unmarshal().json(ReservationDTO.class)
                .bean(reservationHandler, METHOD).marshal().json();
    }
}
