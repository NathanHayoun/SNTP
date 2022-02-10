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
                reservationGateway.sendReservation(terminalSNTP.getReservation());
            } catch (Exception e) {
                terminalSNTP.showErrorMessage(e.getMessage());
            }
        }
    }
}
