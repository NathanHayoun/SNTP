package fr.miage.m1.sntp.services;


import fr.miage.m1.sntp.dto.VoyageurDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author Quentin Vaillant
 */
@RegisterRestClient(configKey = VoyageurService.RESA_API_KEY)
public interface VoyageurService {

    String PATH_GET_VOYAGEUR_BY_EMAIL = "voyageurs/voyageur/email/{email}";
    String PATH_CREATE_VOYAGEUR = "voyageurs/create/{nom}/{prenom}/{email}";
    String EMAIL = "email";
    String PRENOM = "prenom";
    String NOM = "nom";
    String RESA_API_KEY = "resa-api";

    @Path(PATH_GET_VOYAGEUR_BY_EMAIL)
    @GET
    Boolean getVoyageurByEmail(@PathParam(EMAIL) String email);

    @Path(PATH_CREATE_VOYAGEUR)
    @GET
    VoyageurDTO createVoyageur(
            @PathParam(NOM) String nom,
            @PathParam(PRENOM) String prenom,
            @PathParam(EMAIL) String email);
}
