package fr.miage.m1.sntp.ressource;

import fr.miage.m1.sntp.dao.VoyageurDao;
import fr.miage.m1.sntp.exceptions.VoyageurException;
import fr.miage.m1.sntp.models.Voyageur;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/voyageurs")
public class VoyageurRessource {
    @Inject
    VoyageurDao voyageurDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Voyageur> getVoyageurs() {
        return voyageurDao.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/voyageur/{id}")
    public Voyageur getVoyageur(@PathParam("id") int id) throws VoyageurException {
        return voyageurDao.findById(id);
    }

}
