package fr.miage.m1.sntp.application;

import fr.miage.m1.sntp.dto.ArretDTO;
import fr.miage.m1.sntp.ressources.ArretService;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.List;

public class Train implements QuarkusApplication {
    public static final String MESSAGE_INITIAL_ARRET = "Vous partirez de la gare %s a %s";
    public static final String MESSAGE_TERMINUS_ARRET = "Vous terminerez votre parcours a la gare %s et vous devez y être pour %s";
    public static final String MESSAGE_STATION_ARRET = "En postion %s vous devez arrivée a la station %s a %s et partir a %s";
    public static final int numeroDeTrain = Integer.parseInt(System.getProperty("numeroDeTrain"));
    private static final Logger logger = LoggerFactory.getLogger(Train.class);
    @Inject
    @RestClient
    ArretService arretService;

    static void onStart(@Observes StartupEvent ev) {
        logger.info("The application is starting getting idTrain...");
        logger.info("Train with numero de train " + numeroDeTrain + "sucessfull initialise ");
    }

    @Override
    public int run(String... args) throws Exception {
        logger.warn("JAPPEL ARRET AVEC NUMERO " + numeroDeTrain);
        List<ArretDTO> arrets = arretService.getArrets(numeroDeTrain);
        logger.info("Bienvenue Conducteur !");
        logger.info("Voici votre chemin du jour");
        int i = 0;

        for (ArretDTO arret : arrets) {
            String message;

            if (arret.getPosition() == 1) {
                message = String.format(MESSAGE_INITIAL_ARRET, arret.getGareConcerner().getNomGare(), arret.getHeureDepart());
            } else if (++i == arrets.size() - 1) {
                message = String.format(MESSAGE_TERMINUS_ARRET, arret.getGareConcerner().getNomGare(), arret.getHeureArrivee());
            } else {
                message = String.format(MESSAGE_STATION_ARRET, arret.getPosition(), arret.getGareConcerner().getNomGare(), arret.getHeureArrivee(), arret.getHeureDepart());

            }
            logger.info(message);
        }
        Quarkus.waitForExit();

        return 0;
    }
}
