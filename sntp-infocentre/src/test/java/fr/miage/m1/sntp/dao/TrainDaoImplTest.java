package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.ArretException;
import fr.miage.m1.sntp.exceptions.TrainException;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;

import javax.inject.Inject;

import static org.jgroups.util.Util.assertFalse;
import static org.jgroups.util.Util.assertTrue;
import static org.wildfly.common.Assert.assertNotNull;

@QuarkusTest
public class TrainDaoImplTest {

    @Inject
    TrainDAOImpl trainDAOImpl;

    @Test
    void getAllTrainsTest() {

        assertFalse(trainDAOImpl.getAllTrains().isEmpty());
    }

}
