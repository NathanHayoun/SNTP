package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.PassageException;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static org.jgroups.util.Util.assertEquals;
import static org.jgroups.util.Util.assertFalse;
import javax.inject.Inject;

import static org.jgroups.util.Util.*;

@QuarkusTest
public class PassageDaoImplTest {

    @Inject
    PassageDAOImpl passageDAOImpl;

    @Test
    void getAllPassagesTest() {
        assertFalse(passageDAOImpl.getAllPassages().isEmpty());
    }

    @Test
    void findPassageTest() throws PassageException {
        var item = passageDAOImpl.findPassage(1);
        assertEquals(1L, item.getIdPassage());
    }


    @Test
    void findprochainsTrajetsDuJourByGareDepartTest() throws PassageException {
        var item = passageDAOImpl.findprochainsTrajetsDuJourByGareDepart(1);
        assertFalse(item.isEmpty());
    }

    @Test
    void findprochainsTrajetsDuJourByGareArriveeTest() throws PassageException {
        var item = passageDAOImpl.findprochainsTrajetsDuJourByGareArrivee(1);
        assertFalse(item.isEmpty());
    }
}
