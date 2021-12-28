package fr.miage.m1.sntp.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReservationTicketId implements Serializable {
    private static final long serialVersionUID = 4502120898460579676L;
    @Column(name = "Reservation_id_reservation", nullable = false)
    private Long reservationIdReservation;
    @Column(name = "tickets_id_ticket", nullable = false)
    private Long ticketsIdTicket;

    public Long getTicketsIdTicket() {
        return ticketsIdTicket;
    }

    public void setTicketsIdTicket(Long ticketsIdTicket) {
        this.ticketsIdTicket = ticketsIdTicket;
    }

    public Long getReservationIdReservation() {
        return reservationIdReservation;
    }

    public void setReservationIdReservation(Long reservationIdReservation) {
        this.reservationIdReservation = reservationIdReservation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationIdReservation, ticketsIdTicket);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReservationTicketId entity = (ReservationTicketId) o;
        return Objects.equals(this.reservationIdReservation, entity.reservationIdReservation) &&
                Objects.equals(this.ticketsIdTicket, entity.ticketsIdTicket);
    }
}