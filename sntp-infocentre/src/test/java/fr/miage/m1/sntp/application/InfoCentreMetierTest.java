package fr.miage.m1.sntp.application;

import fr.miage.m1.sntp.dao.ArretDAOImpl;
import fr.miage.m1.sntp.exceptions.ArretException;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static fr.miage.m1.sntp.application.InfoCentreMetier.OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.jgroups.util.Util.*;
import static org.wildfly.common.Assert.assertNotNull;

@QuarkusTest
public class InfoCentreMetierTest {

    @Inject
    InfoCentreMetier infoCentreMetier;

    @Test
    void genererRetard() {

    }

    @Test
    void verificationPourRetard() {
    }

    @Test
    void supprimerStation() {
    }

    @Test
    void ajouterStation() {
    }

    @Test
    void supprimerTrain() {
    }

    @Test
    void genererPassagesDuJour() {
    }
}
