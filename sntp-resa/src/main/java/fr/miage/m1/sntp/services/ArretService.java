package fr.miage.m1.sntp.services;

import fr.miage.m1.sntp.dto.ArretDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path(ArretService.ARRETS_KEY)
@RegisterRestClient(configKey = ArretService.GARE_API_CONFIG_KEY)
public interface ArretService {

    String ARRETS_KEY = "/arrets";
    String GARE_API_CONFIG_KEY = "gare-api";
    String ARRET_BY_ID = "/arret/{id}";
    String ARRET_BY_GARE_DEPART = "/arret/depart/{idGare}";
    String ARRET_BY_GARE_ARRIVEE = "/arret/arrivee/{idGare}";
    String ID_GARE = "idGare";
    String ID = "id";

    @GET
    List<ArretDTO> getArrets();

    @Path(ARRET_BY_ID)
    @GET
    ArretDTO getArret(@PathParam(ID) long id);

    @Path(ARRET_BY_GARE_DEPART)
    @GET
    List<ArretDTO> getArretsDepartByGare(@PathParam(ID_GARE) int idGare);

    @Path(ARRET_BY_GARE_ARRIVEE)
    @GET
    List<ArretDTO> getArretsArriveeByGare(@PathParam(ID_GARE) int idGare);

}
