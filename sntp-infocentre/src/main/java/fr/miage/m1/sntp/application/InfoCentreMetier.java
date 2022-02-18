package fr.miage.m1.sntp.application;

import fr.miage.m1.sntp.camel.TrainCamel;
import fr.miage.m1.sntp.dao.GareDAO;
import fr.miage.m1.sntp.dao.PassageDAO;
import fr.miage.m1.sntp.dao.TrainDAO;
import fr.miage.m1.sntp.dto.VoyageurDTO;
import fr.miage.m1.sntp.exceptions.TrainException;
import fr.miage.m1.sntp.models.*;
import fr.miage.m1.sntp.services.TicketService;
import fr.miage.m1.sntp.utils.LibMail;
import io.quarkus.mailer.Mailer;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jgroups.util.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@ApplicationScoped
public class InfoCentreMetier {
    public static final int MINIMUM_PASSAGER_POUR_GENERER_RETARD = 0;
    public static final int MINIMUM_PASSAGER_POUR_SUPPRIMER_TRAIN = 20;
    public static final int MINUTE_MINIMUM_POUR_SUPPRIMER_STATION = 120;
    public static final int NB_HEURE_MINIMUM_FOR_ADD_STATION = 0;

    public static final String REFUS_AJOUTER_STATION = "Vous ne pouvez pas ajouter une station car le train précédent n'a pas %s heures de retard";
    public static final String TRAIN_NOT_FOUND = "Aucun train trouvé";
    public static final String GARE_NOT_FOUND = "Gare non trouvée";
    public static final String RESERVATION_INJOIGNABLE = "Le système de réservation est injoignable";
    public static final String PASSAGER_MINIMUM_PAS_ATTEINDS = "Le nombre minimum de passager n'est pas atteint";
    public static final String OK = "OK";
    public static final String NOMBRE_MINUTE_TROP_IMPORTANT = "Le nombre de minutes était trop important, l'arrêt a été supprimé";

    public static final String NOUVELLE_HORRAIRE_FORMAT = "Nouvelle horaire pour %s. Arrivée : %s et Départ à %s";
    public static final String SUPPRESSION_STATION_MESSAGE_TRAIN_FORMAT = "Attention vous ne devez plus desservir la gare %s";
    public static final String AJOUT_STATION_TRAIN_FORMAT = "Attention, vous devez desservir la gare %s. Départ à %s et arrivée à %s";
    public static final String TRAIN_SUPPRIMER = "Attention chauffeur, votre train est supprimée !";
    public static final String PASSAGER_AVEC_RESA_DANS_TRAIN = "Impossible, des passagers ont des reservations dans ce train à cette station.";
    public static final String RESERVATION_NON_JOIGNABLE = "Erreur, le système de réservation est injoignable";
    public static final String AUCUN_ARRET_TROUVEE = "Aucun arrêt trouvé";
    public static final String PREMIERE_GARE_DE_LA_JOURNEE = "Vous ne pouvez pas ajouter une station car c'est le premier de la journée à passer à la gare %s";
    public static final String PASSAGER_SUPERIEUR_SEUIL = "Le nombre de passagers actuels est supérieur à %s . Il n'est donc pas possible de supprimer le train";
    public static final int ZERO = 0;
    private static final String SUJET_MAIL_RETARD_TRAIN_FORMAT = "Retard du train n° %s";
    private static final String MESSAGE_RETARD_TRAIN_FORMAT = "Le train n° %s à destination de %s est en retard de %s minutes. Nous vous prions de bien vouloir nous excuser.";
    private static final String TRAIN_NOT_FOUD_FORMAT = "Le train avec l'id %s n'a pas été trouvé";
    private static final String GARE_NOT_FOUND_FORMAT = "La gare avec l'id %s n'a pas été trouvée";
    private static final String TRAIN_DELETE_SUBJECT_FORMAT = "Train n° %s supprimé";
    private static final String TRAIN_DELETE_MESSAGE_FORMAT = "Madame, Monsieur, \n nous sommes dans le regret de vous annoncer que le train n° %s à destination de %s est malheureusement supprimé. \n Contactez le service client pour un remboursement. \n Nous vous prions de bien vouloir nous excuser. \n Cordialement, \n Le service SNTP ";
    private static final Logger logger = LoggerFactory.getLogger(InfoCentreMetier.class);
    @Inject
    TrainDAO trainDAO;
    @Inject
    PassageDAO passageDAO;
    @Inject
    GareDAO gareDAO;

    @Inject
    TrainCamel trainCamel;

