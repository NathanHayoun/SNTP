package fr.miage.m1.sntp;

import fr.miage.m1.sntp.ressources.ArretService;
import fr.miage.m1.sntp.ressources.GareService;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;


@QuarkusMain
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Quarkus.run(InfoGare.class, args);
    }

    public static class InfoGare implements QuarkusApplication {
        @Inject
        @RestClient
        GareService gareService;

        @Inject
        @RestClient
        ArretService arretService;

        @Override
        public int run(String... args) throws Exception {
            int idGare;
            try {
                idGare = Integer.parseInt(System.getProperty("idGare"));
            } catch (Exception e) {
                idGare = 1;
            }
            logger.info(gareService.getGare(idGare).getNomGare());
            logger.info(arretService.getArretsDepartByGare(idGare).toString());
            Quarkus.waitForExit();
            return 0;
        }
    }
}