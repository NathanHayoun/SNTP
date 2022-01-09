package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.GareException;
import fr.miage.m1.sntp.models.Gare;

import java.util.List;

public interface GareDAO {

    Gare findGare(long idGare) throws GareException;

    List<Gare> getAllGare();
}
