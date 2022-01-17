package fr.miage.m1.sntp.ressource;

import fr.miage.m1.sntp.dao.TicketDao;
import fr.miage.m1.sntp.exceptions.TicketException;
import fr.miage.m1.sntp.models.Ticket;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tickets")
public class TicketRessource {
    @Inject
    TicketDao ticketDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> getTickets() {
        return ticketDao.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/ticket/{id}")
    public Ticket getTicket(@PathParam("id") int id) throws TicketException {
        try {
            return ticketDao.findById(id);
        } catch (TicketException ticketException) {
            return null;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/ticket/train/now/{numeroDeTrain}")
    public Long getNombreDeTicketByTrainAndDate(@PathParam("numeroDeTrain") int numeroDeTrain) {
        return ticketDao.countNbTicketByNumeroTrainAndNow(numeroDeTrain);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/ticket/train/now/has/correspondance/{numeroDeTrain}")
    public Long getNombreDeTicketByTrainAndDateAndHasCorrespondance(@PathParam("numeroDeTrain") int numeroDeTrain) {
        return ticketDao.countNbTicketByNumeroTrainAndNowAndHasEtape(numeroDeTrain);
    }
}
