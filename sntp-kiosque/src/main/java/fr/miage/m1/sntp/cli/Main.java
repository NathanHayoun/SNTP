package fr.miage.m1.sntp.cli;

import fr.miage.m1.sntp.camel.gateways.ReservationGateway;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import picocli.CommandLine.Command;

import javax.inject.Inject;

@Command(name = "greeting", mixinStandardHelpOptions = true)
public class Main implements Runnable {

    @Inject
    UserInterfaceCLI terminalSNTP;

    @Inject
    ReservationGateway reservationGateway;

    @Override
    public void run() {
        TextIO textIO = TextIoFactory.getTextIO();
        terminalSNTP.accept(textIO, new RunnerData(""));

        while (true) {
            try {
                ChoixUtilisateur choixUtilisateur = textIO.newEnumInputReader(ChoixUtilisateur.class).read("Bienvenu, choissez votre action");
                switch (choixUtilisateur) {
                    case Acheter_Un_Billet:
                        terminalSNTP.getReservation(null);
                        break;
                    case Echanger_Mon_Billet:
                        terminalSNTP.getEchangerBillet();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                terminalSNTP.showErrorMessage(e.getMessage());
            }
        }
    }
}
