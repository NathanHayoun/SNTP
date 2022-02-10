package fr.miage.m1.sntp.ressource;

import fr.miage.m1.sntp.Trajets;
import fr.miage.m1.sntp.dao.VoyageurDao;
import fr.miage.m1.sntp.dto.ReservationDTO;
import fr.miage.m1.sntp.exceptions.VoyageurException;
import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.models.Voyageur;
import fr.miage.m1.sntp.services.ReservationService;

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
    VoyageurDao voyageurDao;
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
    public ReservationDTO createReservation(ReservationDTO reservation) {
        return service.reserver(reservation);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/createResa/{gareDepart}/{gareArrive}/{time}/{date}/{voyageurEmail}")
    public Reservation getReservationTest(
            @PathParam("gareDepart") String gareDepart,
            @PathParam("gareArrive") String gareArrive,
            @PathParam("time") String time,
            @PathParam("date") String date,
            @PathParam("voyageurEmail") String email
    ) {
        try {
            Voyageur voyageur = voyageurDao.findByEmail(email);
            LocalTime timeForTicket = LocalTime.parse(time);
            LocalDate dateForTicket = LocalDate.parse(date);
            return trajets.generer(gareDepart, gareArrive, timeForTicket, dateForTicket, voyageur);
        } catch (Exception | VoyageurException e) {
            System.out.println("exception " + e);
            return null;
        }
    }
}