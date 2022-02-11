package fr.miage.m1.sntp.camel.gateways;

import fr.miage.m1.sntp.dto.ReservationDTO;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class ReservationGatewayImpl implements ReservationGateway {

    @Inject
    CamelContext context;

    @ConfigProperty(name = "fr.miage.m1.sntp.kiosqueId")
    Integer kiosqueId;

    @Override
    public void sendReservation(ReservationDTO reservation) {
        try (ProducerTemplate producer = context.createProducerTemplate()) {
            // reservation.setKiosqueId(kiosqueId);
            producer.sendBody("direct:cli", reservation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
