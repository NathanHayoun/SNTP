package fr.miage.m1.sntp.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Reservation {
    @Id

    @Column(name = "id_reservation", nullable = false)
    private Long id;

    @Column(name = "dateDeReservation")
    private LocalDate dateDeReservation;

    @Column(name = "dateDeVoyage")
    private LocalDate dateDeVoyage;

    @Column(name = "gareArrivee")
    private String gareArrivee;

    @Column(name = "gareDepart")
    private String gareDepart;

    @Column(name = "heureArrivee")
    private LocalTime heureArrivee;

    @Column(name = "heureDeDepart")
    private LocalTime heureDeDepart;

    @Column(name = "prix", nullable = false)
    private Double prix;

    @ManyToOne
    @JoinColumn(name = "voyageur_id_voyageur")
    private fr.miage.m1.sntp.models.Voyageur voyageurIdVoyageur;

    public fr.miage.m1.sntp.models.Voyageur getVoyageurIdVoyageur() {
        return voyageurIdVoyageur;
    }

    public void setVoyageurIdVoyageur(fr.miage.m1.sntp.models.Voyageur voyageurIdVoyageur) {
        this.voyageurIdVoyageur = voyageurIdVoyageur;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}