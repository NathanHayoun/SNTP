package fr.miage.m1.dao;

import fr.miage.m1.libs.LibSQL;
import fr.miage.m1.models.TrainType;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class TrainTypeDao {

    private final LibSQL<TrainType> libSQL = new LibSQL<>(TrainType.class);
    @PersistenceContext(name = "mysql")
    private EntityManager entityManager;

    public void insertTrain(TrainType e) {
        libSQL.insertObject(entityManager, e);
    }
}
