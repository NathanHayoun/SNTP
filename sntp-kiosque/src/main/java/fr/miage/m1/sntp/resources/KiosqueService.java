package fr.miage.m1.sntp.resources;


import fr.miage.m1.sntp.dto.VoyageDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.jaxrs.PathParam;


import java.util.Collection;


@RegisterRestClient(configKey = "resa-api")
public interface KiosqueService {

    @Path("kiosque/voyages/{villeDepart}/{villeArrive}/{date}")
    @GET
    Collection<VoyageDTO> getVoyages(@PathParam("villeDepart") String villeDepart, @PathParam("villeArrive") String villeArrive, @PathParam("date") String date);
}
