package fr.miage.m1.sntp.ressources;

import fr.miage.m1.sntp.dao.TrainDAO;
import fr.miage.m1.sntp.exceptions.TrainException;
import fr.miage.m1.sntp.models.Train;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/trains")
public class TrainRessources {
    public static final String ID = "id";
    public static final String TRAIN_ID = "/train/{id}";
    public static final String PATH_TRAIN_ID = "trains/train/";
    public static final String PATH_LIGNETRAIN_ID = "ligneDeTrains/ligneDeTrain/";
    @Inject
    TrainDAO trainDAO;

    @Context
    UriInfo uriInfo;

    @ConfigProperty(name = "fr.miage.m1.sntp.urlLocal")
    String localURL;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Train> getTrains() {
        List<Train> trains = trainDAO.getAllTrains();
        for (Train train : trains) {
            JsonArray value = Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                            .add("rel", "train")
                            .add("link", localURL+PATH_TRAIN_ID+train.getId()))
                    .add(Json.createObjectBuilder()
                            .add("rel", "ligneDeTrain")
                            .add("link", localURL+PATH_LIGNETRAIN_ID+train.getLigneDeTrainIdLigneDeTrain().getId()))
                    .build();
            train.setLinks(value);
        }
        
        return trains;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(TRAIN_ID)
    public Train getTrain(@PathParam(ID) long id) throws TrainException {
        try {
          Train train =  trainDAO.findTrain(id);
            JsonArray value = Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                            .add("rel", "trains")
                            .add("link", localURL+"trains"))
                    .add(Json.createObjectBuilder()
                            .add("rel", "ligneDeTrain")
                            .add("link", localURL+PATH_LIGNETRAIN_ID+train.getLigneDeTrainIdLigneDeTrain().getId()))
                    .build();
            train.setLinks(value);
            return train;
        } catch (TrainException trainException) {
            return null;
        }
    }
}
