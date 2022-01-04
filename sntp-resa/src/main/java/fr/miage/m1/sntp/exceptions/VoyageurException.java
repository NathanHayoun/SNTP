package fr.miage.m1.sntp.exceptions;

public class VoyageurException extends Throwable {
    public static class NoVoyageurException extends Throwable {
        public NoVoyageurException(int idVoyageur) {
            super("No traveller found with id " + idVoyageur);
        }
    }
}