    @Inject
    @RestClient
    TicketService rs;

    @Inject
    Mailer mailer;

    public Tuple<Boolean, String> genererRetard(Long idTrain, Long idGare, Integer nombreDeMinute) {
        Train train;

        try {
            train = trainDAO.findTrain(idTrain);
        } catch (TrainException exception) {
            String msg = String.format(TRAIN_NOT_FOUD_FORMAT, idTrain);
            logger.warn(msg, exception);

            return new Tuple<>(false, TRAIN_NOT_FOUD_FORMAT);
        }
        Tuple<Boolean, String> verifRetardTuple = verificationPourRetard(train, nombreDeMinute);

        if (Boolean.FALSE.equals(verifRetardTuple.getVal1())) {
            return verifRetardTuple;
        }
        Set<Arret> setArret = train.getItineraireConcerner().getArrets();
        Integer positionGareConcerner = null;

        for (Arret arret : setArret) {
            if (arret.getGareConcerner().getId().equals(idGare)) {
                positionGareConcerner = arret.getPosition();
                break;
            }
        }
        if (positionGareConcerner == null) {
            return new Tuple<>(false, GARE_NOT_FOUND);
        }
        if (nombreDeMinute >= MINUTE_MINIMUM_POUR_SUPPRIMER_STATION) {
            Tuple<Boolean, String> tupleResponse = this.supprimerStation(idTrain, idGare);

            if (Boolean.TRUE.equals(tupleResponse.getVal1())) {
                return new Tuple<>(false, NOMBRE_MINUTE_TROP_IMPORTANT);
            }
        }
        generatePassage(nombreDeMinute, train, setArret, positionGareConcerner);

        return new Tuple<>(true, OK);
    }

    private void generatePassage(Integer nombreDeMinute, Train train, Set<Arret> setArret, Integer positionGareConcerner) {
        LocalDate dateDuJour = LocalDate.now();

        for (Arret arret : setArret) {
            boolean nouveauPassage = false;

            if (arret.getPosition() >= positionGareConcerner) {
                Passage passage = null;

                for (Passage passageFor : arret.getPassages()) {
                    if (passageFor.getDateDePassage().equals(LocalDate.now())) {
                        passage = passageFor;
                    }
                }
                if (passage == null) {
                    passage = new Passage();
                    passage.setEstSupprime(false);
                    passage.setArret(arret);
                    passage.setDateDePassage(dateDuJour);
                    passage.setMarquerArret(true);
                    nouveauPassage = true;
                }
                if (passage.getEstSupprime() == null) {
                    passage.setEstSupprime(false);
                }
                LocalTime heureArrivee = arret.getHeureArrivee();

                if (heureArrivee != null) {
                    heureArrivee = heureArrivee.plusMinutes(nombreDeMinute);
                    passage.setHeureArriveeReel(heureArrivee);
                }
                LocalTime heureDepart = arret.getHeureDepart();

                if (heureDepart != null) {
                    heureDepart = heureDepart.plusMinutes(nombreDeMinute);
                    passage.setHeureDepartReel(heureDepart);
                }
                if (nouveauPassage) {
                    passageDAO.insertPassage(passage);
                } else {
                    passageDAO.updatePassage(passage);
                }
                String message = String.format(NOUVELLE_HORRAIRE_FORMAT, arret.getGareConcerner().getNomGare(), heureArrivee, heureDepart);
                trainCamel.envoyerMessageAuTrain(train.getNumeroDeTrain(), message);
            }
        }
    }

    public Tuple<Boolean, String> verificationPourRetard(Train train, int nombreDeMinute) {
        if (train.getTypeDeTrain() != TypeTrain.TGV) {
            return new Tuple<>(true, OK);
        }
        Integer nombreDePassage;

        try {
            nombreDePassage = rs.getNbPassagerByTrainAndHasCorrespondance(train.getNumeroDeTrain());
        } catch (Exception e) {
            return new Tuple<>(false, RESERVATION_INJOIGNABLE);
        }
        if (nombreDePassage >= MINIMUM_PASSAGER_POUR_GENERER_RETARD) {
            sendEmail(SUJET_MAIL_RETARD_TRAIN_FORMAT, MESSAGE_RETARD_TRAIN_FORMAT, train, nombreDeMinute);
            return new Tuple<>(true, OK);
        }

        return new Tuple<>(false, PASSAGER_MINIMUM_PAS_ATTEINDS);
    }

