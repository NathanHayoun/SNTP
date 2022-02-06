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
import org.jgroups.util.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Session;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@ApplicationScoped

public class InfoCentreMetier {
    public static final int MINIMUM_PASSAGER_POUR_GENERER_RETARD = 50;
    public static final int MINIMUM_PASSAGER_POUR_SUPPRIMER_TRAIN = 20;
    public static final int MINUTE_MINIMUM_POUR_SUPPRIMER_STATION = 30;
    public static final String NB_REQUIS_NON_ATTEINDS_POUR_RETARD = "Le nombre de personnes requise pour générer un retard n'est pas atteinte";
    public static final int NB_HEURE_MINIMUM_FOR_ADD_STATION = 2;
    public static final String REFUS_AJOUTER_STATION = "Vous ne pouvez pas ajouter une station car le train précédent n a pas %s heures de retard";
    public static final String JMS_TRAIN_INFOCENTRE = "jms:train/infocentre/";
    private static final String SUJET_MAIL_RETARD_TRAIN = "Retard du train n° %s";
    private static final String MESSAGE_RETARD_TRAIN = "le train n° %s à destination de %s est en retard de %s minutes. Nous vous prions de bien vouloir nous excuser.";
    private static final String TRAIN_NOT_FOUD = "Train with id %s not found";
    private static final String GARE_NOT_FOUD = "Gare with id %s not found";
    private static final String TRAIN_DELETE_SUBJECT = "Train n° %s supprimé ";
    private static final String TRAIN_DELETE_MESSAGE = "Madame, Monsieur, \n nous sommes dans le regret de vous annoncer que le train n° %s à destination de %s est malheuresement supprimé. \n Contactez le service client pour un remboursement. \n Nous vous prions de bien vouloir nous excuser. \n Cordialement, \n Le service SNTP ";
    private static final Logger logger = LoggerFactory.getLogger(InfoCentreMetier.class);
    //DAO
    @Inject
    TrainDAO trainDAO;
    @Inject
    PassageDAO passageDAO;
    @Inject
    GareDAO gareDAO;

    @Inject
    ConnectionFactory connectionFactory;

    //Services
    @Inject
    @RestClient
    ReservationService rs;

    //Lib
    @Inject
    Mailer mailer;

