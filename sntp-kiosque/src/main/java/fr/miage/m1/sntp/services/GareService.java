package fr.miage.m1.sntp.services;


import fr.miage.m1.sntp.dto.GareDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Collection;


@RegisterRestClient(configKey = "infocentre-api")
public interface GareService {

    @Path("gares")
    @GET
    Collection<GareDTO> getGares();
}
