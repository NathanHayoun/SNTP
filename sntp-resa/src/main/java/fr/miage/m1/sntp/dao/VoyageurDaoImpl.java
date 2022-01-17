package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.VoyageurException;
import fr.miage.m1.sntp.models.Voyageur;
import fr.miage.m1.sntp.utils.LibSQL;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class VoyageurDaoImpl implements VoyageurDao{
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Voyageur> findAll() {
        return LibSQL.findAll(entityManager,Voyageur.class);
    }

    @Override
    @Transactional
    public Voyageur findById(int id) throws VoyageurException {
        return LibSQL.findObject(entityManager,Voyageur.class,id);
    }

    @Override
    @Transactional
    public void save(Voyageur voyageur) {
        LibSQL.insertObject(entityManager,voyageur);
    }

    @Override
    @Transactional
    public void update(Voyageur voyageur) {
        LibSQL.update(entityManager,voyageur);
    }

    @Override
    @Transactional
    public void delete(Voyageur voyageur) {
        LibSQL.deleteObject(entityManager,voyageur);
    }
}