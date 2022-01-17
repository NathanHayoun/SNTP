package fr.miage.m1.sntp.application;

import fr.miage.m1.sntp.dao.PassageDAO;
import fr.miage.m1.sntp.dao.TrainDAO;
import fr.miage.m1.sntp.dto.NombreDTO;
import fr.miage.m1.sntp.exceptions.TrainException;
import fr.miage.m1.sntp.models.Arret;
import fr.miage.m1.sntp.models.Passage;
import fr.miage.m1.sntp.models.Train;
import fr.miage.m1.sntp.models.TypeTrain;
import fr.miage.m1.sntp.ressources.MailResource;
import fr.miage.m1.sntp.services.ReservationService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Path("genererRetard")
public class InfoCentre {
    public static final int MINIMUM_PASSAGER_POUR_GENERER_RETARD = 50;
    public static final int MINUTE_MINIMUM_POUR_SUPPRIMER_STATION = 30;
    Logger logger = LoggerFactory.getLogger(InfoCentre.class);
    @Inject
    TrainDAO trainDAO;
    @Inject
    PassageDAO passageDAO;

    @Inject
    @RestClient
    ReservationService rs;

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
//    public String genererRetard(Long idTrain, Integer nombreDeMinute, Long idGare) {
    public String genererRetard() {
        Long idTrain = 1L;
        int nombreDeMinute = 2;
        Long idGare = 2L;
        Train train;

        try {
            train = trainDAO.findTrain(idTrain);
            logger.info("Get train successfull");
        } catch (TrainException exception) {
            logger.warn("Train with id " + idTrain + " not found", exception);

            return "null";
        }
        LocalDate date = LocalDate.now();

        if (Boolean.FALSE.equals(verificationPourRetard(train))) {
            return "null";
        }

        Set<Arret> setArret = train.getItineraireConcerner().getArrets();
        Map<Integer, Arret> arretOrdonnees = new HashMap<>();
        Integer positionGareConcerner = 1;
        logger.info("get arrets");

        for (Arret arret : setArret) {
            arretOrdonnees.put(arret.getPosition(), arret);
            logger.info("arret " + arret.getGareConcerner().getNomGare());
            if (arret.getGareConcerner().getId().equals(idGare)) {
                positionGareConcerner = arret.getPosition();
            }
        }

        for (int i = positionGareConcerner; i < arretOrdonnees.size() + 1; i++) {
            Passage passage = new Passage();
            Arret arret = arretOrdonnees.get(i);
            logger.info("arret " + arret.getGareConcerner().getNomGare());
            passage.setArret(arret);
            passage.setDateDePassage(date);
            passage.setMarquerArret(true);
            LocalTime heureArrivee = arretOrdonnees.get(i).getHeureArrivee();

            if (heureArrivee != null) {
                heureArrivee = heureArrivee.plusMinutes(nombreDeMinute);
                passage.setHeureArriveeReel(heureArrivee);
                logger.info("set heure arrivee");
            }
            LocalTime heureDepart = arretOrdonnees.get(i).getHeureDepart();

            if (heureDepart != null) {
                heureDepart = heureDepart.plusMinutes(nombreDeMinute);
                passage.setHeureDepartReel(heureDepart);
                logger.info("set heure depart");
            }
            passage.setArret(arret);
            passageDAO.insertPassage(passage);
        }
        return "coucou";
    }

    public String supprimerStation() {
        Long idTrain = 1L;
        Long idGare = 1L;

        Train train;

        try {
            train = trainDAO.findTrain(idTrain);
            logger.info("Get train successfull");
        } catch (TrainException exception) {
            logger.warn("Train with id " + idTrain + " not found", exception);

            return "null";
        }

        if (Boolean.FALSE.equals(verificationPourSuppressionStation(train, 2))) {
            return "null";
        }

        Arret arret = null;

        Set<Arret> setArret = train.getItineraireConcerner().getArrets();
        Integer positionGareConcerner = 1;
        logger.info("get arrets");

        for (Arret arretBoucle : setArret) {
            if (arretBoucle.getGareConcerner().getId().equals(idGare)) {
                arret = arretBoucle;
            }
        }
        LocalDate date = LocalDate.now();
        generatePassage(date, arret, false);

        return "coucou";
    }


