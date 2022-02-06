package fr.miage.m1.sntp.cli;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

import javax.inject.Inject;

public class Main implements Runnable {

    @Inject
    UserInterfaceCLI terminalSNTP;

//    @Inject
//    BookingGateway bookingGateway;

    @Override
    public void run() {
        TextIO textIO = TextIoFactory.getTextIO();
        terminalSNTP.accept(textIO, new RunnerData(""));


        while (true) {
            try {
                terminalSNTP.getReservation();
//                Booking booking = eCommerce.getBookingFromOperator();
//                bookingGateway.sendBookingOrder(booking.getStandingTicketsNumber(), booking.getSeatingTicketsNumber(), booking.getVenueId());
            } catch (Exception e) {
                terminalSNTP.showErrorMessage(e.getMessage());
            }
        }
    }
}
