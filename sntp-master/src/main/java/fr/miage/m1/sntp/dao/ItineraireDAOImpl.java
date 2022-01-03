package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.ItineraireException;
import fr.miage.m1.sntp.models.Itineraire;
import fr.miage.m1.sntp.utils.LibSQL;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ItineraireDAOImpl implements ItineraireDAO {
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Itineraire findItineraire(long idItineraire) throws ItineraireException {
        Itineraire itineraire = LibSQL.findObject(em, Itineraire.class, idItineraire);
        if (itineraire == null) {
            throw new ItineraireException();
        }

        return itineraire;
    }

    @Override
    public List<Itineraire> getAllItineraires() {
        return LibSQL.findAll(em, Itineraire.class);
    }
}
