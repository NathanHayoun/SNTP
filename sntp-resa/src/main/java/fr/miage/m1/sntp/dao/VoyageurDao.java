package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.models.Voyageur;

import java.util.List;

public interface VoyageurDao {
    List<Voyageur> findAll();
    Voyageur findById(int id);
    void save(Voyageur voyageur);
    void delete(Voyageur voyageur);
    void update(Voyageur voyageur);
}

