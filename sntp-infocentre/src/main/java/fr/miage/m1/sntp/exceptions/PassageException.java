package fr.miage.m1.sntp.exceptions;

public class PassageException extends Exception {

    public static final String TRAIN_FOUND_WITH_ID = "No train found with id %s";

    public static class NoPassageException extends Throwable {
        public NoPassageException(String message, Throwable cause) {
            super(message, cause);
        }

        public NoPassageException(int idPassage) {
            super(String.format(TRAIN_FOUND_WITH_ID, idPassage));
        }
    }

}
