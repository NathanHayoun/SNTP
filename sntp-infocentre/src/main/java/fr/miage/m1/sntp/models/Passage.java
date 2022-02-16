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
    public static final String ID_PASSAGE = "id_passage";
    public static final String ID_GARE = "id_gare";
    public static final String ID_ITINERAIRE = "id_itineraire";
    public static final String HEURE_ARRIVEE_REEL = "heure_arrivee_reel";
    public static final String HEURE_DEPART_REEL = "heure_depart_reel";
    public static final String DATE_DE_PASSAGE = "date_de_passage";
    public static final String NUMERO_DE_QUAI = "numero_de_quai";
    public static final String MARQUER_ARRET = "marquer_arret";
    public static final String EST_SUPPRIME = "est_supprime";
    /**
     * Primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_PASSAGE, nullable = false)
    private Long idPassage;

    /**
     * Foreign key
     */
    @JsonbTransient
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = ID_GARE, insertable = true, updatable = false),
            @JoinColumn(name = ID_ITINERAIRE, insertable = true, updatable = false)
    })
    private Arret arret;
    /**
     * heureArriveeReel
     */
    @Column(name = HEURE_ARRIVEE_REEL)
    private LocalTime heureArriveeReel;
    /**
     * heureDepartReel
     */
    @Column(name = HEURE_DEPART_REEL)
    private LocalTime heureDepartReel;
    /**
     * dateDePassage
     */
    @Column(name = DATE_DE_PASSAGE, nullable = false)
    private LocalDate dateDePassage;
    /**
     * numeroDeQuai
     */
    @Column(name = NUMERO_DE_QUAI)
    private Integer numeroDeQuai;
    /**
     * marquerArret
     */
    @Column(name = MARQUER_ARRET)
    private Boolean marquerArret;

    /**
     * estSupprime
     */
    @Column(name = EST_SUPPRIME)
    private Boolean estSupprime;

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

    /**
     * @return estSupprime
     */
    public Boolean getEstSupprime() {
        return estSupprime;
    }

    /**
     * @param estSupprime
     */
    public void setEstSupprime(Boolean estSupprime) {
        this.estSupprime = estSupprime;
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
                ", estSupprime=" + estSupprime +
                '}';
    }
}