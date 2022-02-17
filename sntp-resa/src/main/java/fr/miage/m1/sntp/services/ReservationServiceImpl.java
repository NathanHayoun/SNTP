package fr.miage.m1.sntp.services;

import fr.miage.m1.sntp.dao.ReservationDao;
import fr.miage.m1.sntp.dao.TicketDao;
import fr.miage.m1.sntp.dao.VoyageurDao;
import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.models.Ticket;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ReservationServiceImpl implements ReservationService {

    @Inject
    ReservationDao reservationDao;
    @Inject
    VoyageurDao voyageurDao;
    @Inject
    TicketDao ticketDao;

    @Override
    public List<Reservation> getReservations() {
        return reservationDao.findAll();
    }

    @Override
    public Reservation getReservation(Long id) {
        return reservationDao.findById(id);
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        voyageurDao.update(reservation.getVoyageur());

        for (Ticket ticket : reservation.getTickets()) {
            ticketDao.update(ticket);
        }
        return reservationDao.update(reservation);
    }
}