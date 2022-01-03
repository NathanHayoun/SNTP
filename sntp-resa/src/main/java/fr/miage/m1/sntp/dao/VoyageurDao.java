package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.models.Voyageur;

import java.util.List;

public interface VoyageurDao {
    List<Voyageur> findAll();
}
