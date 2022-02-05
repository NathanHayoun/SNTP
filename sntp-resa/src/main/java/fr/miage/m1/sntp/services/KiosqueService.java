package fr.miage.m1.sntp.services;

import fr.miage.m1.sntp.dto.VoyageDTO;

import java.util.Collection;

public interface KiosqueService {
    Collection<VoyageDTO> getVoyages(String villeDepart, String villeArrive, String dateDepart);
}
