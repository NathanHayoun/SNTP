package fr.miage.m1.sntp.ressources;

import fr.miage.m1.sntp.dao.PassageDAO;
import fr.miage.m1.sntp.exceptions.ArretException;
import fr.miage.m1.sntp.exceptions.PassageException;
import fr.miage.m1.sntp.models.Passage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/passages")
public class PassageRessources {
    @Inject
    PassageDAO passageDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Passage> getPassages() {
        return passageDAO.getAllPassages();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/Passage/{id}")
    public Passage getPassage(@PathParam("id") long id) throws PassageException {
        try {
            return passageDAO.findPassage(id);
        } catch (PassageException passageException) {
            return null;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gare/depart/{idGare}")
    public List<Passage> get10prochainsTrajetsDuJourByGareDepart(@PathParam("idGare") Long idGare) throws ArretException {
        try {
            return passageDAO.findprochainsTrajetsDuJourByGareDepart(idGare);
        } catch (PassageException arretException) {
            return null;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gare/arrivee/{idGare}")
    public List<Passage> get10prochainsTrajetsDuJourByGareArrivee(@PathParam("idGare") Long idGare) throws ArretException {
        try {
            return passageDAO.findprochainsTrajetsDuJourByGareDepart(idGare);
        } catch (PassageException arretException) {
            return null;
        }
    }

}
