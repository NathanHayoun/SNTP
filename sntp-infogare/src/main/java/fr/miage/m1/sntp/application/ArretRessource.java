package fr.miage.m1.sntp.application;

import fr.miage.m1.sntp.dto.ArretDTO;
import fr.miage.m1.sntp.ressources.ArretService;
import fr.miage.m1.sntp.ressources.GareService;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/main")
public class ArretRessource {
    private static final Integer idGare = 1;

    @Inject
    Template departs;

    @Inject
    Template arrivees;

    @Inject
    @RestClient
    GareService gareService;

    @Inject
    @RestClient
    ArretService arretService;

    @Path("/departs")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getDeparts(@QueryParam("name") String name) {
        List<ArretDTO> arrets = arretService.getArretsDepartByGare(idGare);

        return departs.data("arrets", arrets);
    }

    @Path("/arrivees")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getArrivees(@QueryParam("name") String name) {
        List<ArretDTO> arrets = arretService.getArretsArriveeByGare(idGare);
        return arrivees.data("arrets", arrets);
    }
}
