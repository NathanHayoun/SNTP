package fr.miage.m1.sntp.models;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Passage")
public class Passage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_passage", nullable = false)
    private Long idPassage;

    @JsonbTransient
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "id_gare", insertable = false, updatable = false),
            @JoinColumn(name = "id_itineraire", insertable = false, updatable = false)
    })
    private Arret arret;

    @Column(name = "heure_arrivee_reel")
    private LocalTime heureArriveeReel;

    @Column(name = "heure_depart_reel")
    private LocalTime heureDepartReel;

    @Column(name = "date_de_passage", nullable = false)
    private LocalDate dateDePassage;

    @Column(name = "numero_de_quai")
    private Integer numeroDeQuai;

    @Column(name = "marquer_arret")
    private Boolean marquerArret;

    public Long getIdPassage() {
        return idPassage;
    }

    public void setIdPassage(Long idPassage) {
        this.idPassage = idPassage;
    }

    public Arret getArret() {
        return arret;
    }

    public void setArret(Arret arret) {
        this.arret = arret;
    }

    public LocalTime getHeureArriveeReel() {
        return heureArriveeReel;
    }

    public void setHeureArriveeReel(LocalTime heureArriveeReel) {
        this.heureArriveeReel = heureArriveeReel;
    }

    public LocalTime getHeureDepartReel() {
        return heureDepartReel;
    }

    public void setHeureDepartReel(LocalTime heureDepartReel) {
        this.heureDepartReel = heureDepartReel;
    }

    public LocalDate getDateDePassage() {
        return dateDePassage;
    }

    public void setDateDePassage(LocalDate dateDePassage) {
        this.dateDePassage = dateDePassage;
    }

    public Integer getNumeroDeQuai() {
        return numeroDeQuai;
    }

    public void setNumeroDeQuai(Integer numeroDeQuai) {
        this.numeroDeQuai = numeroDeQuai;
    }

    public Boolean getMarquerArret() {
        return marquerArret;
    }

    public void setMarquerArret(Boolean marquerArret) {
        this.marquerArret = marquerArret;
    }
}