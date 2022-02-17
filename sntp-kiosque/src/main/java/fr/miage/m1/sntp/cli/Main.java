package fr.miage.m1.sntp.cli;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import picocli.CommandLine.Command;

import javax.inject.Inject;

/**
 * @author Quentin Vaillant
 */
@Command(name = "greeting", mixinStandardHelpOptions = true)
public class Main implements Runnable {

    public static final String CHOISSEZ_VOTRE_ACTION = "Bienvenue, choisissez votre action";
    @Inject
    UserInterfaceCLI terminalSNTP;

    @Override
    public void run() {
        TextIO textIO = TextIoFactory.getTextIO();
        terminalSNTP.accept(textIO, new RunnerData(""));
        boolean continueBoucle = true;

        while (continueBoucle) {
            try {
                ChoixUtilisateur choixUtilisateur = textIO.newEnumInputReader(ChoixUtilisateur.class).read(CHOISSEZ_VOTRE_ACTION);

                switch (choixUtilisateur) {
                    case ACHETER_UN_BILLET:
                        terminalSNTP.getReservation(null);
                        break;
                    case ECHANGER_UN_BILLET:
                        terminalSNTP.getEchangerBillet();
                        break;
                    case SORTIR:
                        continueBoucle = false;
                    default:
                        break;
                }
            } catch (Exception e) {
                terminalSNTP.showErrorMessage(e.getMessage());
            }
        }
    }
}
