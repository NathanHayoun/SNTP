package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.utils.LibSQL;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ReservationDaoImpl implements ReservationDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Reservation> findAll() {
        return LibSQL.findAll(entityManager, Reservation.class);
    }

    @Override
    @Transactional
    public Reservation findById(Long id) {
        return LibSQL.findObject(entityManager, Reservation.class, id);
    }

    @Override
    @Transactional
    public Reservation save(Reservation reservation) {
        return (Reservation) LibSQL.insertObject(entityManager, reservation);
    }

    @Override
    @Transactional
    public void update(Reservation reservation) {
        LibSQL.update(entityManager, reservation);
    }

    @Override
    @Transactional
    public void delete(Reservation reservation) {
        LibSQL.deleteObject(entityManager, reservation);
    }
}
