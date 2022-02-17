package fr.miage.m1.sntp.cli;


import fr.miage.m1.sntp.application.InfoCentreMetier;
import fr.miage.m1.sntp.dao.TrainDAO;
import fr.miage.m1.sntp.models.Arret;
import fr.miage.m1.sntp.models.Gare;
import fr.miage.m1.sntp.models.Train;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import org.jgroups.util.Tuple;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@ApplicationScoped
public class UserInterfaceCLIImpl implements UserInterfaceCLI {
    public static final String AUCUNE_GARE_A_AJOUTER_DISPONIBLE = "Aucune gare a ajouter disponible !";
    public static final String STATION_AJOUTER_AVEC_SUCCESS = "Nous avons ajouter votre station a votre gare";
    public static final String AUCUNE_STATION_A_SUPPRIMER_TROUVER = "Aucune station a supprimer trouver";
    public static final String STATION_SUPPRIMER_AVEC_SUCCES = "Station supprimer avec succes";
    public static final String TRAIN_SUPPRIMER_AVEC_SUCCESS = "Train supprimer avec success";
    public static final String RETARD_AJOUTER = "Retard ajouté avec succès";
    public static final String ERREUR = "Erreur";
    public static final String CHOIX_TRAIN = "[ Choix numero: %s - Numéro du train: %s - Ligne: %s ]";
    public static final String AFFICHAGE_GARE = "[ %s ] - %s";
    public static final String QUEL_GARE_EST_CONCERNEE = "Quel gare est concernee ?";
    public static final String QUEL_TRAIN_EST_CONCERNEE = "Quel train est concernee ?";
    public static final String QUEL_TRAIN = "Quel train?";
    public static final String COMBIEN_DE_MINUTE = "De combien de minute parle t'on ?";
    public static final int NEGATIVE_NUMBER = -1;
    public static final long NEGATIVE_LONG = -1L;
    private static final TextIO textIO = TextIoFactory.getTextIO();
    private static final TextTerminal<?> terminal = textIO.getTextTerminal();
    @Inject
    TrainDAO trainDAO;
    @Inject
    InfoCentreMetier icm;


    @Override
    public void ajouterStationForTrain() {
        Tuple<Long, Long> ids = generateIdTrainAndGare(false, AUCUNE_GARE_A_AJOUTER_DISPONIBLE);

        if (ids.getVal1() == NEGATIVE_NUMBER) {
            return;
        }
        Tuple<Boolean, String> reponseICM = icm.ajouterStation(ids.getVal1(), ids.getVal2());
        generateResponse(reponseICM, STATION_AJOUTER_AVEC_SUCCESS);
    }

    @Override
    public void supprimerStationForTrain() {
        Tuple<Long, Long> ids = generateIdTrainAndGare(true, AUCUNE_STATION_A_SUPPRIMER_TROUVER);

        if (ids.getVal1() == NEGATIVE_NUMBER) {
            return;
        }
        generateResponse(icm.supprimerStation(ids.getVal1(), ids.getVal2()), STATION_SUPPRIMER_AVEC_SUCCES);
    }

    @Override
    public void supprimerTrain() {
        Long idTrain = getIdTrainFromUser();
        generateResponse(icm.supprimerTrain(idTrain), TRAIN_SUPPRIMER_AVEC_SUCCESS);
    }

    @Override
    public void genererRetard() {
        Tuple<Long, Long> ids = generateIdTrainAndGare(true, ERREUR);
        Integer nombreDeMinute = getNbMinuteForRetard();
        if (ids.getVal1() == NEGATIVE_NUMBER) {
            return;
        }
        generateResponse(icm.genererRetard(ids.getVal1(), ids.getVal2(), nombreDeMinute), RETARD_AJOUTER);
    }

    @Override
    public List<Long> displayAvailableTrainToCli() {
        List<Long> ids = new ArrayList<>();
        List<Train> trains = trainDAO.getAllTrains();
        for (Train train : trains) {

            terminal.println(String.format(CHOIX_TRAIN, train.getId(), train.getNumeroDeTrain(), train.getLigneDeTrainIdLigneDeTrain().getNomLigne()));
            ids.add(train.getId());
        }

        return ids;
    }

    @Override
    public void displayAvailableGareToCli(List<Gare> gares) {
        for (Gare gare : gares) {
            terminal.println(String.format(AFFICHAGE_GARE, gare.getId(), gare.getNomGare()));
        }
    }

    public Tuple<Long, Long> generateIdTrainAndGare(Boolean doitMarquerArret, String msgIfNoGare) {
        long idTrain = getIdTrainFromUser();
        Train train;

        try {
            train = trainDAO.findTrain(idTrain);
        } catch (Exception e) {
            return new Tuple<>(NEGATIVE_LONG, NEGATIVE_LONG);
        }
        List<Gare> gares = new ArrayList<>();

        for (Arret arret : train.getItineraireConcerner().getArrets()) {
            if (Objects.equals(arret.getDoitMarquerArret(), doitMarquerArret)) {
                gares.add(arret.getGareConcerner());
            }
        }
        if (gares.isEmpty()) {
            terminal.println(msgIfNoGare);

            return new Tuple<>(NEGATIVE_LONG, NEGATIVE_LONG);
        }
        terminal.println(QUEL_GARE_EST_CONCERNEE);
        displayAvailableGareToCli(gares);
        Long idGare = textIO.newLongInputReader().withPossibleValues(gares.stream().map(Gare::getId).collect(Collectors.toList())).read("Quel gare?");

        return new Tuple<>(idTrain, idGare);
    }

    private long getIdTrainFromUser() {
        terminal.println(QUEL_TRAIN_EST_CONCERNEE);
        List<Long> idsTrain = displayAvailableTrainToCli();
        return textIO.newLongInputReader().withPossibleValues(idsTrain).read(QUEL_TRAIN);
    }

    private Integer getNbMinuteForRetard() {
        return textIO.newIntInputReader().read(COMBIEN_DE_MINUTE);
    }

    private void generateResponse(Tuple<Boolean, String> reponseICM, String msgIfSuccess) {
        if (Boolean.TRUE.equals(reponseICM.getVal1())) {
            showSuccessMessage(msgIfSuccess);
        } else {
            showErrorMessage(reponseICM.getVal2());
        }
    }

    public void showErrorMessage(String errorMessage) {
        terminal.getProperties().setPromptColor(Color.RED);
        terminal.println(errorMessage);
        terminal.getProperties().setPromptColor(Color.white);
    }

    public void showSuccessMessage(String s) {
        terminal.getProperties().setPromptColor(Color.GREEN);
        terminal.println(s);
        terminal.getProperties().setPromptColor(Color.white);
    }

}
