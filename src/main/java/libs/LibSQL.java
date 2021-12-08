package libs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Library that allows easy access to the database
 */
public class LibSQL<E> {
    @PersistenceContext(name = "persistence")
    private static EntityManager entityManager;
    private final Logger logger = LoggerFactory.getLogger(LibSQL.class);
    private E e;

    public LibSQL() {
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
        } catch (Exception exception) {
            logger.warn("Error during insert object", exception);
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
    public E findObject(Object key) {
        return (E) entityManager.find(e.getClass(), key);
    }

    /**
     * Delete object in parameter
     *
     * @param line to delete
     */
    public void deleteObject(E line) {
        boolean transactionIsOk = false;
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(line);
            transactionIsOk = true;
        } catch (Exception exception) {
            logger.warn("Error during insert object", exception);
        } finally {
            if (transactionIsOk) {
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().rollback();
            }
        }
    }

    public void executeQuery(String query) {
        entityManager.createQuery(query, e.getClass()).executeUpdate();
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

    public List<E> executeSelect(String query) {
        return (List<E>) entityManager.createQuery(query, e.getClass()).getResultList();
    }

    public List<E> executeSelectWithNamedParams(String query, Map<String, Object> params) {
        Query queryToPush = entityManager.createQuery(query, e.getClass());

        for (Entry<String, Object> param : params.entrySet()) {
            queryToPush.setParameter(param.getKey(), param.getValue());
        }
        return queryToPush.getResultList();
    }

    public List<E> executeSelectWithNoNamedParams(String query, List<Object> params) {
        Query queryToPush = entityManager.createQuery(query, e.getClass());
        for (int i = 0; i < params.size(); i++) {
            queryToPush.setParameter(i, params.get(i));
        }
        return queryToPush.getResultList();
    }
}
