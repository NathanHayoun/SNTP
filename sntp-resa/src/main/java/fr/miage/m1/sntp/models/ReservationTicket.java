package fr.miage.m1.sntp.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "Reservation_Ticket", indexes = {
        @Index(name = "UK_eybckd1308uo60ixg0dniqwgl", columnList = "tickets_id_ticket", unique = true)
})
public class ReservationTicket {
    @EmbeddedId
    private ReservationTicketId id;

    public ReservationTicketId getId() {
        return id;
    }

    public void setId(ReservationTicketId id) {
        this.id = id;
    }
}