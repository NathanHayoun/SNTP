package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.PassageException;
import fr.miage.m1.sntp.models.Passage;
import fr.miage.m1.sntp.utils.LibSQL;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class PassageDAOImpl implements PassageDAO {
    public static final String DATE_DE_PASSAGE = "dateDePassage";
    private static final String ID_GARE = "idGare";
    public static final String QUERY_SELECT_10_PROCHAIN_PASSAGE_DU_JOUR_BY_ID_GARE_DEPART = "SELECT pa FROM Passage pa JOIN pa.arret ar JOIN ar.gareConcerner ga WHERE pa.dateDePassage = :" + DATE_DE_PASSAGE + " and ga.id = :" + ID_GARE + " AND pa.heureDepartReel != null order by pa.heureDepartReel";
    public static final String QUERY_SELECT_10_PROCHAIN_PASSAGE_DU_JOUR_BY_ID_GARE_ARRIVEE = "SELECT pa FROM Passage pa JOIN pa.arret ar JOIN ar.gareConcerner ga WHERE pa.dateDePassage = :" + DATE_DE_PASSAGE + " and ga.id = :" + ID_GARE + " AND pa.heureArriveeReel != null order by pa.heureArriveeReel";

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Passage findPassage(long idPassage) throws PassageException {
        Passage passage = LibSQL.findObject(em, Passage.class, idPassage);
        if (passage == null) {
            throw new PassageException();
        }

        return passage;
    }

    @Override
    public List<Passage> getAllPassages() {
        return LibSQL.findAll(em, Passage.class);
    }

    @Override
    @Transactional
    public void insertPassage(Passage passage) {
        LibSQL.insertObject(em, passage);
    }

    @Override
    @Transactional
    public void updatePassage(Passage passage) {
        LibSQL.updateObject(em, passage);
    }

    @Override
    public List<Passage> findprochainsTrajetsDuJourByGareDepart(long idGare) throws PassageException {
        return getPassages(idGare, QUERY_SELECT_10_PROCHAIN_PASSAGE_DU_JOUR_BY_ID_GARE_DEPART);
    }

    @Override
    public List<Passage> findprochainsTrajetsDuJourByGareArrivee(long idGare) throws PassageException {
        return getPassages(idGare, QUERY_SELECT_10_PROCHAIN_PASSAGE_DU_JOUR_BY_ID_GARE_ARRIVEE);
    }

    private List<Passage> getPassages(long idGare, String querySelect10ProchainPassageDuJourByIdGareArrivee) {
        Map<String, Object> params = new HashMap<>();
        params.put(ID_GARE, idGare);
        LocalDate date = LocalDate.now();
        params.put(DATE_DE_PASSAGE, date);
        return LibSQL.executeSelectWithNamedParams(em, Object[].class, querySelect10ProchainPassageDuJourByIdGareArrivee, params);
    }

}
