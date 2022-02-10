package fr.miage.m1.sntp.camel;

import fr.miage.m1.sntp.cli.UserInterface;
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
    CamelContext camelContext;


    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);


        from("direct:cli")
                .marshal().json()
                .to("jms:" + jmsPrefix + "booking?exchangePattern=InOut");
    }
}

