package fr.miage.m1.sntp.ressource;

import fr.miage.m1.sntp.dao.VoyageurDao;
import fr.miage.m1.sntp.exceptions.VoyageurException;
import fr.miage.m1.sntp.metier.Trajets;
import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.models.Ticket;
import fr.miage.m1.sntp.models.Voyageur;
import fr.miage.m1.sntp.services.ReservationService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Path("/reservations")
public class ReservationRessource {
    @Inject
    VoyageurDao voyageurDao;
    @Inject
    Trajets trajets;
    @Inject
    ReservationService reservationService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> getReservations() {
        return reservationService.getReservations();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/reservation/{id}")
    public Reservation getReservation(@PathParam("id") Long id) {
        return reservationService.getReservation(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/createResa/{gareDepart}/{gareArrive}/{time}/{date}/{voyageurEmail}/{idReservation}")
    public Reservation generateReservation(
            @PathParam("gareDepart") String gareDepart,
            @PathParam("gareArrive") String gareArrive,
            @PathParam("time") String time,
            @PathParam("date") String date,
            @PathParam("voyageurEmail") String email,
            @PathParam("idReservation") Long idReservation
    ) {
        try {
            Voyageur voyageur = voyageurDao.findByEmail(email);
            LocalTime timeForTicket = LocalTime.parse(time);
            LocalDate dateForTicket = LocalDate.parse(date);
            return trajets.generer(gareDepart, gareArrive, timeForTicket, dateForTicket, voyageur, idReservation);
        } catch (Exception | VoyageurException e) {
            System.out.println("exception " + e);
            return null;
        }
    }
}