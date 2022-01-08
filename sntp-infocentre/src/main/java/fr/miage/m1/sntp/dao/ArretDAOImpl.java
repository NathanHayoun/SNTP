package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.ArretException;
import fr.miage.m1.sntp.models.Arret;
import fr.miage.m1.sntp.utils.LibSQL;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ArretDAOImpl implements ArretDAO {
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
}
