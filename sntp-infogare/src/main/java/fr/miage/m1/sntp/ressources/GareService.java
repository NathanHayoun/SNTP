package fr.miage.m1.sntp.ressources;

import fr.miage.m1.sntp.dto.GareDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author Guila Bismuth
 * Class that retrieves the different stations from the webserver of the infocenter
 */
@Path(GareService.PATH_MAIN_GARES)
@RegisterRestClient(configKey = "gare-api")
public interface GareService {

    String PATH_MAIN_GARES = "/gares";
    String PATH_FOR_GET_GARE_BY_ID = "/gare/{id}";
    String ID = "id";

    @Path(PATH_FOR_GET_GARE_BY_ID)
    @GET
    GareDTO getGare(@PathParam(ID) long id);
}










