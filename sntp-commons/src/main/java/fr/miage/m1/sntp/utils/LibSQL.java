package fr.miage.m1.sntp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class LibSQL {
    private static final Logger logger = LoggerFactory.getLogger(LibSQL.class);

    private LibSQL() {

    }

    /**
     * Insert object in Database
     *
     * @param object to insert
     */
    @Transactional
    public static void insertObject(EntityManager entityManager, Object object) {
        entityManager.persist(object);
    }

    /**
     * Delete object in parameter
     *
     * @param line          to delete
     * @param entityManager from class
     */
    public static void deleteObject(EntityManager entityManager, Object line) {
        boolean transactionIsOk = false;
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(line);
            transactionIsOk = true;
        } catch (Exception exception) {
            logger.warn("Error during delete object " + line, exception);
        } finally {
            if (transactionIsOk) {
                entityManager.getTransaction().commit();
                logger.info("Delete ok for object " + line);
            } else {
                entityManager.getTransaction().rollback();
                logger.warn("Transaction rollback for object " + line);
            }
        }
    }

    /**
     * @param entityManager
     * @param className     of Object concerned by the request
     * @param query
     */
    public static void executeQuery(EntityManager entityManager, Class className, String query) {
        entityManager.createQuery(query, className).executeUpdate();
    }

    public static void executeQueryWithNamedParams(EntityManager entityManager, String query, Map<String, Object> params) {
        Query queryToPush = entityManager.createQuery(query);

        for (Entry<String, Object> param : params.entrySet()) {
            queryToPush.setParameter(param.getKey(), param.getValue());
        }
        queryToPush.executeUpdate();
    }

    public static void executeQueryWithNoNamedParams(EntityManager entityManager, String query, List<Object> params) {
        Query queryToPush = entityManager.createQuery(query);

        for (int i = 0; i < params.size(); i++) {
            queryToPush.setParameter(i, params.get(i));
        }
        queryToPush.executeUpdate();
    }

    public static <E> List<E> executeSelect(EntityManager entityManager, Class className, String query) {
        return (List<E>) entityManager.createQuery(query, className).getResultList();
    }

    /**
     * Find object by primary key
     * Don't forget to cast your result !
     */
    public static <E> E findObject(EntityManager entityManager, Class className, Object key) {
        E ligne = (E) entityManager.find(className, key);

        if (ligne == null) {
            logger.warn("No result found for " + className.getName());
        }
        return ligne;
    }

    public static <E> List<E> executeSelectWithNamedParams(EntityManager entityManager, Class className, String query, Map<String, Object> params) {
        Query queryToPush = entityManager.createQuery(query, className);

        for (Entry<String, Object> param : params.entrySet()) {
            queryToPush.setParameter(param.getKey(), param.getValue());
        }
        List<E> results = queryToPush.getResultList();

        if (results == null) {
            logger.warn("No result found for " + query);
        }
        return results;
    }

    public static <E> List<E> executeSelectWithNoNamedParams(EntityManager entityManager, Class className, String query, List<Object> params) {
        Query queryToPush = entityManager.createQuery(query, className);

        for (int i = 0; i < params.size(); i++) {
            queryToPush.setParameter(i, params.get(i));
        }
        List<E> results = queryToPush.getResultList();

        if (results == null) {
            logger.warn("No result found for " + query);
        }
        return results;
    }

    public static <E> List<E> findAll(EntityManager entityManager, Class className) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(className);
        Root<E> rootEntry = cq.from(className);
        CriteriaQuery<E> all = cq.select(rootEntry);
        TypedQuery<E> allQuery = entityManager.createQuery(all);
        List<E> results = allQuery.getResultList();

        if (results == null) {
            logger.warn("No result found for " + className.getName());
        }
        return results;
    }

    @Transactional
    public static <E> void update(EntityManager entityManager, E objectToSave) {
        entityManager.merge(objectToSave);
    }
}
