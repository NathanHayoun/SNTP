package fr.miage.m1.sntp.models;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Nathan
 */
@Entity
@Table(name = "Passage")
public class Passage {
    /**
     * Primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_passage", nullable = false)
    private Long idPassage;

    /**
     * Foreign key
     */
    @JsonbTransient
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "id_gare", insertable = true, updatable = false),
            @JoinColumn(name = "id_itineraire", insertable = true, updatable = false)
    })
    private Arret arret;
    /**
     * heureArriveeReel
     */
    @Column(name = "heure_arrivee_reel")
    private LocalTime heureArriveeReel;
    /**
     * heureDepartReel
     */
    @Column(name = "heure_depart_reel")
    private LocalTime heureDepartReel;
    /**
     * dateDePassage
     */
    @Column(name = "date_de_passage", nullable = false)
    private LocalDate dateDePassage;
    /**
     * numeroDeQuai
     */
    @Column(name = "numero_de_quai")
    private Integer numeroDeQuai;
    /**
     * marquerArret
     */
    @Column(name = "marquer_arret")
    private Boolean marquerArret;

    /**
     * @return idPassage
     */
    public Long getIdPassage() {
        return idPassage;
    }

    /**
     * @param idPassage
     */
    public void setIdPassage(Long idPassage) {
        this.idPassage = idPassage;
    }

    /**
     * @return arret
     */
    public Arret getArret() {
        return arret;
    }

    /**
     * @param arret
     */
    public Passage setArret(Arret arret) {
        this.arret = arret;

        return this;
    }

    /**
     * @return heureArriveeReel
     */
    public LocalTime getHeureArriveeReel() {
        return heureArriveeReel;
    }

    /**
     * @param heureArriveeReel
     */
    public Passage setHeureArriveeReel(LocalTime heureArriveeReel) {
        this.heureArriveeReel = heureArriveeReel;

        return this;
    }

    /**
     * @return heureDepartReel
     */
    public LocalTime getHeureDepartReel() {
        return heureDepartReel;
    }

    /**
     * @param heureDepartReel
     */
    public Passage setHeureDepartReel(LocalTime heureDepartReel) {
        this.heureDepartReel = heureDepartReel;

        return this;
    }

    /**
     * @return dateDePassage
     */
    public LocalDate getDateDePassage() {
        return dateDePassage;
    }

    /**
     * @param dateDePassage
     */
    public Passage setDateDePassage(LocalDate dateDePassage) {
        this.dateDePassage = dateDePassage;

        return this;
    }

    /**
     * @return numeroDeQuai
     */
    public Integer getNumeroDeQuai() {
        return numeroDeQuai;
    }

    /**
     * @param numeroDeQuai
     */
    public Passage setNumeroDeQuai(Integer numeroDeQuai) {
        this.numeroDeQuai = numeroDeQuai;

        return this;
    }

    /**
     * @return marquerArret
     */
    public Boolean getMarquerArret() {
        return marquerArret;
    }

    /**
     * @param marquerArret
     */
    public Passage setMarquerArret(Boolean marquerArret) {
        this.marquerArret = marquerArret;

        return this;
    }

    @JsonbTransient
    public Train getTrain() {
        return arret.getItineraireConcerner().getTrain();
    }


    @Override
    public String toString() {
        return "Passage{" +
                "idPassage=" + idPassage +
                ", arret=" + arret +
                ", heureArriveeReel=" + heureArriveeReel +
                ", heureDepartReel=" + heureDepartReel +
                ", dateDePassage=" + dateDePassage +
                ", numeroDeQuai=" + numeroDeQuai +
                ", marquerArret=" + marquerArret +
                '}';
    }
}