package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.PassageException;
import fr.miage.m1.sntp.models.Passage;
import fr.miage.m1.sntp.utils.LibSQL;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PassageDAOImpl implements PassageDAO {
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
}
