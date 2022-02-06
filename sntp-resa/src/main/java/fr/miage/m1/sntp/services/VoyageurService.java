package fr.miage.m1.sntp.services;

import fr.miage.m1.sntp.models.Voyageur;

import java.util.List;

public interface VoyageurService {
    List<Voyageur> getVoyageurs();

    Voyageur getVoyageur(int id);
}