    public String ajouterStation() {
        Long idTrain = 1L;
        Long idGare = 1L;

        Train train;

        try {
            train = trainDAO.findTrain(idTrain);
            logger.info("Get train successfull");
        } catch (TrainException exception) {
            logger.warn("Train with id " + idTrain + " not found", exception);

            return "null";
        }
        if (Boolean.FALSE.equals(verificationPourAjouterStation(train))) {
            return "null";
        }
        Arret arret = null;

        Set<Arret> setArret = train.getItineraireConcerner().getArrets();
        Integer positionGareConcerner = 1;
        logger.info("get arrets");

        for (Arret arretBoucle : setArret) {
            if (arretBoucle.getGareConcerner().getId().equals(idGare)) {
                arret = arretBoucle;
            }
        }
        LocalDate date = LocalDate.now();
        generatePassage(date, arret, true);

        return "coucou";
    }

    public String supprimerTrain() {
        Long idTrain = 1L;
        int nombreDeMinute = 2;
        Train train;

        try {
            train = trainDAO.findTrain(idTrain);
            logger.info("Get train successfull");
        } catch (TrainException exception) {
            logger.warn("Train with id " + idTrain + " not found", exception);

            return "null";
        }
        LocalDate date = LocalDate.now();

        if (Boolean.FALSE.equals(verificationPourSuppressionTrain(train))) {
            return "null";
        }

        Set<Arret> setArret = train.getItineraireConcerner().getArrets();

        for (Arret arret : setArret) {
            generatePassage(date, arret, false);
        }

        return "coucou";
    }

    private void generatePassage(LocalDate date, Arret arret, Boolean marquerArret) {
        Passage passage = new Passage();
        passage.setArret(arret);
        passage.setDateDePassage(date);
        passage.setMarquerArret(marquerArret);
        LocalTime heureArrivee = arret.getHeureArrivee();
        passage.setHeureArriveeReel(heureArrivee);
        LocalTime heureDepart = arret.getHeureDepart();
        passage.setHeureDepartReel(heureDepart);
        passage.setArret(arret);
        passageDAO.insertPassage(passage);
    }

    private Boolean verificationPourAjouterStation(Train train) {
        return true;
    }

    public Boolean verificationPourRetard(Train train) {
        logger.info("dans verif train");
        if (train.getTypeDeTrain() == TypeTrain.TER) {
            return false;
        }
        if (train.getTypeDeTrain() == TypeTrain.TGV) {
            logger.info("dans c tgv");
            List<String> mails = new ArrayList<>();
            mails.add("nathanpapy@hotmail.fr");
            mails.add("nathan-perso@live.fr");
            MailResource mr = new MailResource();
            mr.sendMailWithBcc(mails, "Retard du Train N°" + train.getNumeroDeTrain().toString(), "Votre train N°" + train.getNumeroDeTrain() + " est en retard de " + 30 + " minutes. Nous vous prions de bien vouloir nous excuser pour la gène occasionée");
            logger.info("normalment mail send");
        }
        NombreDTO nombreDePassage = rs.getNbPassagerByTrainAndHasCorrespondance(train.getNumeroDeTrain());

        return nombreDePassage.getNomreDeReservation() > MINIMUM_PASSAGER_POUR_GENERER_RETARD;
    }

    private Boolean verificationPourSuppressionStation(Train train, Integer nombreDeMinute) {
        if (nombreDeMinute < MINUTE_MINIMUM_POUR_SUPPRIMER_STATION) {
            return false;
        }
        return rs.getNbPassagerByTrain(train.getNumeroDeTrain()).getNomreDeReservation() == 0;
    }

    private Boolean verificationPourSuppressionTrain(Train train) {
        return true;
    }
}
