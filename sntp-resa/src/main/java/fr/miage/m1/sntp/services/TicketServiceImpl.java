package fr.miage.m1.sntp.services;

import com.google.common.hash.Hashing;
import fr.miage.m1.sntp.dao.TicketDao;
import fr.miage.m1.sntp.dao.VoyageurDao;
import fr.miage.m1.sntp.dto.ETicket;
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

    @Override
    @Transactional
    public String emitTicket(ETicket eticket) {
        Voyageur voyageur = null;

        voyageur = voyageurDao.findByEmail(eticket.getEmail());

/*
        Customer customer = null;
        try {
            customer = customerDAO.findMatchingCustomer(eticket.getEmail());
        } catch (CustomerNotFoundException e) {
            customer = customerDAO.createNewCustomer(eticket.getFname(), eticket.getLname(), eticket.getEmail());
        }

        Ticket ticket = ticketDAO.findTicket(eticket.getTransitionalTicketId());
        if (ticket.getValidUntil().isBefore(Instant.now())) {
            throw new ExpiredTransitionalTicketException(eticket.getTransitionalTicketId());
        }
        ticket = ticketDAO.emitTicketForCustomer(eticket.getTransitionalTicketId(), customer);
        ticket.setTicketKey(this.getKeyForTicket(ticket));
        if (eticket.getType().equals(TicketType.SEATING)) {
            ticket.setSeatReference(seatPlacementService.bookSeat(ticket.getIdVenue().getId()));
        }
        return ticket.getTicketKey();*/


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
