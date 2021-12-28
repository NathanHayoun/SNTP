package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.TrainException;
import fr.miage.m1.sntp.models.Train;
import fr.miage.m1.sntp.utils.LibSQL;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TrainDAOImpl implements TrainDAO {
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Train findTrain(long idTrain) throws TrainException {
        Train train = LibSQL.findObject(em, Train.class, idTrain);
        if (train == null) {
            throw new TrainException();
        }

        return train;
    }

    @Override
    public List<Train> getAllTrains() {
        return LibSQL.findAll(em, Train.class);
    }
}
