package fr.miage.m1.sntp.ressource;

import fr.miage.m1.sntp.dao.VoyageurDao;
import fr.miage.m1.sntp.exceptions.VoyageurException;
import fr.miage.m1.sntp.metier.Trajets;
import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.models.Ticket;
import fr.miage.m1.sntp.models.Voyageur;
import fr.miage.m1.sntp.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Path(ReservationRessource.RESERVATIONS_KEY)
public class ReservationRessource {
    public static final String RESERVATIONS_KEY = "/reservations";
    public static final String ERREUR_TRAJET = "Erreur pendant la génération du trajet ";
    public static final String RESERVATION_BY_ID = "/reservation/{id}";
    public static final String CREATE_RESA = "/createResa/{gareDepart}/{gareArrive}/{time}/{date}/{voyageurEmail}/{idReservation}";
    public static final String GARE_DEPART = "gareDepart";
    public static final String GARE_ARRIVE = "gareArrive";
    public static final String TIME = "time";
    public static final String DATE = "date";
    public static final String VOYAGEUR_EMAIL = "voyageurEmail";
    public static final String ID_RESERVATION = "idReservation";
    public static final String ID = "id";
    private static final Logger logger = LoggerFactory.getLogger(ReservationRessource.class);
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

    @Path(RESERVATION_BY_ID)
    public Reservation getReservation(@PathParam(ID) Long id) {
        return reservationService.getReservation(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(CREATE_RESA)
    public Reservation generateReservation(
            @PathParam(GARE_DEPART) String gareDepart,
            @PathParam(GARE_ARRIVE) String gareArrive,
            @PathParam(TIME) String time,
            @PathParam(DATE) String date,
            @PathParam(VOYAGEUR_EMAIL) String email,
            @PathParam(ID_RESERVATION) Long idReservation
    ) {
        try {
            Voyageur voyageur = voyageurDao.findByEmail(email);
            LocalTime timeForTicket = LocalTime.parse(time);
            LocalDate dateForTicket = LocalDate.parse(date);

            return trajets.generer(gareDepart, gareArrive, timeForTicket, dateForTicket, voyageur, idReservation);
        } catch (Exception | VoyageurException e) {
            logger.warn(ERREUR_TRAJET, e);

            return null;
        }
    }
}