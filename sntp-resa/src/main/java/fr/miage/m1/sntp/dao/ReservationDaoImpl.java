package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.utils.LibSQL;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ReservationDaoImpl implements ReservationDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Reservation> findAll() {
        return LibSQL.findAll(entityManager, Reservation.class);
    }

    @Override
    public Reservation findById(int id) {
        return LibSQL.findObject(entityManager, Reservation.class, id);
    }

    @Override
    public void save(Reservation reservation) {
        LibSQL.insertObject(entityManager, reservation);
    }

    @Override
    public void update(Reservation reservation) {
        // NOP
    }

    @Override
    public void delete(Reservation reservation) {
        LibSQL.deleteObject(entityManager, reservation);
    }
}
