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
    private static final TextIO textIO = TextIoFactory.getTextIO();
    private static final TextTerminal<?> terminal = textIO.getTextTerminal();
    @Inject
    TrainDAO trainDAO;
    @Inject
    InfoCentreMetier icm;


    @Override
    public void ajouterStationForTrain() {
        Tuple<Long, Long> ids = generateIdTrainAndGare(false, AUCUNE_GARE_A_AJOUTER_DISPONIBLE);

        if (ids.getVal1() == -1) {
            return;
        }
        Tuple<Boolean, String> reponseICM = icm.ajouterStation(ids.getVal1(), ids.getVal2());
        generateResponse(reponseICM, STATION_AJOUTER_AVEC_SUCCESS);
    }

    @Override
    public void supprimerStationForTrain() {
        Tuple<Long, Long> ids = generateIdTrainAndGare(true, AUCUNE_STATION_A_SUPPRIMER_TROUVER);

        if (ids.getVal1() == -1) {
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
        Tuple<Long, Long> ids = generateIdTrainAndGare(true, "Erreur");
        Integer nombreDeMinute = getNbMinuteForRetard();
        if (ids.getVal1() == -1) {
            return;
        }
        generateResponse(icm.genererRetard(ids.getVal1(), ids.getVal2(), nombreDeMinute), RETARD_AJOUTER);
    }

    @Override
    public List<Long> displayAvailableTrainToCli() {
        List<Long> ids = new ArrayList<>();
        List<Train> trains = trainDAO.getAllTrains();
        for (Train train : trains) {
            terminal.println("[ Choix numero : " + train.getId() + " Numéro du train " + train.getNumeroDeTrain() + "  - Ligne : " + train.getLigneDeTrainIdLigneDeTrain().getNomLigne());
            ids.add(train.getId());
        }

        return ids;
    }

    @Override
    public void displayAvailableGareToCli(List<Gare> gares) {
        for (Gare gare : gares) {
            terminal.println("[" + gare.getId() + "] " + gare.getNomGare());
        }
    }

    public Tuple<Long, Long> generateIdTrainAndGare(Boolean doitMarquerArret, String msgIfNoGare) {
        long idTrain = getIdTrainFromUser();
        Train train;

        try {
            train = trainDAO.findTrain(idTrain);
        } catch (Exception e) {
            return new Tuple<>(-1L, -1L);
        }
        List<Gare> gares = new ArrayList<>();

        for (Arret arret : train.getItineraireConcerner().getArrets()) {
            if (Objects.equals(arret.getDoitMarquerArret(), doitMarquerArret)) {
                gares.add(arret.getGareConcerner());
            }
        }
        if (gares.isEmpty()) {
            terminal.println(msgIfNoGare);

            return new Tuple<>(-1L, -1L);
        }
        terminal.println("Quel gare est concernee ?");
        displayAvailableGareToCli(gares);
        Long idGare = textIO.newLongInputReader().withPossibleValues(gares.stream().map(Gare::getId).collect(Collectors.toList())).read("Quel gare?");

        return new Tuple<>(idTrain, idGare);
    }

    private long getIdTrainFromUser() {
        terminal.println("Quel train est concernee ?");
        List<Long> idsTrain = displayAvailableTrainToCli();
        return textIO.newLongInputReader().withPossibleValues(idsTrain).read("Quel train?");
    }

    private Integer getNbMinuteForRetard() {
        return textIO.newIntInputReader().read("De combien de minute parle t'on ?");
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
