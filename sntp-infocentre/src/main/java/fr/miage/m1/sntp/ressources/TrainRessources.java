package fr.miage.m1.sntp.ressources;

import fr.miage.m1.sntp.application.InfoCentre;
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
    @Inject
    TrainDAO trainDAO;

    @Inject
    InfoCentre ic;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Train> getTrains() {
        return trainDAO.getAllTrains();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/train/{id}")
    public Train getTrain(@PathParam("id") long id) throws TrainException {
        //ic.genererRetard(id, 35, 1L);
        try {
            return trainDAO.findTrain(id);
        } catch (TrainException trainException) {
            return null;
        }
    }
}
