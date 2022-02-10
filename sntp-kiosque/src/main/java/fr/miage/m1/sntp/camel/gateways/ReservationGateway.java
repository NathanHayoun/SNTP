package fr.miage.m1.sntp.camel.gateways;

import fr.miage.m1.sntp.dto.ReservationDTO;

public interface ReservationGateway {
    void sendReservation(ReservationDTO reservation);
}
