package fr.miage.m1.sntp.camel;

import fr.miage.m1.sntp.dto.ReservationDTO;
import fr.miage.m1.sntp.services.ReservationService;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ReservationGateway {

    @Inject
    ReservationService bookingService;

    public ReservationDTO book(ReservationDTO reservationRequest) {
        return bookingService.reserver(reservationRequest);
    }


}
