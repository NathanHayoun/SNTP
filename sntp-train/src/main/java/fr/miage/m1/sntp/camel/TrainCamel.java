package fr.miage.m1.sntp.camel;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Etan Guir
 */
@ApplicationScoped
public class TrainCamel extends RouteBuilder {
    private static final String QUEUE_FROM_INFOCENTRE = "jms:%s/train/infocentre/%s";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ConfigProperty(name = "fr.miage.m1.sntp.jmsPrefix", defaultValue = "SNTP")
    String prefix;

    @ConfigProperty(name = "fr.miage.m1.sntp.numero.train", defaultValue = "2587")
    int numeroDeTrain;

    @Override
    public void configure() {
        from(String.format(QUEUE_FROM_INFOCENTRE, prefix, numeroDeTrain)).process(exchange -> afficherMessage(exchange.getMessage().toString()));
    }

    private void afficherMessage(String message) {
        logger.info("Afficher un message");
        logger.info(message);
    }
}