package fr.miage.m1.sntp;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ressources.GareService;

import javax.inject.Inject;


@QuarkusMain
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Integer idGare = Integer.parseInt(System.getProperty("idGare"));


    public static void main(String[] args) {
        Quarkus.run(InfoGare.class, args);
    }

     public static class InfoGare implements QuarkusApplication{
         @Inject
         @RestClient
         GareService gs;

         @Override
         public int run(String... args) throws Exception {
             logger.info(gs.getGare(idGare).getNomGare() + "COUCOU EVERYONE");

             Quarkus.waitForExit();
             return 0;
         }
     }
}