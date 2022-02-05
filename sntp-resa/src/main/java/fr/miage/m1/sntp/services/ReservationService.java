package fr.miage.m1.sntp.services;

import fr.miage.m1.sntp.models.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation reserver(Reservation reservation);

    List<Reservation> getReservations();

    Reservation getReservation(Long id);
}
