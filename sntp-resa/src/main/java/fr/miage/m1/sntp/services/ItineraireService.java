package fr.miage.m1.sntp.services;

import fr.miage.m1.sntp.dto.ItineraireDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path(ItineraireService.ITINERAIRES_KEY)
@RegisterRestClient(configKey = ItineraireService.GARE_API_CONFIG_KEY)
public interface ItineraireService {

    String ITINERAIRES_KEY = "/itineraires";
    String GARE_API_CONFIG_KEY = "gare-api";

    @GET
    List<ItineraireDTO> getItineraire();
}
