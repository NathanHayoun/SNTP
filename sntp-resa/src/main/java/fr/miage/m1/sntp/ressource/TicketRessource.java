package fr.miage.m1.sntp.ressource;

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

@Path(TicketRessource.TICKETS_MAIN)
public class TicketRessource {

    public static final String TICKETS_MAIN = "/tickets";
    public static final String GET_TICKET_BY_ID = "/ticket/{id}";
    public static final String GET_TICKET_BY_NUM_TRAIN = "/ticket/train/now/{numeroDeTrain}";
    public static final String GET_TICKET_WITH_CORRESPONDANCE = "/ticket/train/now/has/correspondance/{numeroDeTrain}";
    public static final String GET_EMAIL_IN_TRAIN = "/ticket/train/email/{numeroDeTrain}";
    public static final String NUMERO_DE_TRAIN = "numeroDeTrain";
    public static final String ID = "id";
    @Inject
    TicketService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> getTickets() {
        return service.getTickets();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(GET_TICKET_BY_ID)
    public Ticket getTicket(@PathParam(ID) Long id) {
        return service.getTicket(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(GET_TICKET_BY_NUM_TRAIN)
    public Long getNombreDeTicketByTrainAndDate(@PathParam(NUMERO_DE_TRAIN) int numeroDeTrain) {
        return service.countNbTicketByNumeroTrainAndNow(numeroDeTrain);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(GET_TICKET_WITH_CORRESPONDANCE)
    public Long getNombreDeTicketByTrainAndDateAndHasCorrespondance(@PathParam(NUMERO_DE_TRAIN) int numeroDeTrain) {
        return service.countNbTicketByNumeroTrainAndNowAndHasEtape(numeroDeTrain);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(GET_EMAIL_IN_TRAIN)
    public List<Voyageur> getEmailFromUserByTrainAndToDay(@PathParam(NUMERO_DE_TRAIN) int numeroDeTrain) {
        return service.getEmailsByTrainAndDate(numeroDeTrain);
    }
}
