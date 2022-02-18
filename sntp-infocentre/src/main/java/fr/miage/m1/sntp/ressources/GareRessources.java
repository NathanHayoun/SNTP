package fr.miage.m1.sntp.ressources;

import fr.miage.m1.sntp.dao.GareDAO;
import fr.miage.m1.sntp.exceptions.GareException;
import fr.miage.m1.sntp.models.Gare;
import fr.miage.m1.sntp.models.Train;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/gares")
public class GareRessources {
    public static final String GARE_ID = "/gare/{id}";
    public static final String ID = "id";

    @ConfigProperty(name = "fr.miage.m1.sntp.urlLocal")
    String localURL;

    @Inject
    GareDAO gareDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Gare> getGares() {
        List<Gare> gares = gareDAO.getAllGare();
        for (Gare gare : gares) {
            JsonArray value = Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                            .add("rel", "gare")
                            .add("link", localURL+"gares/gare/"+gare.getId()))
                    .build();
            gare.setLinks(value);
        }

        return gares;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(GARE_ID)
    public Gare getGare(@PathParam(ID) long id) {
        try {
            Gare gare = gareDAO.findGare(id);
            JsonArray value = Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                            .add("rel", "gares")
                            .add("link", localURL+"gares/"))
                    .build();
            gare.setLinks(value);
            return gare;
        } catch (GareException gareException) {
            return null;
        }
    }
}


