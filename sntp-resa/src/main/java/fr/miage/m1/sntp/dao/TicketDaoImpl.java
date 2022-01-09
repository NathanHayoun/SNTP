package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.TicketException;
import fr.miage.m1.sntp.models.Ticket;
import fr.miage.m1.sntp.utils.LibSQL;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TicketDaoImpl implements TicketDao {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Ticket> findAll() {
        return LibSQL.findAll(entityManager, Ticket.class);
    }

    @Override
    public Ticket findById(int id) throws TicketException {
        return LibSQL.findObject(entityManager,Ticket.class,id);
    }

    @Override
    public void save(Ticket ticket) {
        LibSQL.insertObject(entityManager,ticket);
    }

    @Override
    public void update(Ticket ticket) {
        //NOP
    }

    @Override
    public void delete(Ticket ticket) {
        LibSQL.deleteObject(entityManager,ticket);
    }
}
