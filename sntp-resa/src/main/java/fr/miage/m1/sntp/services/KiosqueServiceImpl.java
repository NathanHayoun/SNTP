package fr.miage.m1.sntp.services;

import fr.miage.m1.sntp.dto.VoyageDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

@ApplicationScoped
public class KiosqueServiceImpl implements KiosqueService {

    @Override
    public Collection<VoyageDTO> getVoyages(String villeDepart, String villeArrive, String dateDepart) {
        Collection<VoyageDTO> voyages = new LinkedList<>();
        return voyages;
    }
}
