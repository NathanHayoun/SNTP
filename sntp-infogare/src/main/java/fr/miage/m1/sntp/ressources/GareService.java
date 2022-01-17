package fr.miage.m1.sntp.ressources;

import fr.miage.m1.sntp.dto.GareDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/gares")
@RegisterRestClient(configKey = "gare-api")
public interface GareService {
    @Path("/gare/{id}")
    @GET
    GareDTO getGare(@PathParam("id") long id);
}










