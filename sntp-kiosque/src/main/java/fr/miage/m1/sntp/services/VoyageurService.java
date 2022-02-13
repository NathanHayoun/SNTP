package fr.miage.m1.sntp.services;


import fr.miage.m1.sntp.dto.VoyageurDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@RegisterRestClient(configKey = "resa-api")
public interface VoyageurService {

    @Path("voyageurs/voyageur/email/{email}")
    @GET
    Boolean getVoyageurByEmail(@PathParam("email") String email);

    @Path("voyageurs/create/{nom}/{prenom}/{email}")
    @GET
    VoyageurDTO createVoyageur(
            @PathParam("nom") String nom,
            @PathParam("prenom") String prenom,
            @PathParam("email") String email);
}
