package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.ArretException;
import fr.miage.m1.sntp.models.Arret;
import fr.miage.m1.sntp.utils.LibSQL;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ArretDAOImpl implements ArretDAO {
    public static final String NUMERO_DE_TRAIN = "numeroDeTrain";
    public static final String DATE_DE_PASSAGE = "dateDePassage";
    public static final String QUERY_SELECT_ARRET_BY_TRAIN_NUMBER = "Select ar FROM Arret ar JOIN ar.itineraireConcerner it JOIN it.train tr WHERE tr.numeroDeTrain = :" + NUMERO_DE_TRAIN + " and ar.doitMarquerArret = 1";
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Arret findArret(long idArret) throws ArretException {
        Arret arret = LibSQL.findObject(em, Arret.class, idArret);
        if (arret == null) {
            throw new ArretException();
        }

        return arret;
    }

    @Override
    public List<Arret> getAllArret() {
        return LibSQL.findAll(em, Arret.class);
    }

    @Override
    public List<Arret> getAllArretByNumeroDeTrain(int numeroDeTrain) {
        Map<String, Object> params = new HashMap<>();
        params.put(NUMERO_DE_TRAIN, numeroDeTrain);

        return LibSQL.executeSelectWithNamedParams(em, Arret.class, QUERY_SELECT_ARRET_BY_TRAIN_NUMBER, params);
    }
}
