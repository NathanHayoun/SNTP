package fr.miage.m1.sntp.application;

import fr.miage.m1.sntp.dao.GareDAO;
import fr.miage.m1.sntp.dao.PassageDAO;
import fr.miage.m1.sntp.dao.TrainDAO;
import fr.miage.m1.sntp.dto.VoyageurDTO;
import fr.miage.m1.sntp.exceptions.TrainException;
import fr.miage.m1.sntp.models.*;
import fr.miage.m1.sntp.services.ReservationService;
import fr.miage.m1.sntp.utils.LibMail;
import io.quarkus.mailer.Mailer;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@ApplicationScoped

public class InfoCentre {
    public static final int MINIMUM_PASSAGER_POUR_GENERER_RETARD = 50;
    public static final int MINIMUM_PASSAGER_POUR_SUPPRIMER_TRAIN = 20;
    public static final int MINUTE_MINIMUM_POUR_SUPPRIMER_STATION = 30;
    public static final String NB_REQUIS_NON_ATTEINDS_POUR_RETARD = "Le nombre de personnes requise pour générer un retard n'est pas atteinte";
    public static final int NB_HEURE_MINIMUM_FOR_ADD_STATION = 2;
    private static final String SUJET_MAIL_RETARD_TRAIN = "Retard du train n° %s";
    private static final String MESSAGE_RETARD_TRAIN = "le train n° %s à destination de %s est en retard de %s minutes. Nous vous prions de bien vouloir nous excuser.";
    private static final String TRAIN_NOT_FOUD = "Train with id %s not found";
    private static final String GARE_NOT_FOUD = "Gare with id %s not found";
    private static final String TRAIN_DELETE_SUBJECT = "Train n° %s supprimé ";
    private static final String TRAIN_DELETE_MESSAGE = "Madame, Monsieur, \n nous sommes dans le regret de vous annoncer que le train n° %s à destination de %s est malheuresement supprimé. \n Contactez le service client pour un remboursement. \n Nous vous prions de bien vouloir nous excuser. \n Cordialement, \n Le service SNTP ";
    private static final Logger logger = LoggerFactory.getLogger(InfoCentre.class);
    //DAO
    @Inject
    TrainDAO trainDAO;
    @Inject
    PassageDAO passageDAO;
    @Inject
    GareDAO gareDAO;

    //Services
    @Inject
    @RestClient
    ReservationService rs;

    //Lib
    @Inject
    Mailer mailer;

    public Boolean genererRetard(Long idTrain, Integer nombreDeMinute, Long idGare) {
        Train train;

        try {
            train = trainDAO.findTrain(idTrain);
            logger.info("Get train successfull");
        } catch (TrainException exception) {
            String msg = String.format(TRAIN_NOT_FOUD, idTrain);
            logger.warn(msg, exception);

            return false;
        }
        if (Boolean.FALSE.equals(verificationPourRetard(train, nombreDeMinute))) {
            return false;
        }
        Set<Arret> setArret = train.getItineraireConcerner().getArrets();
        Map<Integer, Arret> arretOrdonnees = new HashMap<>();
        Integer positionGareConcerner = null;

        for (Arret arret : setArret) {
            arretOrdonnees.put(arret.getPosition(), arret);

            if (arret.getGareConcerner().getId().equals(idGare)) {
                positionGareConcerner = arret.getPosition();
            }
        }
        if (positionGareConcerner == null) {
            return false;
        }
        if (nombreDeMinute >= MINUTE_MINIMUM_POUR_SUPPRIMER_STATION) {
            this.supprimerStation(idTrain, idGare);

            return false;
        } else {
            LocalDate dateDuJour = LocalDate.now();

            for (int i = positionGareConcerner; i < arretOrdonnees.size() + 1; i++) {
                Passage passage = new Passage();
                Arret arret = arretOrdonnees.get(i);
                passage.setArret(arret);
                passage.setDateDePassage(dateDuJour);
                passage.setMarquerArret(true);
                LocalTime heureArrivee = arretOrdonnees.get(i).getHeureArrivee();

                if (heureArrivee != null) {
                    heureArrivee = heureArrivee.plusMinutes(nombreDeMinute);
                    passage.setHeureArriveeReel(heureArrivee);
                }
                LocalTime heureDepart = arretOrdonnees.get(i).getHeureDepart();

                if (heureDepart != null) {
                    heureDepart = heureDepart.plusMinutes(nombreDeMinute);
                    passage.setHeureDepartReel(heureDepart);
                }
                passage.setArret(arret);
                passageDAO.insertPassage(passage);
            }

            return true;
        }
    }

