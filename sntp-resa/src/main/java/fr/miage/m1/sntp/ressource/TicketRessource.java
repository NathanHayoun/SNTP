package fr.miage.m1.sntp.ressource;

import fr.miage.m1.sntp.dao.TicketDao;
import fr.miage.m1.sntp.exceptions.TicketException;
import fr.miage.m1.sntp.models.Ticket;
import fr.miage.m1.sntp.models.Voyageur;
import fr.miage.m1.sntp.services.TicketService;

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
    TicketService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> getTickets() {
        return service.getTickets();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/ticket/{id}")
    public Ticket getTicket(@PathParam("id") Long id) {
        return service.getTicket(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/ticket/train/now/{numeroDeTrain}")
    public Long getNombreDeTicketByTrainAndDate(@PathParam("numeroDeTrain") int numeroDeTrain) {
        return service.countNbTicketByNumeroTrainAndNow(numeroDeTrain);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/ticket/train/now/has/correspondance/{numeroDeTrain}")
    public Long getNombreDeTicketByTrainAndDateAndHasCorrespondance(@PathParam("numeroDeTrain") int numeroDeTrain) {
        return service.countNbTicketByNumeroTrainAndNowAndHasEtape(numeroDeTrain);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/ticket/train/email/{numeroDeTrain}")
    public List<Voyageur> getEmailFromUserByTrainAndToDay(@PathParam("numeroDeTrain") int numeroDeTrain) {
        return service.getEmailsByTrainAndDate(numeroDeTrain);
    }
}
