package fr.miage.m1.sntp.dao;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.jgroups.util.Util.assertFalse;

@QuarkusTest
public class TrainDaoImplTest {

    @Inject
    TrainDAOImpl trainDAOImpl;

    @Test
    void getAllTrainsTest() {

        assertFalse(trainDAOImpl.getAllTrains().isEmpty());
    }

}
