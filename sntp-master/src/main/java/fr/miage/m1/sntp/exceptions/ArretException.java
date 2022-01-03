package fr.miage.m1.sntp.exceptions;

public class ArretException extends Exception {

    public static class NoArretException extends Throwable {
        public NoArretException(int idArret) {
            super("No train found with id " + idArret);
        }
    }

}
