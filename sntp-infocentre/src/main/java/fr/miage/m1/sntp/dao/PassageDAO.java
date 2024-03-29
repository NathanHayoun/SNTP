package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.PassageException;
import fr.miage.m1.sntp.models.Passage;

import java.util.List;

public interface PassageDAO {
    Passage findPassage(long idPassage) throws PassageException;

    List<Passage> getAllPassages();

    void insertPassage(Passage passage);

    List<Passage> findprochainsTrajetsDuJourByGareDepart(long idGare) throws PassageException;

    List<Passage> findprochainsTrajetsDuJourByGareArrivee(long idGare) throws PassageException;

    void updatePassage(Passage passage);
}
