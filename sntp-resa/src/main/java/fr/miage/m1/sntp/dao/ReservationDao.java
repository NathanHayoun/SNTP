package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.models.Reservation;

import java.util.List;

public interface ReservationDao {
    List<Reservation> findAll();

    Reservation findById(Long id);

    Reservation save(Reservation reservation);

    Reservation update(Reservation reservation);

    void delete(Reservation reservation);
}
