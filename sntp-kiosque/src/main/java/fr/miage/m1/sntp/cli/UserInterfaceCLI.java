package fr.miage.m1.sntp.cli;

import fr.miage.m1.sntp.dto.ReservationDTO;
import org.beryx.textio.TextIO;

import java.util.function.BiConsumer;

public interface UserInterfaceCLI extends BiConsumer<TextIO, RunnerData>, UserInterface {

    ReservationDTO getReservation(ReservationDTO reservationDTO) throws Exception;
}
