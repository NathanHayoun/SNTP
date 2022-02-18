package fr.miage.m1.sntp.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Reservation {
    public static final String ID_RESERVATION = "id_reservation";
    public static final String DATE_DE_RESERVATION = "dateDeReservation";
    public static final String PRIX = "prix";
    public static final String ID_VOYAGEUR = "id_voyageur";
    public static final String RESERVATION_CONCERNEE = "reservationConcernee";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_RESERVATION, nullable = false)
    private Long id;
    @Column(name = DATE_DE_RESERVATION)
    private LocalDate dateDeReservation;
    @Column(name = PRIX, nullable = false)
    private Double prix;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = ID_VOYAGEUR)
    private Voyageur voyageurConcernee;
    @OneToMany(mappedBy = RESERVATION_CONCERNEE, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Ticket> tickets;

    public Reservation() {
        //NOP
    }

    public Reservation(LocalDate dateDeReservation, Voyageur voyageurConcernee, Set<Ticket> tickets) {
        this.dateDeReservation = dateDeReservation;
        this.voyageurConcernee = voyageurConcernee;
        this.tickets = tickets;
    }

    public Voyageur getVoyageur() {
        return voyageurConcernee;
    }

    public void setVoyageur(Voyageur voyageurConcernee) {
        this.voyageurConcernee = voyageurConcernee;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public LocalDate getDateDeReservation() {
        return dateDeReservation;
    }

    public void setDateDeReservation(LocalDate dateDeReservation) {
        this.dateDeReservation = dateDeReservation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", dateDeReservation=" + dateDeReservation +
                ", prix=" + prix +
                ", voyageurConcernee=" + voyageurConcernee +
                ", tickets=" + tickets +
                '}';
    }
}