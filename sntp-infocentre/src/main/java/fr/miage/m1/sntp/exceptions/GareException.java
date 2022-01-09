package fr.miage.m1.sntp.exceptions;

public class GareException extends Exception {

    public static class NoGareException extends Throwable {
        public NoGareException(int idGare) {
            super("No train found with id " + idGare);
        }
    }

}
