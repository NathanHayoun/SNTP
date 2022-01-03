package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.models.Voyageur;
import fr.miage.m1.sntp.utils.LibSQL;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class VoyageurDaoImpl implements VoyageurDao{
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Voyageur> findAll() {
        return LibSQL.findAll(entityManager,Voyageur.class);

    }

    @Override
    public Voyageur findById(int id) {
        return LibSQL.findObject(entityManager,Voyageur.class,id);
    }

    @Override
    public void save(Voyageur voyageur) {
        LibSQL.insertObject(entityManager,voyageur);
    }

    @Override
    public void update(Voyageur voyageur) {
        //NOP
    }

    @Override
    public void delete(Voyageur voyageur) {
        LibSQL.deleteObject(entityManager,voyageur);
    }

}