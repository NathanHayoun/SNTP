package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.ItineraireException;
import fr.miage.m1.sntp.models.Arret;
import fr.miage.m1.sntp.models.Itineraire;
import fr.miage.m1.sntp.utils.LibSql;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ItineraireDAOImpl implements ItineraireDAO {
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Itineraire findItineraire(long idItineraire) throws ItineraireException {
        Itineraire itineraire = LibSql.findObject(em, Itineraire.class, idItineraire);
        if (itineraire == null) {
            throw new ItineraireException();
        }

        return itineraire;
    }

    @Override
    public List<Itineraire> getAllItineraires() {
        List<Itineraire> itineraires = LibSql.findAll(em, Itineraire.class);
        for (Itineraire itineraire : itineraires) {
            itineraire.setArrets(itineraire.getArrets().stream().sorted(Comparator.comparing(Arret::getPosition)).collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        
        return itineraires;

    }
}
