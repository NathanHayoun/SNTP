package fr.miage.m1.sntp.models;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Voyageur {
    public static final String ID_VOYAGEUR = "id_voyageur";
    public static final String EMAIL = "email";
    public static final String NOM = "nom";
    public static final String PRENOM = "prenom";
    public static final String VOYAGEUR_CONCERNEE = "voyageurConcernee";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_VOYAGEUR, nullable = false)
    private Long id;

    @Column(name = EMAIL)
    private String email;

    @Column(name = NOM)
    private String nom;

    @Column(name = PRENOM)
    private String prenom;

    @OneToMany(mappedBy = VOYAGEUR_CONCERNEE, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonbTransient
    private Set<Reservation> reservations;

    public Voyageur() {
        //NOP
    }

    public Voyageur(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}