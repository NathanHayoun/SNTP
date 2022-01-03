package fr.miage.m1.sntp.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation", nullable = false)
    private Long id;

    @Column(name = "dateDeReservation")
    private LocalDate dateDeReservation;


    @Column(name = "prix", nullable = false)
    private Double prix;

    @ManyToOne
    @JoinColumn(name = "id_voyageur")
    private Voyageur voyageurConcernee;

    @OneToMany(mappedBy="reservationConcernee",fetch = FetchType.EAGER)
    private Set<Ticket> tickets ;

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
}