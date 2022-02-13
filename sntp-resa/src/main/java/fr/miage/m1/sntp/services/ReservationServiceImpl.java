package fr.miage.m1.sntp.services;

import fr.miage.m1.sntp.dao.ReservationDao;
import fr.miage.m1.sntp.models.Reservation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ReservationServiceImpl implements ReservationService {


    @Inject
    ReservationDao reservationDao;

    @Override
    public List<Reservation> getReservations() {
        return reservationDao.findAll();
    }

    @Override
    public Reservation getReservation(Long id) {
        return reservationDao.findById(id);
    }
}