    public Boolean verificationPourRetard(Train train, int nombreDeMinute) {
        if (train.getTypeDeTrain() != TypeTrain.TGV) {
            return true;
        }
        Integer nombreDePassage = rs.getNbPassagerByTrainAndHasCorrespondance(train.getNumeroDeTrain());
        if (nombreDePassage > MINIMUM_PASSAGER_POUR_GENERER_RETARD) {
            List<String> mails = new ArrayList<>();
            List<VoyageurDTO> voyageurs = rs.getEmailsByTrainAndNow(train.getNumeroDeTrain());

            for (VoyageurDTO voyageur : voyageurs) {
                mails.add(voyageur.getEmail());
            }
            String sujet = String.format(SUJET_MAIL_RETARD_TRAIN, train.getNumeroDeTrain());
            String message = String.format(MESSAGE_RETARD_TRAIN, train.getNumeroDeTrain(), train.getTerminus(), nombreDeMinute);
            LibMail.sendMailWithBcc(mailer, mails, sujet, message);

            return true;
        }
        logger.info(NB_REQUIS_NON_ATTEINDS_POUR_RETARD);

        return false;
    }

    public Boolean supprimerStation(Long idTrain, Long idGare) {
        Train train;

        try {
            train = trainDAO.findTrain(idTrain);
        } catch (TrainException exception) {
            String msgError = String.format(TRAIN_NOT_FOUD, idTrain);
            logger.warn(msgError, exception);

            return false;
        }

        if (Boolean.FALSE.equals(verificationPourSuppressionStation(train))) {
            return false;
        }
        Arret arret = null;
        Set<Arret> setArret = train.getItineraireConcerner().getArrets();
        Integer positionGareConcerner = 1;

        for (Arret arretBoucle : setArret) {
            if (arretBoucle.getGareConcerner().getId().equals(idGare)) {
                arret = arretBoucle;
            }
        }
        if (arret == null) {
            String messageError = String.format(GARE_NOT_FOUD, idGare);
            logger.warn(messageError);

            return false;
        }
        LocalDate date = LocalDate.now();
        generatePassage(date, arret, false);

        if (train.getTypeDeTrain() == TypeTrain.TGV) {
            String subject = String.format(TRAIN_DELETE_SUBJECT, train.getNumeroDeTrain());
            String message = String.format(TRAIN_DELETE_MESSAGE, train.getNumeroDeTrain(), train.getTerminus());
            List<VoyageurDTO> voyageurs = rs.getEmailsByTrainAndNow(train.getNumeroDeTrain());
            List<String> emails = new ArrayList<>();
            for (VoyageurDTO voyageur : voyageurs) {
                emails.add(voyageur.getEmail());
            }
            LibMail.sendMailWithBcc(mailer, emails, subject, message);
        }

        return true;
    }

    public Boolean ajouterStation(Long idTrain, Long idGare) {
        Train train;

        try {
            train = trainDAO.findTrain(idTrain);
        } catch (TrainException exception) {
            String msgError = String.format(TRAIN_NOT_FOUD, idTrain);
            logger.warn(msgError, exception);

            return false;
        }

        Gare gare = null;

        try {
            gare = gareDAO.findGare(idGare);
        } catch (Exception e) {
            //
        }
        if (gare == null) {
            return false;
        }
        Arret arret = null;
        Set<Arret> setArret = train.getItineraireConcerner().getArrets();

        for (Arret arretBoucle : setArret) {
            if (arretBoucle.getGareConcerner().getId().equals(idGare)) {
                arret = arretBoucle;
            }
        }
        if (arret == null) {
            return false;
        }
        LocalDate date = LocalDate.now();
        if (Boolean.FALSE.equals(verificationPourAjouterStation(gare, arret, date))) {
            return false;
        }
        generatePassage(date, arret, true);

        return true;
    }

    public Boolean supprimerTrain(Long idTrain) {
        Train train;

        try {
            train = trainDAO.findTrain(idTrain);
        } catch (TrainException exception) {
            String msgError = String.format(TRAIN_NOT_FOUD, idTrain);
            logger.warn(msgError, exception);

            return false;
        }
        LocalDate date = LocalDate.now();

        if (Boolean.FALSE.equals(verificationPourSuppressionTrain(train))) {
            return false;
        }
        Set<Arret> setArret = train.getItineraireConcerner().getArrets();

        for (Arret arret : setArret) {
            generatePassage(date, arret, false);
        }

        return true;
    }

    private Boolean verificationPourAjouterStation(Gare gare, Arret arretConcerner, LocalDate date) {
        Set<Arret> arretsDanslaGare = gare.getTrainsQuiPasseDansLaGare();
        Arret precedent = null;

        for (Arret arret : arretsDanslaGare) {
            if (arret == arretConcerner) {
                break;
            } else {
                precedent = arret;
            }
        }
        if (precedent == null) {
            return false;
        }
        for (Passage p : precedent.getPassages()) {
            if (p.getDateDePassage().equals(date) && precedent.getHeureDepart().until(p.getHeureDepartReel(), ChronoUnit.HOURS) > NB_HEURE_MINIMUM_FOR_ADD_STATION) {
                return true;
            }
        }

        return false;
    }

    private Boolean verificationPourSuppressionStation(Train train) {
        return rs.getNbPassagerByTrain(train.getNumeroDeTrain()) == 0;
    }

    private Boolean verificationPourSuppressionTrain(Train train) {
        return rs.getNbPassagerByTrain(train.getNumeroDeTrain()) <= MINIMUM_PASSAGER_POUR_SUPPRIMER_TRAIN;
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
}
