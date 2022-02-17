package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.ArretException;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.jgroups.util.Util.assertTrue;
import static org.wildfly.common.Assert.assertNotNull;

@QuarkusTest
public class ArretDaoImplTest {

    @Inject
    ArretDAOImpl arretDaoImpl;

    @Test
    void getAllArretByNumeroDeTrainTest() throws ArretException {
        assertNotNull(arretDaoImpl.getAllArretByNumeroDeTrain(6103));
    }

    @Test
    void getAllArretByNumeroDeTrainTestKO() throws ArretException {
        assertTrue(arretDaoImpl.getAllArretByNumeroDeTrain(0).isEmpty());
    }

    @Test
    void getArretsDepartByGareTest() throws ArretException {
        assertNotNull(arretDaoImpl.getArretsDepartByGare(1L));
    }

    @Test
    void getArretsDepartByGareTestKO() throws ArretException {
        assertTrue(arretDaoImpl.getArretsDepartByGare(0).isEmpty());
    }

    @Test
    void getArretsArriveeByGareTest() throws ArretException {
        assertNotNull(arretDaoImpl.getArretsArriveeByGare(1L));
    }

    @Test
    void getArretsArriveeByGareTestKO() throws ArretException {
        assertTrue(arretDaoImpl.getArretsArriveeByGare(0).isEmpty());
    }
}
