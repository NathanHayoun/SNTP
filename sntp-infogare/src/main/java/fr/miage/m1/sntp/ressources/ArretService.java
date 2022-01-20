package fr.miage.m1.sntp.ressources;


import fr.miage.m1.sntp.dto.ArretDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/arrets")
@RegisterRestClient(configKey = "gare-api")
public interface ArretService {

//    @GET
//    List<ArretDTO> getArrets();

    @Path("/arret/{id}")
    @GET
    ArretDTO getArret(@PathParam("id") long id) ;

    @Path("/arret/train/{numero}")
    @GET
    List<ArretDTO> getArretByNumTrain(@PathParam("numero") int numeroDeTrain);

    @Path("/arret/depart/{idGare}")
    @GET
    List<ArretDTO> getArretsDepartByGare(@PathParam("idGare") int idGare);

    @Path("/arret/arrivee/{idGare}")
    @GET
    List<ArretDTO> getArretsArriveeByGare(@PathParam("idGare") int idGare);

}
