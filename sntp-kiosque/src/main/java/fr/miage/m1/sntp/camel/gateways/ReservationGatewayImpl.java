package fr.miage.m1.sntp.camel.gateways;

import fr.miage.m1.sntp.dto.ReservationDTO;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

/**
 * @author Quentin Vaillant
 */
@ApplicationScoped
public class ReservationGatewayImpl implements ReservationGateway {
    public static final String ERREUR_PENDANT_ENVOIE = "Erreur pendant l'envoie du message sur direct:cli";
    private static final Logger logger = LoggerFactory.getLogger(ReservationGatewayImpl.class);
    @Inject
    CamelContext context;

    @ConfigProperty(name = "fr.miage.m1.sntp.kiosqueId")
    Integer kiosqueId;

    @Override
    public void sendReservation(ReservationDTO reservation) {
        try (ProducerTemplate producer = context.createProducerTemplate()) {
            producer.sendBody("direct:cli", reservation);
        } catch (IOException e) {
            logger.error(ERREUR_PENDANT_ENVOIE, e);
        }
    }
}
