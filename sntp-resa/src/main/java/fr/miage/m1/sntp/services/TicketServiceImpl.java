package fr.miage.m1.sntp.services;

import fr.miage.m1.sntp.dao.ReservationDao;
import fr.miage.m1.sntp.dao.TicketDao;
import fr.miage.m1.sntp.dao.VoyageurDao;
import fr.miage.m1.sntp.dto.ETicketDTO;
import fr.miage.m1.sntp.exceptions.ReservationException;
import fr.miage.m1.sntp.exceptions.TicketException;
import fr.miage.m1.sntp.exceptions.VoyageurException;
import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.models.Ticket;
import fr.miage.m1.sntp.models.Voyageur;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class TicketServiceImpl implements TicketService {
    @Inject
    VoyageurDao voyageurDao;

    @Inject
    TicketDao ticketDao;

    @Inject
    ReservationDao reservationDao;

    @Inject
    PlacementService placementService;

    @PersistenceContext
    EntityManager em;


    @Override
    public Ticket creerTicket(Ticket ticket) {
        em.persist(ticket);
        return ticket;
    }

    @Transactional
    public Reservation emitTicket(ETicketDTO eticket, Reservation reservation) throws VoyageurException, TicketException, ReservationException {
        Voyageur voyageur = null;

        try {
            voyageur = voyageurDao.findByEmail(eticket.getEmail());
        } catch (VoyageurException e) {
            voyageur = voyageurDao.createNewVoyageur(eticket.getNom(), eticket.getPrenom(), eticket.getEmail());
        }

        Reservation res = ticketDao.emitTicketForCustomer(eticket.getId_ticket(), voyageur, reservation);

        return res;
    }

}
