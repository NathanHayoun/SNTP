package fr.miage.m1.sntp.ressources;

import fr.miage.m1.sntp.dto.ArretDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/arrets")
@RegisterRestClient(configKey = "infocentre-api")
public interface ArretService {
    @Path("/arret/train/{numero}")
    @GET
    List<ArretDTO> getArrets(@PathParam("numero") int numeroDeTrain);
}
