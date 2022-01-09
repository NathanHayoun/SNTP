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
    public Arret getArret(@PathParam("id") long id) throws ArretException {
        try {
            return arretDAO.findArret(id);
        } catch (ArretException arretException) {
            return null;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/arret/train/{numero}")
    public List<Arret> getArretByNumTrain(@PathParam("numero") int numeroDeTrain) throws ArretException {
        try {
            return arretDAO.getAllArretByNumeroDeTrain(numeroDeTrain);
        } catch (ArretException arretException) {
            return null;
        }
    }
}
