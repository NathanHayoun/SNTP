package fr.miage.m1.sntp.application;

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
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("main")
public class TrainRessource {
    private static final String PATH_MAIN = "train";
    private static final String ARRETS = "arrets";
    public static final String LIGNE_DE_TRAIN = "ligneDeTrain";
    public static final String NUMERO_DE_TRAIN = "numeroDeTrain";

    @Inject
    Template main;

    @Inject
    @RestClient
    ArretService arretService;

    @ConfigProperty(name = "fr.miage.m1.sntp.numero.train", defaultValue = "2587")
    int numeroDeTrain;

    /***
     * @return the template with the arrets of trains that must stop
     */
    @Path(PATH_MAIN)
    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getArrets() {
        List<ArretDTO> arrets = arretService.getArrets(numeroDeTrain);
        String ligneDeTrain = arrets.get(0).getTrain().getLigneDeTrain();
        Long numeroDeTrain = arrets.get(0).getTrain().getNumeroDeTrain();
        return main.data(ARRETS, arrets)
                .data(LIGNE_DE_TRAIN, ligneDeTrain)
                .data(NUMERO_DE_TRAIN, numeroDeTrain);
    }
}