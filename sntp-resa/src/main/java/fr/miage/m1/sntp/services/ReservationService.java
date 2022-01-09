package fr.miage.m1.sntp.services;

import fr.miage.m1.sntp.models.Reservation;

public interface ReservationService {
    public Reservation reserver(Reservation reservation);
    /*public void annuler(String id);
    public void updateReservation(String id, String id_user, String id_salle, String date, String heure);
    public void getReservation(String id);
    public void getAllReservations();*/
}
