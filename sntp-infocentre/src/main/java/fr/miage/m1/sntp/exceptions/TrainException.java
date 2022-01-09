package fr.miage.m1.sntp.exceptions;

public class TrainException extends Exception {

    public static class NoTrainException extends Throwable {
        public NoTrainException(int idTrain) {
            super("No train found with id " + idTrain);
        }
    }

}
