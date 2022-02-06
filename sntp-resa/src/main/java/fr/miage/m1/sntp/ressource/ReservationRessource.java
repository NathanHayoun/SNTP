package fr.miage.m1.sntp.ressource;

import fr.miage.m1.sntp.Trajets;
import fr.miage.m1.sntp.dao.ReservationDao;
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

    @Inject
    ReservationDao reservationDao;
  
    @Inject
    Trajets trajets;

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
    public Reservation createReservation(Reservation reservation) {
        return service.reserver(reservation);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test")
    public Object getReservationTest() throws ReservationException {
        try {
            System.out.println("test");
            return trajets.generer(1, 3);
        } catch (Exception e){
            return null;
        }
    }
}