package fr.miage.m1.sntp.exceptions;

public class LigneDeTrainException extends Exception {

    public static class NoLigneDeTrainException extends Throwable {
        public NoLigneDeTrainException(int idLigneDeTrain) {
            super("No ligne de train found with id " + idLigneDeTrain);
        }
    }

}
