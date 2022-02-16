package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.LigneDeTrainException;
import fr.miage.m1.sntp.models.LigneDeTrain;

import java.util.List;

public interface LigneDeTrainDAO {
    LigneDeTrain findLigneDeTrain(long idLigneDeTrain) throws LigneDeTrainException;

    List<LigneDeTrain> getAllLigneDeTrains();
}
