package fr.miage.m1.sntp.application;

import fr.miage.m1.sntp.Main;
import fr.miage.m1.sntp.dto.ArretDTO;
import fr.miage.m1.sntp.ressources.ArretService;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Guila Bismuth
 */
@Path("/main")
public class ArretRessource {
    private static final String PATH_FOR_DEPARTURE = "/departs";
    private static final String NAME = "name";
    private static final String ARRETS = "arrets";
    private static final String PATH_FOR_ARRIVALS = "/arrivees";

    @Inject
    Template departs;

    @Inject
    Template arrivees;

    @Inject
    @RestClient
    ArretService arretService;

    @ConfigProperty(name = Main.InfoGare.PROPERTY_ID_GARE, defaultValue = "1")
    int idGare;

    /***
     * @param name
     * @return the template with the list of trains that must leave from the station passed in parameters
     */
    @Path(PATH_FOR_DEPARTURE)
    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getDeparts(@QueryParam(NAME) String name) {
        List<ArretDTO> arrets = arretService.getArretsDepartByGare(idGare);

        return departs.data(ARRETS, arrets);
    }

    /***
     * @param name
     * @return Returns the template with the list of trains that should arrive in the station passed in parameters
     */
    @Path(PATH_FOR_ARRIVALS)
    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getArrivees(@QueryParam(NAME) String name) {
        List<ArretDTO> arrets = arretService.getArretsArriveeByGare(idGare);

        return arrivees.data(ARRETS, arrets);
    }
}
