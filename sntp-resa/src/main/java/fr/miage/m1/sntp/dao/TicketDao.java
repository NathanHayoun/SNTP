package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.TicketException;
import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.models.Ticket;
import fr.miage.m1.sntp.models.Voyageur;

import javax.transaction.Transactional;
import java.util.List;

public interface TicketDao {
    List<Ticket> findAll();

    @Transactional
    Ticket findById(long id) throws TicketException;

    void save(Ticket ticket);

    void update(Ticket ticket);

    void delete(Ticket ticket);

    Reservation emitTicketForCustomer(Long ticketId, Voyageur voyageur, Reservation reservation) throws TicketException;

    Long countNbTicketByNumeroTrainAndNow(int numeroDeTrain);

    Long countNbTicketByNumeroTrainAndNowAndHasEtape(int numeroDeTrain);

    List<Voyageur> getEmailsByTrainAndDate(int numeroDeTrain);

}
