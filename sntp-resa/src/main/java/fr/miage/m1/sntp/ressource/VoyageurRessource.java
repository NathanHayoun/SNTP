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

@Path(VoyageurRessource.VOYAGEURS_PATH)
public class VoyageurRessource {
    public static final String VOYAGEURS_PATH = "/voyageurs";
    public static final String GET_VOYAGEUR_BY_ID = "/voyageur/{id}";
    public static final String VOYAGEUR_BY_EMAIL = "/voyageur/email/{email}";
    public static final String CREATE_VOYAGEUR = "/create/{nom}/{prenom}/{email}";
    public static final String NOM = "nom";
    public static final String PRENOM = "prenom";
    public static final String EMAIL = "email";
    public static final String ID = "id";
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
    @Path(GET_VOYAGEUR_BY_ID)
    public Voyageur getVoyageur(@PathParam(ID) int id) {
        return service.getVoyageur(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(VOYAGEUR_BY_EMAIL)
    public Boolean getVoyageurByEmail(@PathParam(EMAIL) String email) throws VoyageurException {
        return service.getVoyageurByEmail(email) != null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(CREATE_VOYAGEUR)
    public Voyageur createVoyageur(
            @PathParam(NOM) String nom,
            @PathParam(PRENOM) String prenom,
            @PathParam(EMAIL) String email
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
