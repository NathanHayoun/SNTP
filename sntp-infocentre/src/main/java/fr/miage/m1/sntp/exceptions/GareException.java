package fr.miage.m1.sntp.exceptions;

public class GareException extends Exception {

    public static final String NO_TRAIN_FOUND_WITH_ID = "No train found with id %s";

    public static class NoGareException extends Throwable {
        public NoGareException(String message, Throwable cause) {
            super(message, cause);
        }

        public NoGareException(int idGare) {
            super(String.format(NO_TRAIN_FOUND_WITH_ID, idGare));
        }
    }

}
