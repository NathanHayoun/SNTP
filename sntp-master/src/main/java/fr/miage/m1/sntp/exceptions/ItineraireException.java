package fr.miage.m1.sntp.exceptions;

public class ItineraireException extends Exception {

    public static class NoItineraireException extends Throwable {
        public NoItineraireException(int idItineraire) {
            super("No itineraire found with id " + idItineraire);
        }
    }

}
