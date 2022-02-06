package fr.miage.m1.sntp.resources;


import fr.miage.m1.sntp.dto.VoyageDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.jboss.resteasy.annotations.jaxrs.PathParam;


import java.util.Collection;


@Path("/kiosque")
@RegisterRestClient(configKey = "kiosque-api")
public interface KiosqueService {

    @GET
    @Path("voyages/{villeDepart}/{villeArrive}/{date}")
    Collection<VoyageDTO> getVoyages(@PathParam("villeDepart") String villeDepart, @PathParam("villeArrive") String villeArrive, @PathParam("date") String date);
}
