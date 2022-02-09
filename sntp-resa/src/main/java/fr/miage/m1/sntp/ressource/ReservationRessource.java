package fr.miage.m1.sntp.ressource;

import fr.miage.m1.sntp.Trajets;
import fr.miage.m1.sntp.dao.ReservationDao;
import fr.miage.m1.sntp.dao.VoyageurDao;
import fr.miage.m1.sntp.exceptions.ReservationException;
import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.models.Voyageur;
import fr.miage.m1.sntp.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Path("/reservations")
public class ReservationRessource {

    @Inject
    ReservationService service;

    @Inject
    ReservationDao reservationDao;

    @Inject
    VoyageurDao voyageurDao;
  
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
    public Reservation createReservation(Reservation reservation) {
        return service.reserver(reservation);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test")
    public Reservation getReservationTest() throws ReservationException {
        System.out.println("je test");
        try {
            Voyageur voyageur = new Voyageur();
            voyageur.setEmail("nathanpapy@hotmail.fr");
            voyageur.setNom("Hayoun");
            voyageur.setPrenom("Nathan");
            return trajets.generer("Paris", "Rome", LocalTime.parse("07:00:00"), LocalDate.now(),voyageur);
        } catch (Exception e){
            return null;
        }
    }
}