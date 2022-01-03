package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.ItineraireException;
import fr.miage.m1.sntp.models.Itineraire;

import java.util.List;

public interface ItineraireDAO {
    Itineraire findItineraire(long idItineraire) throws ItineraireException;

    List<Itineraire> getAllItineraires();
}