    public Tuple<Boolean, String> supprimerStation(Long idTrain, Long idGare) {
        Train train;
        try {
            train = trainDAO.findTrain(idTrain);
        } catch (TrainException exception) {
            String msgError = String.format(TRAIN_NOT_FOUD_FORMAT, idTrain);
            logger.warn(msgError, exception);

            return new Tuple<>(false, TRAIN_NOT_FOUND);
        }
        Tuple<Boolean, String> verifsDeleteTuple = verificationPourSuppressionStation(train);

        if (Boolean.FALSE.equals(verifsDeleteTuple.getVal1())) {
            return verifsDeleteTuple;
        }
        Arret arret = null;
        Set<Arret> setArret = train.getItineraireConcerner().getArrets();

        for (Arret arretBoucle : setArret) {
            if (arretBoucle.getGareConcerner().getId().equals(idGare)) {
                arret = arretBoucle;
            }
        }
        if (arret == null) {
            String messageError = String.format(GARE_NOT_FOUND_FORMAT, idGare);
            logger.warn(messageError);

            return new Tuple<>(false, GARE_NOT_FOUND);
        }
        LocalDate date = LocalDate.now();
        generatePassagePrecis(date, arret, false, true);

        if (train.getTypeDeTrain() == TypeTrain.TGV) {
            sendEmail(TRAIN_DELETE_SUBJECT_FORMAT, TRAIN_DELETE_MESSAGE_FORMAT, train, ZERO);
        }
        String message = String.format(SUPPRESSION_STATION_MESSAGE_TRAIN_FORMAT, arret.getGareConcerner().getNomGare());
        trainCamel.envoyerMessageAuTrain(train.getNumeroDeTrain(), message);

        return new Tuple<>(true, OK);
    }

    private Tuple<Boolean, String> verificationPourSuppressionStation(Train train) {
        try {
            if (rs.getNbPassagerByTrain(train.getNumeroDeTrain()) == ZERO) {
                return new Tuple<>(true, OK);
            } else {
                return new Tuple<>(false, PASSAGER_AVEC_RESA_DANS_TRAIN);
            }
        } catch (Exception e) {
            return new Tuple<>(false, RESERVATION_NON_JOIGNABLE);
        }

    }

    public Tuple<Boolean, String> ajouterStation(Long idTrain, Long idGare) {
        Train train;

        try {
            train = trainDAO.findTrain(idTrain);
        } catch (TrainException exception) {
            String msgError = String.format(TRAIN_NOT_FOUD_FORMAT, idTrain);
            logger.warn(msgError, exception);

            return new Tuple<>(false, msgError);
        }

        Gare gare;

        try {
            gare = gareDAO.findGare(idGare);
        } catch (Exception e) {
            return new Tuple<>(false, GARE_NOT_FOUND);
        }
        if (gare == null) {
            return new Tuple<>(false, GARE_NOT_FOUND);
        }
        Arret arret = null;
        Set<Arret> setArret = train.getItineraireConcerner().getArrets();

        for (Arret arretBoucle : setArret) {
            if (arretBoucle.getGareConcerner().getId().equals(idGare)) {
                arret = arretBoucle;
            }
        }
        if (arret == null) {
            return new Tuple<>(false, AUCUN_ARRET_TROUVEE);
        }
        LocalDate date = LocalDate.now();
        Tuple<Boolean, String> reponseVerif = verificationPourAjouterStation(gare, arret, date);
        if (Boolean.FALSE.equals(reponseVerif.getVal1())) {
            return reponseVerif;
        }
        generatePassagePrecis(date, arret, true, false);
        String message = String.format(AJOUT_STATION_TRAIN_FORMAT, arret.getGareConcerner().getNomGare(), arret.getHeureDepart(), arret.getHeureArrivee());
        trainCamel.envoyerMessageAuTrain(train.getNumeroDeTrain(), message);

        return new Tuple<>(true, OK);
    }

    private Tuple<Boolean, String> verificationPourAjouterStation(Gare gare, Arret arretConcerner, LocalDate date) {
        Set<Arret> arretsDanslaGare = gare.getTrainsQuiPasseDansLaGare();
        Arret precedent = null;
        for (Arret arret : arretsDanslaGare) {
            if (arret == arretConcerner) {
                break;
            } else {
                if (arret.getHeureArrivee() != null) {
                    precedent = arret;
                }
            }
        }
        if (precedent == null) {
            return new Tuple<>(false, String.format(PREMIERE_GARE_DE_LA_JOURNEE, gare.getNomGare()));
        }
        for (Passage p : precedent.getPassages()) {
            if (p.getHeureArriveeReel() == null) {
                continue;
            }
            if (p.getDateDePassage().equals(date) &&
                    precedent.getHeureArrivee().until(p.getHeureArriveeReel(), ChronoUnit.HOURS) >= NB_HEURE_MINIMUM_FOR_ADD_STATION ||
                    Boolean.TRUE.equals(p.getEstSupprime())) {
                return new Tuple<>(true, OK);
            }
        }
        String messageDeRefus = String.format(REFUS_AJOUTER_STATION, NB_HEURE_MINIMUM_FOR_ADD_STATION);

        return new Tuple<>(false, messageDeRefus);
    }

