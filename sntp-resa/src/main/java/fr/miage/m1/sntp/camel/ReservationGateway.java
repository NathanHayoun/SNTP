package fr.miage.m1.sntp.camel;

import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.services.ReservationService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ReservationGateway {

    @Inject
    ReservationService bookingService;

    public Reservation book(Reservation reservationRequest) {
        return bookingService.createReservation(reservationRequest);
    }


}
