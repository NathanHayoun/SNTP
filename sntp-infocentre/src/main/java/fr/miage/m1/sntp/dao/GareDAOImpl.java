package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.GareException;
import fr.miage.m1.sntp.models.Gare;
import fr.miage.m1.sntp.utils.LibSql;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class GareDAOImpl implements GareDAO {
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Gare findGare(long idGare) throws GareException {
        Gare gare = LibSql.findObject(em, Gare.class, idGare);
        if (gare == null) {
            throw new GareException();
        }

        return gare;
    }

    @Override
    public List<Gare> getAllGare() {
        return LibSql.findAll(em, Gare.class);
    }
}
