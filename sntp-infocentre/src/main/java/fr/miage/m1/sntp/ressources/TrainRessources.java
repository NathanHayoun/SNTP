package fr.miage.m1.sntp.ressources;

import fr.miage.m1.sntp.dao.TrainDAO;
import fr.miage.m1.sntp.exceptions.TrainException;
import fr.miage.m1.sntp.models.Train;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/trains")
public class TrainRessources {
    public static final String ID = "id";
    public static final String TRAIN_ID = "/train/{id}";
    public static final String PATH_TRAIN_ID = "http://localhost:8080/trains/train/";
    @Inject
    TrainDAO trainDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Train> getTrains() {
        List<Train> trains = trainDAO.getAllTrains();
        for (Train train : trains) {
            train.setUri(PATH_TRAIN_ID+train.getId());
            train.setUri2("Test2");
        }
        
        return trains;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(TRAIN_ID)
    public Train getTrain(@PathParam(ID) long id) throws TrainException {
        try {
            return trainDAO.findTrain(id);
        } catch (TrainException trainException) {
            return null;
        }
    }
}
