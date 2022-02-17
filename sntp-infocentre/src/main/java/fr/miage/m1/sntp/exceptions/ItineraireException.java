package fr.miage.m1.sntp.exceptions;

public class ItineraireException extends Exception {

    public static final String NO_ITINERAIRE_FOUND_WITH_ID = "Aucun itineraire trouvé avec l'id %s";

    public static class NoItineraireException extends Throwable {
        public NoItineraireException(String message, Throwable cause) {
            super(message, cause);
        }

        public NoItineraireException(int idItineraire) {
            super(String.format(NO_ITINERAIRE_FOUND_WITH_ID, idItineraire));
        }
    }

}
