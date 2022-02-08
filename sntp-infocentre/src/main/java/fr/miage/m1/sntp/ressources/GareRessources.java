package fr.miage.m1.sntp.ressources;

import fr.miage.m1.sntp.dao.GareDAO;
import fr.miage.m1.sntp.exceptions.GareException;
import fr.miage.m1.sntp.models.Gare;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/gares")
public class GareRessources {
    @Inject
    GareDAO gareDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Gare> getGares() {
        return gareDAO.getAllGare();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gare/{id}")
    public Gare getGare(@PathParam("id") long id) {
        try {
            return gareDAO.findGare(id);
        } catch (GareException gareException) {
            return null;
        }
    }
}


