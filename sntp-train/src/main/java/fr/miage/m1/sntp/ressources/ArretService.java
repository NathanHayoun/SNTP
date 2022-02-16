package fr.miage.m1.sntp.ressources;

import fr.miage.m1.sntp.dto.ArretDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * @author Etan Guir
 */
@Path(ArretService.MAIN_PATH_FOR_ARRETS)
@RegisterRestClient(configKey = ArretService.INFOCENTRE_API_KEY)
public interface ArretService {

    String MAIN_PATH_FOR_ARRETS = "/arrets";
    String INFOCENTRE_API_KEY = "infocentre-api";
    String PATH_FOR_ARRET_BY_NUM_TRAIN = "/arret/train/{numero}";
    String NUMERO = "numero";

    @Path(PATH_FOR_ARRET_BY_NUM_TRAIN)
    @GET
    List<ArretDTO> getArrets(@PathParam(NUMERO) int numeroDeTrain);
}
