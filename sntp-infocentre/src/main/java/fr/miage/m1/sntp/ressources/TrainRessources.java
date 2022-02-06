package fr.miage.m1.sntp.ressources;

import fr.miage.m1.sntp.application.InfoCentreMetier;
import fr.miage.m1.sntp.dao.TrainDAO;
import fr.miage.m1.sntp.exceptions.TrainException;
import fr.miage.m1.sntp.models.Train;
import org.jgroups.util.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/trains")
public class TrainRessources {
    private static final Logger logger = LoggerFactory.getLogger(TrainRessources.class);
    @Inject
    TrainDAO trainDAO;

    @Inject
    InfoCentreMetier ic;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Train> getTrains() {
        return trainDAO.getAllTrains();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/train/{id}")
    public Train getTrain(@PathParam("id") long id) throws TrainException {
        Tuple<Boolean, String> tuple = ic.ajouterStation(3L, 1L);
        logger.warn(tuple.getVal2());
        try {
            return trainDAO.findTrain(id);
        } catch (TrainException trainException) {
            return null;
        }
    }
}
