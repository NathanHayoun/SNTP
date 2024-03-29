package fr.miage.m1.sntp.ressources;

import fr.miage.m1.sntp.dao.PassageDAO;
import fr.miage.m1.sntp.exceptions.PassageException;
import fr.miage.m1.sntp.models.Passage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("/passages")
public class PassageRessources {
    public static final String PASSAGE_BY_ID = "/passage/{id}";
    public static final String GARE_DEPART_BY_ID_GARE = "/gare/depart/{idGare}";
    public static final String ID = "id";
    public static final String ID_GARE = "idGare";
    public static final String GARE_ARRIVEE_BY_ID_GARE = "/gare/arrivee/{idGare}";
    @Inject
    PassageDAO passageDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Passage> getPassages() {
        return passageDAO.getAllPassages();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(PASSAGE_BY_ID)
    public Passage getPassage(@PathParam(ID) long id) {
        try {
            return passageDAO.findPassage(id);
        } catch (PassageException passageException) {
            return null;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(GARE_DEPART_BY_ID_GARE)
    public List<Passage> get10prochainsTrajetsDuJourByGareDepart(@PathParam(ID_GARE) Long idGare) {
        try {
            return passageDAO.findprochainsTrajetsDuJourByGareDepart(idGare);
        } catch (PassageException arretException) {
            return Collections.emptyList();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(GARE_ARRIVEE_BY_ID_GARE)
    public List<Passage> get10prochainsTrajetsDuJourByGareArrivee(@PathParam(ID_GARE) Long idGare) {
        try {
            return passageDAO.findprochainsTrajetsDuJourByGareArrivee(idGare);
        } catch (PassageException arretException) {
            return Collections.emptyList();
        }
    }

}
