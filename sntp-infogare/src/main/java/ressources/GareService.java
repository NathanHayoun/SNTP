package ressources;

import fr.miage.m1.sntp.dto.GareDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/gares")
@RegisterRestClient(configKey = "gare-api")
public interface GareService {
    @Path("/gare/{id}")
    @GET
    GareDTO getGare(@PathParam("id") long id);
}










