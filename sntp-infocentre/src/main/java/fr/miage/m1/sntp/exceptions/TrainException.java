package fr.miage.m1.sntp.exceptions;

public class TrainException extends Exception {

    public static final String NO_TRAIN_FOUND_WITH_ID = "Aucun train trouvé avec l'id %s";

    public static class NoTrainException extends Throwable {
        public NoTrainException(String message, Throwable cause) {
            super(message, cause);
        }

        public NoTrainException(int idTrain) {
            super(String.format(NO_TRAIN_FOUND_WITH_ID, idTrain));
        }
    }

}