    public Tuple<Boolean, String> supprimerTrain(Long idTrain) {
        Train train;

        try {
            train = trainDAO.findTrain(idTrain);
        } catch (TrainException exception) {
            String msgError = String.format(TRAIN_NOT_FOUD_FORMAT, idTrain);
            logger.warn(msgError, exception);

            return new Tuple<>(false, TRAIN_NOT_FOUND);
        }
        LocalDate date = LocalDate.now();
        Tuple<Boolean, String> verifDelTuple = verificationPourSuppressionTrain(train);

        if (Boolean.FALSE.equals(verifDelTuple.getVal1())) {
            return verifDelTuple;
        }

        Set<Arret> setArret = train.getItineraireConcerner().getArrets();

        for (Arret arret : setArret) {
            generatePassagePrecis(date, arret, false, true);
        }
        trainCamel.envoyerMessageAuTrain(train.getNumeroDeTrain(), TRAIN_SUPPRIMER);

        return new Tuple<>(true, OK);
    }

    private Tuple<Boolean, String> verificationPourSuppressionTrain(Train train) {
        if (rs.getNbPassagerByTrain(train.getNumeroDeTrain()) <= MINIMUM_PASSAGER_POUR_SUPPRIMER_TRAIN) {
            return new Tuple<>(true, OK);
        } else {
            String messageError = String.format(PASSAGER_SUPERIEUR_SEUIL, MINIMUM_PASSAGER_POUR_SUPPRIMER_TRAIN);
            return new Tuple<>(false, messageError);
        }
    }

    private void generatePassagePrecis(LocalDate date, Arret arret, Boolean marquerArret, Boolean estSupprime) {
        Passage passage = null;
        boolean nouveauPassage = false;
        for (Passage passageFor : arret.getPassages()) {
            if (passageFor.getDateDePassage().equals(LocalDate.now())) {
                passage = passageFor;
            }
        }
        if (passage == null) {
            passage = new Passage();
            nouveauPassage = true;
        }
        passage.setArret(arret).setDateDePassage(date).setMarquerArret(marquerArret);
        LocalTime heureArrivee = arret.getHeureArrivee();
        passage.setHeureArriveeReel(heureArrivee);
        LocalTime heureDepart = arret.getHeureDepart();
        passage.setHeureDepartReel(heureDepart);
        passage.setArret(arret);
        passage.setEstSupprime(estSupprime);
        if (nouveauPassage) {
            passageDAO.insertPassage(passage);
        } else {
            passageDAO.updatePassage(passage);
        }
    }

    public void genererPassagesDuJour() {
        List<Train> trains = trainDAO.getAllTrains();
        for (Train train : trains) {
            Set<Arret> arrets = train.getItineraireConcerner().getArrets();

            for (Arret arret : arrets) {
                Set<Passage> passages = arret.getPassages();

                boolean find = false;
                for (Passage passage : passages) {
                    if (Objects.equals(passage.getDateDePassage(), LocalDate.now())) {
                        find = true;
                    }
                }
                if (!find) {
                    generatePassagePrecis(LocalDate.now(), arret, arret.getDoitMarquerArret(), false);
                }
            }
        }

    }

    private void sendEmail(String subjectConst, String messageConst, Train train, Integer nombreDeMinute) {
        String subject = String.format(subjectConst, train.getNumeroDeTrain());
        String message;

        if (nombreDeMinute > 0) {
            message = String.format(messageConst, train.getNumeroDeTrain(), train.getTerminus(), nombreDeMinute);
        } else {
            message = String.format(messageConst, train.getNumeroDeTrain(), train.getTerminus());
        }
        List<VoyageurDTO> voyageurs = rs.getEmailsByTrainAndNow(train.getNumeroDeTrain());

        if (voyageurs.size() == 0) {
            return;
        }
        List<String> emails = new ArrayList<>();

        for (VoyageurDTO voyageur : voyageurs) {
            emails.add(voyageur.getEmail());
        }
        LibMail.sendMailWithBcc(mailer, emails, subject, message);
    }
}