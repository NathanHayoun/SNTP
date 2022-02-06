package fr.miage.m1.sntp.ressource;

import fr.miage.m1.sntp.dto.TrainDTO;
import fr.miage.m1.sntp.dto.VoyageDTO;
import fr.miage.m1.sntp.services.KiosqueService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/kiosque")
public class KiosqueResource {

    @Inject
    protected KiosqueService service;

    @Path("trains/{villeDepart}/{villeArrivee}/{date}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<VoyageDTO> getVoyages(@PathParam("villeDepart") String villeDepart, @PathParam("villeArrivee") String villeArrive, @PathParam("date") String date) {
        return service.getVoyages(villeDepart, villeArrive, date);
    }
}
