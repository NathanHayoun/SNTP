package fr.miage.m1.sntp;

import fr.miage.m1.sntp.cli.ChoixUtilisateur;
import fr.miage.m1.sntp.cli.UserInterfaceCLI;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import picocli.CommandLine;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;

@CommandLine.Command(name = "greeting", mixinStandardHelpOptions = true)
public class Main implements Runnable {
    public static final String CHOISIR_UNE_ACTION = "Choisir une action";

    @Inject
    UserInterfaceCLI cli;

    @Override
    @ActivateRequestContext
    public void run() {
        TextIO textIO = TextIoFactory.getTextIO();

        while (true) {
            try {
                ChoixUtilisateur choixUtilisateur = textIO.newEnumInputReader(ChoixUtilisateur.class).read(CHOISIR_UNE_ACTION);

                switch (choixUtilisateur) {
                    case Ajouter_Une_Station:
                        ajouterStationForTrain();
                        break;
                    case Supprimer_Une_Station:
                        supprimerStationForTrain();
                        break;
                    case Supprimer_Un_Train:
                        supprimerTrain();
                        break;
                    case Generer_Un_Retard:
                        genererRetard();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                break;
            }
        }
    }

    @Dependent
    @ActivateRequestContext
    public void ajouterStationForTrain() {
        cli.ajouterStationForTrain();
    }

    @Dependent
    @ActivateRequestContext
    public void supprimerStationForTrain() {
        cli.supprimerStationForTrain();
    }

    @Dependent
    @ActivateRequestContext
    public void supprimerTrain() {
        cli.supprimerTrain();
    }

    @Dependent
    @ActivateRequestContext
    public void genererRetard() {
        cli.genererRetard();
    }
}
