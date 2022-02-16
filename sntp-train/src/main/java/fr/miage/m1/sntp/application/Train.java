package fr.miage.m1.sntp.application;

import fr.miage.m1.sntp.dto.ArretDTO;
import fr.miage.m1.sntp.ressources.ArretService;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.List;

public class Train implements QuarkusApplication {
    public static final String TRAIN_SUPPRIMEE = "Votre train est supprimé ! Rentré au dépot ";
    private static final String MESSAGE_INITIAL_ARRET = "Vous partirez de la gare %s a %s";
    private static final String MESSAGE_TERMINUS_ARRET = "Vous terminerez votre parcours a la gare %s et vous devez y être pour %s";
    private static final String MESSAGE_STATION_ARRET = "En postion %s vous devez arrivée a la station %s a %s et partir a %s";
    private static final String GETTING_ROUTE = "The application is starting getting your route...";
    private static final String BIENVENUE_CONDUCTEUR = "Bienvenue Conducteur !";
    private static final String CHEMIN_DU_JOUR = "Voici votre chemin du jour";
    private static final Logger logger = LoggerFactory.getLogger(Train.class);
    @Inject
    @RestClient
    ArretService arretService;

    @ConfigProperty(name = "fr.miage.m1.sntp.numero.train", defaultValue = "2587")
    int numeroDeTrain;

    static void onStart(@Observes StartupEvent ev) {
        logger.info(GETTING_ROUTE);
    }

    @Override
    public int run(String... args) {
        List<ArretDTO> arrets = arretService.getArrets(numeroDeTrain);
        logger.info(BIENVENUE_CONDUCTEUR);
        int i = 0;
        int count = 0;
        
        for (ArretDTO arret : arrets) {
            if (!arret.getPassageDuJour().getEstSupprime()) {
                break;
            }
            count++;
        }
        if (count == arrets.size()) {
            logger.info(TRAIN_SUPPRIMEE);
        } else {
            logger.info(CHEMIN_DU_JOUR);

            for (ArretDTO arret : arrets) {
                String message = "";

                if (arret.getPosition() == 1 && !arret.getPassageDuJour().getEstSupprime()) {
                    message = String.format(MESSAGE_INITIAL_ARRET, arret.getGareConcerner().getNomGare(), arret.getHeureDepart());
                } else if (++i == arrets.size() - 1) {
                    if (!arret.getPassageDuJour().getEstSupprime()) {
                        message = String.format(MESSAGE_TERMINUS_ARRET, arret.getGareConcerner().getNomGare(), arret.getHeureArrivee());
                    }
                } else {
                    if (!arret.getPassageDuJour().getEstSupprime()) {
                        message = String.format(MESSAGE_STATION_ARRET, arret.getPosition(), arret.getGareConcerner().getNomGare(), arret.getHeureArrivee(), arret.getHeureDepart());
                    }
                }
                logger.info(message);
            }
            Quarkus.waitForExit();
        }
        return 0;
    }
}
