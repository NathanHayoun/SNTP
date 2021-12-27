package fr.miage.m1.sntp.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_ticket", nullable = false)
    private Long idTicket;

    private String gareDepart;
    private String gareArrivee;
    private LocalDate dateDepart;
    private LocalTime heureDepart;
    private LocalTime heureArrivee;
    private int numeroEtape;

    public Ticket(String gareDepart, String gareArrivee, LocalDate dateDepart, LocalTime heureDepart, LocalTime heureArrivee, int numeroEtape) {
        this.gareDepart = gareDepart;
        this.gareArrivee = gareArrivee;
        this.dateDepart = dateDepart;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.numeroEtape = numeroEtape;
    }

    public Ticket() {
        
    }

    public Long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Long idTicket) {
        this.idTicket = idTicket;
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

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public int getNumeroEtape() {
        return numeroEtape;
    }

    public void setNumeroEtape(int numeroEtape) {
        this.numeroEtape = numeroEtape;
    }
}
