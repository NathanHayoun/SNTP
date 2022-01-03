package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.LigneDeTrainException;
import fr.miage.m1.sntp.models.LigneDeTrain;
import fr.miage.m1.sntp.utils.LibSQL;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class LigneDeTrainDAOImpl implements LigneDeTrainDAO {
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public LigneDeTrain findLigneDeTrain(long idLigneDeTrain) throws LigneDeTrainException {
        LigneDeTrain ligneDeTrain = LibSQL.findObject(em, LigneDeTrain.class, idLigneDeTrain);
        if (ligneDeTrain == null) {
            throw new LigneDeTrainException();
        }

        return ligneDeTrain;
    }

    @Override
    public List<LigneDeTrain> getAllLigneDeTrains() {
        return LibSQL.findAll(em, LigneDeTrain.class);
    }
}
