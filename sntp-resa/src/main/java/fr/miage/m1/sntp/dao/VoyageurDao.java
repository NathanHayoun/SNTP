package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.VoyageurException;
import fr.miage.m1.sntp.models.Voyageur;

import java.util.List;

public interface VoyageurDao {
    List<Voyageur> findAll();
    Voyageur findById(int id) throws VoyageurException;
    Voyageur findByEmail(String email) throws VoyageurException;
    void save(Voyageur voyageur);
    void delete(Voyageur voyageur);
    void update(Voyageur voyageur);
    Voyageur createNewVoyageur(String nom, String prenom, String email);
}

