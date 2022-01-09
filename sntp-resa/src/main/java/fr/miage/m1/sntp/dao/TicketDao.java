package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.exceptions.TicketException;
import fr.miage.m1.sntp.models.Ticket;

import java.util.List;

public interface TicketDao {
    List<Ticket> findAll();
    Ticket findById(int id) throws TicketException;
    void save(Ticket ticket);
    void update(Ticket ticket);
    void delete(Ticket ticket);
}
