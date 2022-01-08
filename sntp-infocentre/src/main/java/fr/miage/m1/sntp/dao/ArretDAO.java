package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.ArretException;
import fr.miage.m1.sntp.models.Arret;

import java.util.List;

public interface ArretDAO {
    Arret findArret(long idArret)  throws ArretException;

    List<Arret> getAllArret();
}
