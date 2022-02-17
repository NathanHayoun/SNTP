package fr.miage.m1.sntp.services;

import fr.miage.m1.sntp.dao.ReservationDao;
import fr.miage.m1.sntp.dao.TicketDao;
import fr.miage.m1.sntp.models.Ticket;
import fr.miage.m1.sntp.models.Voyageur;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class TicketServiceImpl implements TicketService {

    @Inject
    TicketDao ticketDao;

    @Inject
    ReservationDao reservationDao;

    @PersistenceContext
    EntityManager em;


    @Override
    public Ticket creerTicket(Ticket ticket) {
        em.persist(ticket);
        return ticket;
    }

    @Override
    public Long countNbTicketByNumeroTrainAndNow(int numeroDeTrain) {
        return ticketDao.countNbTicketByNumeroTrainAndNow(numeroDeTrain);
    }

    @Override
    public Long countNbTicketByNumeroTrainAndNowAndHasEtape(int numeroDeTrain) {
        return ticketDao.countNbTicketByNumeroTrainAndNowAndHasEtape(numeroDeTrain);
    }

    @Override
    public List<Voyageur> getEmailsByTrainAndDate(int numeroDeTrain) {
        return ticketDao.getEmailsByTrainAndDate(numeroDeTrain);
    }

    @Override
    public Ticket getTicket(Long id) {
        return ticketDao.findById(id);
    }

    @Override
    public List<Ticket> getTickets() {
        return ticketDao.findAll();
    }
}
