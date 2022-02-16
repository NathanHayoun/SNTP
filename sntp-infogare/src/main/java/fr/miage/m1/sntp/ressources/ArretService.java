package fr.miage.m1.sntp.ressources;


import fr.miage.m1.sntp.dto.ArretDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * @author Guila Bismuth
 * Class that retrieves the different stops from the webserver of the infocenter
 */
@Path(ArretService.PATH_MAIN_FOR_ARRET)
@RegisterRestClient(configKey = ArretService.CONFIG_KEY_GARE_API)
public interface ArretService {

    String PATH_MAIN_FOR_ARRET = "/arrets";
    String CONFIG_KEY_GARE_API = "gare-api";
    String PATH_FOR_GET_ARRET_BY_ID = "/arret/{id}";
    String PATH_FOR_GET_TRAIN_BY_NUMBER = "/arret/train/{numero}";
    String ID = "id";
    String NUMERO = "numero";
    String ID_GARE = "idGare";
    String PATH_FOR_GET_ARRET_DEPARTURE = "/arret/depart/{idGare}";
    String PATH_FOR_GET_ARRET_ARRIVALS = "/arret/arrivee/{idGare}";

    @Path(PATH_FOR_GET_ARRET_BY_ID)
    @GET
    ArretDTO getArret(@PathParam(ID) long id);

    @Path(PATH_FOR_GET_TRAIN_BY_NUMBER)
    @GET
    List<ArretDTO> getArretByNumTrain(@PathParam(NUMERO) int numeroDeTrain);

    @Path(PATH_FOR_GET_ARRET_DEPARTURE)
    @GET
    List<ArretDTO> getArretsDepartByGare(@PathParam(ID_GARE) int idGare);

    @Path(PATH_FOR_GET_ARRET_ARRIVALS)
    @GET
    List<ArretDTO> getArretsArriveeByGare(@PathParam(ID_GARE) int idGare);

}
