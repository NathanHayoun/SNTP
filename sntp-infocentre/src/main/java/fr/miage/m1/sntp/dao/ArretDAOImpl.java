package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.ArretException;
import fr.miage.m1.sntp.models.Arret;
import fr.miage.m1.sntp.models.Passage;
import fr.miage.m1.sntp.utils.LibSQL;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@ApplicationScoped
public class ArretDAOImpl implements ArretDAO {
    public static final String NUMERO_DE_TRAIN = "numeroDeTrain";
    public static final String DATE_DE_PASSAGE = "dateDePassage";
    public static final String QUERY_SELECT_ARRET_BY_TRAIN_NUMBER = "Select ar FROM Arret ar JOIN ar.itineraireConcerner it JOIN it.train tr WHERE tr.numeroDeTrain = :" + NUMERO_DE_TRAIN + " and ar.doitMarquerArret = 1 order by ar.position";
    private static final String ID_GARE = "idGare";
    public static final String QUERY_SELECT_DEPART_BY_ID_GARE = "Select ar FROM Arret ar JOIN ar.gareConcerner ga JOIN ar.passages pa WHERE ar.heureDepart != null and ga.id = :" + ID_GARE + " and ar.doitMarquerArret = 1 and pa.dateDePassage = :" + DATE_DE_PASSAGE + " order by ar.heureDepart, ar.position";
    public static final String QUERY_SELECT_ARRIVEE_BY_ID_GARE = "Select ar FROM Arret ar JOIN ar.gareConcerner ga JOIN ar.passages pa WHERE ar.heureArrivee != null and ga.id = :" + ID_GARE + " and ar.doitMarquerArret = 1 and pa.dateDePassage = :" + DATE_DE_PASSAGE + " order by ar.heureArrivee, ar.position";

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
        return LibSQL.executeSelectWithNamedParams(em, Arret.class, QUERY_SELECT_ARRET_BY_TRAIN_NUMBER, getIdParamsWithOneParameters(numeroDeTrain, NUMERO_DE_TRAIN));
    }

    @Override
    public List<Arret> getArretsDepartByGare(long idGare) throws ArretException {
        return getArrets(idGare, QUERY_SELECT_DEPART_BY_ID_GARE);
    }

    @Override
    public List<Arret> getArretsArriveeByGare(long idGare) throws ArretException {
        return getArrets(idGare, QUERY_SELECT_ARRIVEE_BY_ID_GARE);
    }

    @NotNull
    private List<Arret> getArrets(long idGare, String querySelectArriveeByIdGare) {
        Map<String, Object> params = new HashMap<>();
        params.put(ID_GARE, idGare);
        params.put(DATE_DE_PASSAGE, LocalDate.now());
        List<Arret> arrets = LibSQL.executeSelectWithNamedParams(em, Arret.class, querySelectArriveeByIdGare, params);
        List<Arret> arretToReturn = new ArrayList<>();
        for (Arret arret : arrets) {
            Arret arretToPush = new Arret();
            arretToPush.setDoitMarquerArret(arret.getDoitMarquerArret());
            arretToPush.setGareConcerner(arret.getGareConcerner());
            arretToPush.setHeureArrivee(arret.getHeureArrivee());
            arretToPush.setHeureDepart(arret.getHeureDepart());
            arretToPush.setId(arret.getId());
            arretToPush.setItineraireConcerner(arret.getItineraireConcerner());
            arretToPush.setPosition(arret.getPosition());
            Set<Passage> passageSet = new HashSet<>();
            for (Passage passage : arret.getPassages()) {
                if (Objects.equals(passage.getDateDePassage(), LocalDate.now())) {
                    passageSet.add(passage);
                    arretToPush.setPassageDuJour(passage);
                }
            }
            arretToPush.setPassages(passageSet);
            arretToReturn.add(arretToPush);
        }
        return arretToReturn;
    }

    @NotNull
    private Map<String, Object> getIdParamsWithOneParameters(long value, String key) {
        Map<String, Object> params = new HashMap<>();
        params.put(key, value);
        return params;
    }
}
