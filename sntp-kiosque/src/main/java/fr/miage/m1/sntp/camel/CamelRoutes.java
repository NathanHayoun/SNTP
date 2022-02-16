package fr.miage.m1.sntp.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author Quentin Vaillant
 */
@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    public static final String CONFIG_KEY_JMS_PREFIX = "fr.miage.m1.sntp.jmsPrefix";
    @ConfigProperty(name = CONFIG_KEY_JMS_PREFIX)
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
