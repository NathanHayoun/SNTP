package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.TicketException;
import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.models.Ticket;
import fr.miage.m1.sntp.models.Voyageur;
import fr.miage.m1.sntp.utils.LibSQL;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TicketDaoImpl implements TicketDao {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Ticket> findAll() {
        return LibSQL.findAll(entityManager, Ticket.class);
    }

    @Override
    @Transactional
    public Ticket findById(Long id) throws TicketException {
        return LibSQL.findObject(entityManager,Ticket.class,id);
    }

    @Override
    @Transactional
    public void save(Ticket ticket) {
        LibSQL.insertObject(entityManager,ticket);
    }

    @Override
    @Transactional
    public void update(Ticket ticket) {
        LibSQL.update(entityManager,ticket);
    }

    @Override
    @Transactional
    public void delete(Ticket ticket) {
        LibSQL.deleteObject(entityManager,ticket);
    }

    @Override
    @Transactional
    public Reservation emitTicketForCustomer(Long ticketId, Voyageur voyageur, Reservation reservation) throws TicketException {
        Ticket t = LibSQL.findObject(entityManager, Ticket.class, ticketId);
        Reservation r = LibSQL.findObject(entityManager, Reservation.class, reservation.getId());
        r.setVoyageur(voyageur);
        var listeTickets = r.getTickets();
        listeTickets.add(t);
        r.setTickets(listeTickets);
        return r;
    }
}
