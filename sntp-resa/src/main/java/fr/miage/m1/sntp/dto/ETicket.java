package fr.miage.m1.sntp.dto;

import java.util.Date;

public class ETicket {
    /*private Long id_ticket;
    private int place;
    private Date date_depart;
    private Date date_arrivee;
    private String gare_depart;
    private String gare_arrivee;
    private int heure_depart;
    private int heure_arrivee;
    private boolean is_reservable;
    private int numero_etape;
    private int numero_train;
    private Long id_reservation;*/

    private String email;
    private String nom;
    private String prenom;
    private boolean is_reservable;
    private Long id_ticket;

    public ETicket() {
    }

    public ETicket(String email, String nom, String prenom, boolean is_reservable, Long id_ticket) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.is_reservable = is_reservable;
        this.id_ticket = id_ticket;
    }

    /*public ETicket(Long id_ticket, int place, Date date_depart, Date date_arrivee, String gare_depart, String gare_arrivee, int heure_depart, int heure_arrivee, boolean is_reservable, int numero_etape, int numero_train, Long id_reservation) {
        this.id_ticket = id_ticket;
        this.place = place;
        this.date_depart = date_depart;
        this.date_arrivee = date_arrivee;
        this.gare_depart = gare_depart;
        this.gare_arrivee = gare_arrivee;
        this.heure_depart = heure_depart;
        this.heure_arrivee = heure_arrivee;
        this.is_reservable = is_reservable;
        this.numero_etape = numero_etape;
        this.numero_train = numero_train;
        this.id_reservation = id_reservation;
    }*/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean isIs_reservable() {
        return is_reservable;
    }

    public void setIs_reservable(boolean is_reservable) {
        this.is_reservable = is_reservable;
    }

    public Long getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(Long id_ticket) {
        this.id_ticket = id_ticket;
    }
}
