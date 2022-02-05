package fr.miage.m1.sntp.services;

import fr.miage.m1.sntp.dao.ReservationDao;
import fr.miage.m1.sntp.models.Reservation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ReservationServiceImpl implements ReservationService {

    @PersistenceContext
    EntityManager em;

    @Inject
    ReservationDao reservationDao;

    @Override
    @Transactional
    public Reservation reserver(Reservation reservation) {
        return reservationDao.save(reservation);
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationDao.findAll();
    }

    @Override
    public Reservation getReservation(Long id) {
        return reservationDao.findById(id);
    }
}