    public Tuple<Boolean, String> genererRetard(Long idTrain, Long idGare, Integer nombreDeMinute) {
        Train train;

        try {
            train = trainDAO.findTrain(idTrain);
            logger.info("Get train successfull");
        } catch (TrainException exception) {
            String msg = String.format(TRAIN_NOT_FOUD, idTrain);
            logger.warn(msg, exception);

            return new Tuple<>(false, "Train non trouve");
        }
        Tuple<Boolean, String> verifRetard = verificationPourRetard(train, nombreDeMinute);

        if (Boolean.FALSE.equals(verifRetard.getVal1())) {
            return verifRetard;
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
            return new Tuple<>(false, "Gare non trouve");
        }
        if (nombreDeMinute >= MINUTE_MINIMUM_POUR_SUPPRIMER_STATION) {
            this.supprimerStation(idTrain, idGare);

            return new Tuple<>(false, "Le nombre de minute n est pas atteinds");
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
                envoyerMessage(train.getId());
            }
            return new Tuple<>(true, "OK");
        }
    }

    public Tuple<Boolean, String> verificationPourRetard(Train train, int nombreDeMinute) {
        if (train.getTypeDeTrain() != TypeTrain.TGV) {
            return new Tuple<>(true, "OK");
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

            return new Tuple<>(true, "OK");
        }
        logger.info(NB_REQUIS_NON_ATTEINDS_POUR_RETARD);

        return new Tuple<>(false, "Le nombre minimum de passager n est pas atteinds");
    }

    public Tuple<Boolean, String> supprimerStation(Long idTrain, Long idGare) {
        Train train;

        try {
            train = trainDAO.findTrain(idTrain);
        } catch (TrainException exception) {
            String msgError = String.format(TRAIN_NOT_FOUD, idTrain);
            logger.warn(msgError, exception);

            return new Tuple<>(false, "Aucun train trouvee");
        }
        Tuple<Boolean, String> verifsDelete = verificationPourSuppressionStation(train);
        if (Boolean.FALSE.equals(verifsDelete.getVal1())) {
            return verifsDelete;
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

            return new Tuple<>(false, "Gare non trouve");
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

        return new Tuple<>(true, "OK");
    }

    public Tuple<Boolean, String> ajouterStation(Long idTrain, Long idGare) {
        Train train;

        try {
            train = trainDAO.findTrain(idTrain);
        } catch (TrainException exception) {
            String msgError = String.format(TRAIN_NOT_FOUD, idTrain);
            logger.warn(msgError, exception);

            return new Tuple<>(false, msgError);
        }

        Gare gare = null;

        try {
            gare = gareDAO.findGare(idGare);
        } catch (Exception e) {
            logger.warn("err", e);
        }
        if (gare == null) {
            return new Tuple<>(false, "Aucune gare trouvee");
        }
        Arret arret = null;
        Set<Arret> setArret = train.getItineraireConcerner().getArrets();

        for (Arret arretBoucle : setArret) {
            if (arretBoucle.getGareConcerner().getId().equals(idGare)) {
                arret = arretBoucle;
            }
        }
        if (arret == null) {
            return new Tuple<>(false, "Aucun Arret trouvee");
        }
        LocalDate date = LocalDate.now();
        Tuple<Boolean, String> reponseVerif = verificationPourAjouterStation(gare, arret, date);
        if (Boolean.FALSE.equals(reponseVerif.getVal1())) {
            return reponseVerif;
        }
        generatePassage(date, arret, true);

        return new Tuple<>(true, "ok");
    }

    public Tuple<Boolean, String> supprimerTrain(Long idTrain) {
        Train train;

        try {
            train = trainDAO.findTrain(idTrain);
        } catch (TrainException exception) {
            String msgError = String.format(TRAIN_NOT_FOUD, idTrain);
            logger.warn(msgError, exception);

            return new Tuple<>(false, "train non trouvee");
        }
        LocalDate date = LocalDate.now();
        Tuple<Boolean, String> verifDel = verificationPourSuppressionTrain(train);
        if (Boolean.FALSE.equals(verifDel.getVal1())) {
            return verifDel;
        }
        Set<Arret> setArret = train.getItineraireConcerner().getArrets();

        for (Arret arret : setArret) {
            generatePassage(date, arret, false);
        }

        return new Tuple<>(true, "OK");
    }

    private Tuple<Boolean, String> verificationPourAjouterStation(Gare gare, Arret arretConcerner, LocalDate date) {
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
            return new Tuple<>(false, "Vous ne pouvez pas ajouter une station car c'est le premier de la journee a y passer");
        }
        for (Passage p : precedent.getPassages()) {
            if (p.getHeureDepartReel() == null) {
                continue;
            }
            if (p.getDateDePassage().equals(date) && precedent.getHeureDepart().until(p.getHeureDepartReel(), ChronoUnit.HOURS) > NB_HEURE_MINIMUM_FOR_ADD_STATION) {
                return new Tuple<>(true, "OK");
            }
        }
        logger.info("Message de refus");
        String messageDeRefus = String.format(REFUS_AJOUTER_STATION, NB_HEURE_MINIMUM_FOR_ADD_STATION);

        return new Tuple<>(false, messageDeRefus);
    }

    private Tuple<Boolean, String> verificationPourSuppressionStation(Train train) {
        if (rs.getNbPassagerByTrain(train.getNumeroDeTrain()) == 0) {
            return new Tuple<>(true, "OK");
        } else {
            return new Tuple<>(false, "Impossible, des passagers on des reservations dans ce train à cette station.");
        }

    }

    private Tuple<Boolean, String> verificationPourSuppressionTrain(Train train) {
        if (rs.getNbPassagerByTrain(train.getNumeroDeTrain()) <= MINIMUM_PASSAGER_POUR_SUPPRIMER_TRAIN) {
            return new Tuple<>(true, "OK");
        } else {
            String messageError = String.format("Le nombre de passager acutel est superieur a %s . Il n est donc pas possible de supprimer le train", MINIMUM_PASSAGER_POUR_SUPPRIMER_TRAIN);
            return new Tuple<>(false, messageError);
        }
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

    public void envoyerMessage(Long idTrain) {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            context.createProducer().send(context.createQueue(JMS_TRAIN_INFOCENTRE + idTrain), Integer.toString(3));
        }
    }
}