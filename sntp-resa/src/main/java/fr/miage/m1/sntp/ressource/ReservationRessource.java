package fr.miage.m1.sntp.ressource;

import fr.miage.m1.sntp.dto.ReservationDTO;
import fr.miage.m1.sntp.exceptions.ReservationException;
import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.services.ReservationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/reservations")
public class ReservationRessource {

    @Inject
    ReservationService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> getReservations() {
        return service.getReservations();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/reservation/{id}")
    public Reservation getReservation(@PathParam("id") Long id) {
        return service.getReservation(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/reservation")
    public ReservationDTO createReservation(ReservationDTO reservation) {
        return service.reserver(reservation);
    }
}