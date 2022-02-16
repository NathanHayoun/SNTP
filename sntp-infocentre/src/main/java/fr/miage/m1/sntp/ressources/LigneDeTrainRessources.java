package fr.miage.m1.sntp.ressources;

import fr.miage.m1.sntp.dao.LigneDeTrainDAO;
import fr.miage.m1.sntp.exceptions.LigneDeTrainException;
import fr.miage.m1.sntp.models.LigneDeTrain;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/ligneDeTrains")
public class LigneDeTrainRessources {
    public static final String LIGNE_DE_TRAIN_BY_ID = "/ligneDeTrain/{id}";
    public static final String ID = "id";
    @Inject
    LigneDeTrainDAO ligneDeTrainDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LigneDeTrain> getLigneDeTrains() {
        return ligneDeTrainDAO.getAllLigneDeTrains();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(LIGNE_DE_TRAIN_BY_ID)
    public LigneDeTrain getLigneDeTrain(@PathParam(ID) long id) {
        try {
            return ligneDeTrainDAO.findLigneDeTrain(id);
        } catch (LigneDeTrainException ligneDeTrainException) {
            return null;
        }
    }
}
