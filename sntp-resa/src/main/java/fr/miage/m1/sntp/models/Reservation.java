package fr.miage.m1.sntp.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
public class Reservation {
    @OneToMany
    private Set<Ticket> tickets;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_reservation", nullable = false)
    private Long idReservation;
    private String gareDepart;
    private String gareArrivee;
    private LocalDate dateDeVoyage;
    private LocalDate dateDeReservation;
    private LocalTime heureDeDepart;
    private LocalTime heureArrivee;
    private float prix;
    @ManyToOne
    @JoinColumn(name = "voyageur_id_voyageur")
    private Voyageur voyageur;

    public Reservation(Set<Ticket> tickets) {

        this.tickets = tickets;
    }

    public Reservation(Set<Ticket> tickets, Long idReservation, String gareDepart, String gareArrivee, LocalDate dateDeVoyage, LocalDate dateDeReservation, LocalTime heureDeDepart, LocalTime heureArrivee, float prix, Voyageur voyageur) {
        this.tickets = tickets;
        this.idReservation = idReservation;
        this.gareDepart = gareDepart;
        this.gareArrivee = gareArrivee;
        this.dateDeVoyage = dateDeVoyage;
        this.dateDeReservation = dateDeReservation;
        this.heureDeDepart = heureDeDepart;
        this.heureArrivee = heureArrivee;
        this.prix = prix;
        this.voyageur = voyageur;
    }

    public Reservation() {

    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public Voyageur getVoyageur() {
        return voyageur;
    }

    public void setVoyageur(Voyageur voyageur) {
        this.voyageur = voyageur;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public String getGareDepart() {
        return gareDepart;
    }

    public void setGareDepart(String gareDepart) {
        this.gareDepart = gareDepart;
    }

    public String getGareArrivee() {
        return gareArrivee;
    }

    public void setGareArrivee(String gareArrivee) {
        this.gareArrivee = gareArrivee;
    }

    public LocalDate getDateDeVoyage() {
        return dateDeVoyage;
    }

    public void setDateDeVoyage(LocalDate dateDeVoyage) {
        this.dateDeVoyage = dateDeVoyage;
    }

    public LocalDate getDateDeReservation() {
        return dateDeReservation;
    }

    public void setDateDeReservation(LocalDate dateDeReservation) {
        this.dateDeReservation = dateDeReservation;
    }

    public LocalTime getHeureDeDepart() {
        return heureDeDepart;
    }

    public void setHeureDeDepart(LocalTime heureDeDepart) {
        this.heureDeDepart = heureDeDepart;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}
