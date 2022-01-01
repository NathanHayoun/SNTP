package fr.miage.m1.sntp.models;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalTime;

@Entity
public class Passage {
    @Id
    @Column(name = "id_Passage", nullable = false)
    private Long id;

    @Column(name = "id_planification_des_train", nullable = false)
    private Long idPlanificationDesTrain;

    @Column(name = "dateDePassage")
    private Instant dateDePassage;

    @Column(name = "heureArrivee")
    private LocalTime heureArrivee;

    @Column(name = "heureDepart")
    private LocalTime heureDepart;

    @Column(name = "numeroDeQuai", nullable = false)
    private Integer numeroDeQuai;

    @ManyToOne(optional = false)
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Integer getNumeroDeQuai() {
        return numeroDeQuai;
    }

    public void setNumeroDeQuai(Integer numeroDeQuai) {
        this.numeroDeQuai = numeroDeQuai;
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

    public Instant getDateDePassage() {
        return dateDePassage;
    }

    public void setDateDePassage(Instant dateDePassage) {
        this.dateDePassage = dateDePassage;
    }

    public Long getIdPlanificationDesTrain() {
        return idPlanificationDesTrain;
    }

    public void setIdPlanificationDesTrain(Long idPlanificationDesTrain) {
        this.idPlanificationDesTrain = idPlanificationDesTrain;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}