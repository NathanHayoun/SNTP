package fr.miage.m1.sntp.services;


import fr.miage.m1.sntp.dto.GareDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Collection;

/**
 * @author Quentin Vaillant
 */
@RegisterRestClient(configKey = GareService.API_KEY_INFOCENTRE)
public interface GareService {

    String API_KEY_INFOCENTRE = "infocentre-api";
    String PATH_TO_GARE = "gares";

    @Path(PATH_TO_GARE)
    @GET
    Collection<GareDTO> getGares();
}
