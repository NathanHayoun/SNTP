package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.ReservationException;
import fr.miage.m1.sntp.models.Reservation;

import java.util.List;

public interface ReservationDao {
    List<Reservation> findAll();
    Reservation findById(int id) throws ReservationException;
    void save(Reservation reservation);
    void update(Reservation reservation);
    void delete(Reservation reservation);
}
