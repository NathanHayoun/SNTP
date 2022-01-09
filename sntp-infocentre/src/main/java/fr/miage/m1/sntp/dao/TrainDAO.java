package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.TrainException;
import fr.miage.m1.sntp.models.Train;

import java.util.List;

public interface TrainDAO {
    Train findTrain(long idTrain) throws TrainException;

    List<Train> getAllTrains();

    void update(Train train);
}
