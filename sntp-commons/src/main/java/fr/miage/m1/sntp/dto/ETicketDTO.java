package fr.miage.m1.sntp.dto;

import java.util.Date;

public class ETicket {

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
