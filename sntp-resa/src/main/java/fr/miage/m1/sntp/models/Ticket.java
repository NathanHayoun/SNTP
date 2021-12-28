package fr.miage.m1.sntp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Ticket {
    @Id
    @Column(name = "id_ticket", nullable = false)
    private Long id;

    @Column(name = "DTYPE", nullable = false, length = 31)
    private String dtype;

    @Column(name = "dateDepart")
    private LocalDate dateDepart;

    @Column(name = "gareArrivee")
    private String gareArrivee;

    @Column(name = "gareDepart")
    private String gareDepart;

    @Column(name = "heureArrivee")
    private LocalTime heureArrivee;

    @Column(name = "heureDepart")
    private LocalTime heureDepart;

    @Column(name = "numeroEtape", nullable = false)
    private Integer numeroEtape;

    @Column(name = "place")
    private Integer place;

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getNumeroEtape() {
        return numeroEtape;
    }

    public void setNumeroEtape(Integer numeroEtape) {
        this.numeroEtape = numeroEtape;
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

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}