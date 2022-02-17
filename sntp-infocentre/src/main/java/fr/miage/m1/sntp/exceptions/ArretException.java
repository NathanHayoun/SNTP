package fr.miage.m1.sntp.exceptions;

public class ArretException extends Exception {

    public static final String NO_TRAIN_FOUND_WITH_ID = "Aucun train trouvé avec l'id %s";

    public static class NoArretException extends Throwable {
        public NoArretException(String message, Throwable cause) {
            super(message, cause);
        }

        public NoArretException(int idArret) {
            super(String.format(NO_TRAIN_FOUND_WITH_ID, idArret));
        }
    }

}
