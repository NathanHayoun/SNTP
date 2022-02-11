package fr.miage.m1.sntp.ressource;

import fr.miage.m1.sntp.dao.VoyageurDao;
import fr.miage.m1.sntp.exceptions.VoyageurException;
import fr.miage.m1.sntp.models.Voyageur;
import fr.miage.m1.sntp.services.VoyageurService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/voyageurs")
public class VoyageurRessource {
    @Inject
    VoyageurService service;

    @Inject
    VoyageurDao voyageurDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Voyageur> getVoyageurs() {
        return service.getVoyageurs();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/voyageur/{id}")
    public Voyageur getVoyageur(@PathParam("id") int id) {
        return service.getVoyageur(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/voyageur/email/{email}")
    public Boolean getVoyageurByEmail(@PathParam("email") String email) throws VoyageurException {
        return service.getVoyageurByEmail(email) != null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create/{nom}/{prenom}/{email}")
    public Voyageur createVoyageur(
            @PathParam("nom") String nom,
            @PathParam("prenom") String prenom,
            @PathParam("email") String email
    ) throws VoyageurException {
        Voyageur voyageur = voyageurDao.findByEmail(email);

        if (voyageur == null) {
            voyageur = new Voyageur();
            voyageur.setEmail(email);
            voyageur.setNom(nom);
            voyageur.setPrenom(prenom);
            service.createVoyageur(voyageur);
        }

        return voyageur;
    }

}
