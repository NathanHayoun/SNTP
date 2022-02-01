package fr.miage.m1.sntp.ressource;

import fr.miage.m1.sntp.Trajets;
import fr.miage.m1.sntp.dao.ReservationDao;
import fr.miage.m1.sntp.exceptions.ReservationException;
import fr.miage.m1.sntp.models.Reservation;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/reservations")
public class ReservationRessource {
    @Inject
    ReservationDao reservationDao;
    @Inject
    Trajets trajets;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> getReservations() {
        return reservationDao.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/reservation/{id}")
    public Reservation getReservation(@PathParam("id") int id) throws ReservationException {
        try {
            return reservationDao.findById(id);
        } catch (ReservationException reservationException){
            return null;
        }
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