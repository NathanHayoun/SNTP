package fr.miage.m1.sntp.ressource;

import fr.miage.m1.sntp.dao.ReservationDao;
import fr.miage.m1.sntp.exceptions.ReservationException;
import fr.miage.m1.sntp.models.Reservation;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Path("/reservations")
public class ReservationRessource {
    @Inject
    ReservationDao reservationDao;

    //@Inject
    //private reservationService reservationService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> getReservations() {
        return reservationDao.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/reservation/{id}")
    public Reservation getReservation(@PathParam("id") Long id) throws ReservationException {
        try {
            return reservationDao.findById(id);
        } catch (ReservationException reservationException){
            return null;
        }
    }

/*    @Path("/reservation/{date}/{idTrain}")
    public void reserver(@PathParam("date") Date date, @PathParam("idTrain") String idTrain) {
        //reservationService.reserver(date, idTrain);
    }

    @Path("/annuler/{id}")
    public void annuler(@PathParam("id") int id) {
        //reservationService.annuler(date, idTrain);
    }

    @Path("/{date}/{idTrain}")
    public void listeReservations(@PathParam("date") Date date, @PathParam("idTrain") String idTrain) {
        // reservationService.listeReservations(date, idTrain);
    }*/
}