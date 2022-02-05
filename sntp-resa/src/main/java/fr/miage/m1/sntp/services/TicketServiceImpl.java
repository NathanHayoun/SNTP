package fr.miage.m1.sntp.services;

import fr.miage.m1.sntp.dao.ReservationDao;
import fr.miage.m1.sntp.dao.TicketDao;
import fr.miage.m1.sntp.dao.VoyageurDao;
import fr.miage.m1.sntp.dto.TicketDTO;
import fr.miage.m1.sntp.exceptions.ReservationException;
import fr.miage.m1.sntp.exceptions.TicketException;
import fr.miage.m1.sntp.exceptions.VoyageurException;
import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.models.Ticket;
import fr.miage.m1.sntp.models.Voyageur;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TicketServiceImpl implements TicketService {
    @Inject
    VoyageurDao voyageurDao;

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

//    @Transactional
//    public Reservation emitTicket(TicketDTO eticket, Reservation reservation) throws VoyageurException, TicketException, ReservationException {
//        Voyageur voyageur = null;
//
//        try {
//            voyageur = voyageurDao.findByEmail(eticket.getEmail());
//        } catch (VoyageurException e) {
//            voyageur = voyageurDao.createNewVoyageur(eticket.getNom(), eticket.getPrenom(), eticket.getEmail());
//        }
//
//        Reservation res = ticketDao.emitTicketForCustomer(eticket.getId_ticket(), voyageur, reservation);
//
//        return res;
//    }

}
