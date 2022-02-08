package fr.miage.m1.sntp.ressources;

import fr.miage.m1.sntp.dao.ArretDAO;
import fr.miage.m1.sntp.exceptions.ArretException;
import fr.miage.m1.sntp.models.Arret;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("/arrets")
public class ArretRessources {
    @Inject
    ArretDAO arretDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Arret> getArrets() {
        return arretDAO.getAllArret();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/arret/{id}")
    public Arret getArret(@PathParam("id") long id) {
        try {
            return arretDAO.findArret(id);
        } catch (ArretException arretException) {
            return null;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/arret/train/{numero}")
    public List<Arret> getArretByNumTrain(@PathParam("numero") Integer numeroDeTrain) {
        try {
            return arretDAO.getAllArretByNumeroDeTrain(numeroDeTrain);
        } catch (ArretException arretException) {
            return Collections.emptyList();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/arret/depart/{idGare}")
    public List<Arret> getArretsDepartByGare(@PathParam("idGare") int idGare) {
        try {
            return arretDAO.getArretsDepartByGare(idGare);
        } catch (ArretException arretException) {
            return Collections.emptyList();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/arret/arrivee/{idGare}")
    public List<Arret> getArretsArriveeByGare(@PathParam("idGare") int idGare) {
        try {
            return arretDAO.getArretsArriveeByGare(idGare);
        } catch (ArretException arretException) {
            return Collections.emptyList();
        }
    }
}
