package fr.miage.m1.sntp.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Voyageur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_voyageur", nullable = false)
    private Long idVoyageur;

    private String nom;
    private String prenom;
    private String email;
    @OneToMany
    private Set<Reservation> reservations;

    public Voyageur(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Voyageur() {

    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Long getIdVoyageur() {
        return idVoyageur;
    }

    public void setIdVoyageur(Long idVoyageur) {
        this.idVoyageur = idVoyageur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
