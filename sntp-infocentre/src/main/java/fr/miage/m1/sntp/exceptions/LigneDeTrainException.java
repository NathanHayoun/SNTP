package fr.miage.m1.sntp.exceptions;

public class LigneDeTrainException extends Exception {

    public static final String LIGNE_DE_TRAIN_FOUND_WITH_ID = "No ligne de train found with id %s";

    public static class NoLigneDeTrainException extends Throwable {
        public NoLigneDeTrainException(String message, Throwable cause) {
            super(message, cause);
        }

        public NoLigneDeTrainException(int idLigneDeTrain) {
            super(String.format(LIGNE_DE_TRAIN_FOUND_WITH_ID, idLigneDeTrain));
        }
    }

}
