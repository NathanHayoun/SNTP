package fr.miage.m1.sntp;

import fr.miage.m1.sntp.ressources.GareService;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * @author Guila Bismuth
 * Class Main
 */
@QuarkusMain
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Quarkus.run(InfoGare.class, args);
    }

    public static class InfoGare implements QuarkusApplication {
        public static final String PROPERTY_ID_GARE = "fr.miage.m1.sntp.idGare";
        public static final String INFOGARE_STARTED_WITH_GARE_NAME = "Infogare started with gare name {}";

        @Inject
        @RestClient
        GareService gareService;

        @ConfigProperty(name = PROPERTY_ID_GARE, defaultValue = "1")
        int idGare;

        @Override
        public int run(String... args) {
            logger.info(INFOGARE_STARTED_WITH_GARE_NAME, gareService.getGare(idGare).getNomGare());
            Quarkus.waitForExit();

            return 0;
        }
    }
}