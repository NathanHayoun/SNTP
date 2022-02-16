package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.VoyageurException;
import fr.miage.m1.sntp.models.Voyageur;
import fr.miage.m1.sntp.utils.LibSql;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class VoyageurDaoImpl implements VoyageurDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Voyageur> findAll() {
        return LibSql.findAll(entityManager, Voyageur.class);
    }

    @Override
    @Transactional
    public Voyageur findById(int id) {
        return LibSql.findObject(entityManager, Voyageur.class, id);
    }

    @Override
    public Voyageur findByEmail(String email) throws VoyageurException {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("email", email);
            List<Voyageur> voyageurList = LibSql.executeSelectWithNamedParams(entityManager, Voyageur.class, "from Voyageur where email = :email", params);
            if (voyageurList.size() == 0) {
                return null;
            }
            return (voyageurList.get(0));
        } catch (NoResultException e) {
            throw new VoyageurException();
        }
    }

    @Override
    @Transactional
    public void save(Voyageur voyageur) {
        LibSql.insertObject(entityManager, voyageur);
    }

    @Override
    @Transactional
    public void update(Voyageur voyageur) {
        LibSql.update(entityManager, voyageur);
    }

    @Override
    @Transactional
    public void delete(Voyageur voyageur) {
        LibSql.deleteObject(entityManager, voyageur);
    }

    @Override
    @Transactional
    public Voyageur createNewVoyageur(String nom, String prenom, String email) {
        Voyageur v = new Voyageur(nom, prenom, email);
        entityManager.persist(v);
        return v;
    }

}