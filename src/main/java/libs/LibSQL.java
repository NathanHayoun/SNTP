package libs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Library that allows easy access to the database
 */
public class LibSQL {
    private static EntityManager entityManager;
    private final Logger logger = LoggerFactory.getLogger(LibSQL.class);

    public LibSQL() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        entityManager = emf.createEntityManager();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Close connection");
            entityManager.close();
        }));
    }

    /**
     * Insert object in Database
     *
     * @param object to insert
     */
    public void insertObject(Object object) {
        entityManager.getTransaction().begin();
        boolean transactionIsOk = false;
        try {
            entityManager.persist(object);
            transactionIsOk = true;
        } catch (Exception e) {
            logger.warn("Error during insert object", e);
        } finally {
            if (transactionIsOk) {
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().rollback();
            }
        }
    }

    /**
     * Find object by primary key
     * Don't forget to cast your result !
     */
    public Object findObject(Class classToFind, Object key) {
        return entityManager.find(classToFind, key);
    }

    /**
     * Delete object in parameter
     *
     * @param object to delete
     */
    public void deleteObject(Object object) {
        boolean transactionIsOk = false;
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(object);
            transactionIsOk = true;
        } catch (Exception e) {
            logger.warn("Error during insert object", e);
        } finally {
            if (transactionIsOk) {
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().rollback();
            }
        }
    }

    public void executeQuery(String query, Class classForQuery) {
        entityManager.createQuery(query, classForQuery).executeUpdate();
    }

    public void executeQueryWithNamedParams(String query, Map<String, Object> params) {
        Query queryToPush = entityManager.createQuery(query);
        for (Entry<String, Object> param : params.entrySet()) {
            queryToPush.setParameter(param.getKey(), param.getValue());
        }
        queryToPush.executeUpdate();
    }

    public void executeQueryWithNoNamedParams(String query, List<Object> params) {
        Query queryToPush = entityManager.createQuery(query);
        for (int i = 0; i < params.size(); i++) {
            queryToPush.setParameter(i, params.get(i));
        }
        queryToPush.executeUpdate();
    }

    public List executeSelect(String query, Class className) {
        return entityManager.createQuery(query, className).getResultList();
    }

    public List executeSelectWithNamedParams(String query, List<Object> params) {
        Query queryToPush = entityManager.createQuery(query);
        for (int i = 0; i < params.size(); i++) {
            queryToPush.setParameter(i, params.get(i));
        }
        return queryToPush.getResultList();
    }

    public List executeSelectWithNoNamedParams(String query, List<Object> params) {
        Query queryToPush = entityManager.createQuery(query);
        for (int i = 0; i < params.size(); i++) {
            queryToPush.setParameter(i, params.get(i));
        }
        return queryToPush.getResultList();
    }
}
