package fr.miage.m1.sntp.services;

import com.google.common.hash.Hashing;
import fr.miage.m1.sntp.dao.ReservationDao;
import fr.miage.m1.sntp.dao.TicketDao;
import fr.miage.m1.sntp.dao.VoyageurDao;
import fr.miage.m1.sntp.dto.ETicket;
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
import java.nio.charset.StandardCharsets;

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

    public String getKeyForTicket(Ticket ticket) {
        return Hashing.sha256().hashString(ticket.getId() + "" + "CodeSecret!", StandardCharsets.UTF_8).toString();
    }

    @Transactional
    public Reservation emitTicket(ETicket eticket, Reservation reservation) throws VoyageurException, TicketException, ReservationException {
        Voyageur voyageur = null;

        try {
            voyageur = voyageurDao.findByEmail(eticket.getEmail());
        } catch (VoyageurException e) {
            voyageur = voyageurDao.createNewVoyageur(eticket.getNom(), eticket.getPrenom(), eticket.getEmail());
        }

        Reservation res = ticketDao.emitTicketForCustomer(eticket.getId_ticket(), voyageur, reservation);
        //        ticket.setTicketKey(this.getKeyForTicket(ticket));
        //        if (eticket.getType().equals(TicketType.SEATING)) {
        //            ticket.setSeatReference(seatPlacementService.bookSeat(ticket.getIdVenue().getId()));
        //        }
        return res;
    }

/*    @Override
    public Ticket getTicket(Long id) {
        return em.find(Ticket.class, id);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return em.merge(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        em.remove(getTicket(id));
    }*/
}
