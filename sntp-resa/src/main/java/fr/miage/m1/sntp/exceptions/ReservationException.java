package fr.miage.m1.sntp.exceptions;

public class ReservationException extends Throwable {
    public static class NoReservationException extends Throwable {
        public NoReservationException(int idReservation) {
            super("No reservation found with id " + idReservation);
        }
    }
}
