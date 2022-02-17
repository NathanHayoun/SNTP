package fr.miage.m1.sntp.utils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Library that allows easy access to the database
 */
public class LibSql {

    public static final String UTILITY_CLASS = "classe utilitaire";

    private LibSql() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    /**
     * Insert object in Database
     *
     * @param object to insert
     */
    @Transactional
    public static Object insertObject(EntityManager entityManager, Object object) {
        entityManager.persist(object);

        return object;
    }

    /**
     * Update object in Database
     *
     * @param object to insert
     */
    @Transactional
    public static void updateObject(EntityManager entityManager, Object object) {
        entityManager.merge(object);
    }

    /**
     * Delete object in parameter
     *
     * @param line          to delete
     * @param entityManager from class
     */
    public static void deleteObject(EntityManager entityManager, Object line) {
        entityManager.remove(entityManager.contains(line) ? line : entityManager.merge(line));
    }

    /**
     * Find object by primary key
     * Don't forget to cast your result !
     */
    public static <E> E findObject(EntityManager entityManager, Class<?> className, Object key) {
        return (E) entityManager.find(className, key);
    }

    public static <E> List<E> executeSelectWithNamedParams(EntityManager entityManager, Class<?> className, String query, Map<String, Object> params) {
        Query queryToPush = entityManager.createQuery(query, className);

        for (Entry<String, Object> param : params.entrySet()) {
            queryToPush.setParameter(param.getKey(), param.getValue());
        }

        return queryToPush.getResultList();
    }

    public static <E> List<E> findAll(EntityManager entityManager, Class<?> className) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = (CriteriaQuery<E>) cb.createQuery(className);
        Root<E> rootEntry = (Root<E>) cq.from(className);
        CriteriaQuery<E> all = cq.select(rootEntry);
        TypedQuery<E> allQuery = entityManager.createQuery(all);

        return allQuery.getResultList();
    }

    @Transactional
    public static <E> void update(EntityManager entityManager, E objectToSave) {
        entityManager.merge(objectToSave);
    }
}
