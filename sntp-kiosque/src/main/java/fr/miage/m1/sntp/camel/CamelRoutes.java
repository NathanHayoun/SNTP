package fr.miage.m1.sntp.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringWriter;

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

    private static String toXML(Object obj) {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            StringWriter writer = new StringWriter();
            context.createMarshaller().marshal(obj, writer);
            return writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void configure() {
        camelContext.setTracing(true);

        from("direct:cli").marshal().jacksonxml().to("jms:" + jmsPrefix + "booking?exchangePattern=InOut");
    }
}
