package fr.miage.m1.sntp.services;

import fr.miage.m1.sntp.dao.VoyageurDao;
import fr.miage.m1.sntp.models.Voyageur;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class VoyageurServiceImpl implements VoyageurService {

    @Inject
    VoyageurDao voyageurDao;

    @Override
    public List<Voyageur> getVoyageurs() {
        return voyageurDao.findAll();
    }

    @Override
    public Voyageur getVoyageur(int id) {
        return voyageurDao.findById(id);
    }
}
