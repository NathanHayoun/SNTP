package fr.miage.m1.sntp.cli;


import fr.miage.m1.sntp.models.Gare;

import java.util.List;

public interface UserInterfaceCLI {

    List<Long> displayAvailableTrainToCli();

    void displayAvailableGareToCli(List<Gare> gares);

    void ajouterStationForTrain();

    void supprimerStationForTrain();

    void supprimerTrain();

    void genererRetard();

    void showErrorMessage(String errorMessage);

    void showSuccessMessage(String s);

}