package fr.miage.m1.sntp.ressources;

import fr.miage.m1.sntp.dao.LigneDeTrainDAO;
import fr.miage.m1.sntp.exceptions.LigneDeTrainException;
import fr.miage.m1.sntp.models.LigneDeTrain;
import fr.miage.m1.sntp.models.Train;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/ligneDeTrains")
public class LigneDeTrainRessources {
    public static final String LIGNE_DE_TRAIN_BY_ID = "/ligneDeTrain/{id}";
    public static final String PATH_LIGNE_TRAIN_ID = "ligneDeTrain/";
    public static final String ID = "id";
    @Inject
    LigneDeTrainDAO ligneDeTrainDAO;
    @ConfigProperty(name = "fr.miage.m1.sntp.urlLocal")
    String localURL;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LigneDeTrain> getLigneDeTrains() {
        List<LigneDeTrain> ligneDeTrains = ligneDeTrainDAO.getAllLigneDeTrains();
        for (LigneDeTrain ligneDeTrain : ligneDeTrains) {
            JsonArray value = Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                            .add("rel", "train")
                            .add("link", localURL+PATH_LIGNE_TRAIN_ID+ligneDeTrain.getId()))
                    .build();
            ligneDeTrain.setLinks(value);
        }

        return ligneDeTrains;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(LIGNE_DE_TRAIN_BY_ID)
    public LigneDeTrain getLigneDeTrain(@PathParam(ID) long id) {
        try {
            LigneDeTrain ligneDeTrain = ligneDeTrainDAO.findLigneDeTrain(id);
            JsonArray value = Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                            .add("rel", "trains")
                            .add("link", localURL+"ligneDeTrains"))
                    .build();
            ligneDeTrain.setLinks(value);
            return ligneDeTrain;
        } catch (LigneDeTrainException ligneDeTrainException) {
            return null;
        }
    }
}
