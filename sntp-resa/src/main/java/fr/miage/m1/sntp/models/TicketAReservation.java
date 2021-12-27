package fr.miage.m1.sntp.models;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class TicketAReservation extends Ticket {
    private int place;

    public TicketAReservation(String gareDepart, String gareArrivee, LocalDate dateDepart, LocalTime heureDepart, LocalTime heureArrivee, int numeroEtape) {
        super(gareDepart, gareArrivee, dateDepart, heureDepart, heureArrivee, numeroEtape);
    }

    public TicketAReservation(String gareDepart, String gareArrivee, LocalDate dateDepart, LocalTime heureDepart, LocalTime heureArrivee, int numeroEtape, int place) {
        super(gareDepart, gareArrivee, dateDepart, heureDepart, heureArrivee, numeroEtape);
        this.place = place;
    }

    public TicketAReservation() {
        
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
