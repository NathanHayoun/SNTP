package fr.miage.m1.sntp.exceptions;

public class PassageException extends Exception {

    public static class NoPassageException extends Throwable {
        public NoPassageException(int idPassage) {
            super("No train found with id " + idPassage);
        }
    }

}
