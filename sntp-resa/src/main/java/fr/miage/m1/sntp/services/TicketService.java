package fr.miage.m1.sntp.services;

import fr.miage.m1.sntp.models.Ticket;
import fr.miage.m1.sntp.models.Voyageur;

import java.util.List;

public interface TicketService {

    Ticket getTicket(Long id);

    List<Ticket> getTickets();

    Ticket creerTicket(Ticket ticket);

    Long countNbTicketByNumeroTrainAndNow(int numeroDeTrain);

    Long countNbTicketByNumeroTrainAndNowAndHasEtape(int numeroDeTrain);

    List<Voyageur> getEmailsByTrainAndDate(int numeroDeTrain);
}
