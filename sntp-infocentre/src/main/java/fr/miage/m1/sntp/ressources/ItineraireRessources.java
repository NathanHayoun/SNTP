package fr.miage.m1.sntp.ressources;

import fr.miage.m1.sntp.dao.ItineraireDAO;
import fr.miage.m1.sntp.exceptions.ItineraireException;
import fr.miage.m1.sntp.models.Itineraire;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/itineraires")
public class ItineraireRessources {
    @Inject
    ItineraireDAO itineraireDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Itineraire> getTrains() {
        return itineraireDAO.getAllItineraires();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/itineraire/{id}")
    public Itineraire getitineraireDAO(@PathParam("id") long id) throws ItineraireException {
        try {
            return itineraireDAO.findItineraire(id);
        } catch (ItineraireException itineraireException) {
            return null;
        }
    }
}
