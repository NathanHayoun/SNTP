package fr.miage.m1.sntp.ressource;

import fr.miage.m1.sntp.dto.ReservationDTO;
import fr.miage.m1.sntp.Trajets;
import fr.miage.m1.sntp.dao.ReservationDao;
import fr.miage.m1.sntp.exceptions.ReservationException;
import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalTime;
import java.util.List;

@Path("/reservations")
public class ReservationRessource {

    @Inject
    ReservationService service;

    @Inject
    ReservationDao reservationDao;

    @Inject
    Trajets trajets;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test")
    public Object getReservationTest() throws ReservationException {
        try {
            System.out.println("getReservationTest");
            return trajets.generer(1, 3, LocalTime.parse("10:00:00"));
        } catch (Exception e){
            return null;
        }
    }
}