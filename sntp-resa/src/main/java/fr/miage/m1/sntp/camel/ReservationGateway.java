package fr.miage.m1.sntp.camel;

import fr.miage.m1.sntp.dto.ReservationDTO;
import fr.miage.m1.sntp.dto.TicketDTO;
import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.models.Ticket;
import fr.miage.m1.sntp.models.Voyageur;
import fr.miage.m1.sntp.services.ReservationService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class ReservationGateway {

    @Inject
    ReservationService bookingService;

    public Reservation book(ReservationDTO reservationRequest) {
        Reservation reservation = new Reservation();
        reservation.setId((long) reservationRequest.getId());
        LocalDate dateDeResa = LocalDate.parse(reservationRequest.getDateDeReservation());
        reservation.setDateDeReservation(dateDeResa);
        reservation.setPrix(reservationRequest.getPrix());
        Voyageur voyageur = new Voyageur();
        voyageur.setEmail(reservationRequest.getVoyageur().getEmail());
        voyageur.setPrenom(reservationRequest.getVoyageur().getPrenom());
        voyageur.setNom(reservationRequest.getVoyageur().getNom());
        reservation.setVoyageur(voyageur);
        Set<Ticket> ticketSet = new HashSet<>();

        for (TicketDTO ticketDTO : reservationRequest.getTickets()) {
            System.out.println(ticketDTO);
            Ticket ticket = new Ticket();
            ticket.setGareArrivee(ticketDTO.getGareArrivee())
                    .setGareDepart(ticketDTO.getGareDepart())
                    .setHeureArrivee(LocalTime.parse(ticketDTO.getHeureArrivee()))
                    .setHeureDepart(LocalTime.parse(ticketDTO.getHeureDepart()))
                    .setIsReservable(ticketDTO.isReservable())
                    .setDateDepart(dateDeResa)
                    .setNumeroEtape(ticketDTO.getNumeroEtape())
                    .setPlace(ticketDTO.getPlace())
                    .setNumeroTrain(ticketDTO.getNumeroTrain())
                    .setReservationConcernee(reservation);
            ticketSet.add(ticket);
        }
        reservation.setTickets(ticketSet);

        return bookingService.createReservation(reservation);
    }


}
