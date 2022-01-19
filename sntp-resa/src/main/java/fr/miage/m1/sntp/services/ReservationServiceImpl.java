package fr.miage.m1.sntp.services;

import fr.miage.m1.sntp.models.Reservation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ReservationServiceImpl implements ReservationService {

    @PersistenceContext
    EntityManager em;

    @Override
    public Reservation reserver(Reservation reservation) {
        em.persist(reservation);
        return reservation;
    }

}
