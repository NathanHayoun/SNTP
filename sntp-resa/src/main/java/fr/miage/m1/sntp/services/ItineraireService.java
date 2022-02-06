package fr.miage.m1.sntp.services;

import fr.miage.m1.sntp.dto.ItineraireDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/itineraires")
@RegisterRestClient(configKey = "gare-api")
public interface ItineraireService {
    @GET
    List<ItineraireDTO> getItineraire();
}
