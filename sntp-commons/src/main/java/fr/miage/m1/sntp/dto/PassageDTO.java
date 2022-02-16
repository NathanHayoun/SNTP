package fr.miage.m1.sntp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Aicha Nur
 */
public class PassageDTO {
    public static final String DATE_DE_PASSAGE = "dateDePassage";
    public static final String HEURE_DEPART_REEL = "heureDepartReel";
    public static final String ID_PASSAGE = "idPassage";
    public static final String NUMERO_DE_QUAI = "numeroDeQuai";
    public static final String TRAIN = "train";
    public static final String HEURE_ARRIVEE_REEL = "heureArriveeReel";
    public static final String MARQUER_ARRET = "marquerArret";
    public static final String EST_SUPPRIME = "estSupprime";
    private LocalDate dateDePassage;
    private LocalTime heureDepartReel;
    private long idPassage;
    private int numeroDeQuai;
    private TrainDTO train;
    private LocalTime heureArriveeReel;
    private Boolean marquerArret;
    private Boolean estSupprime;

    @JsonProperty(DATE_DE_PASSAGE)
    public LocalDate getDateDePassage() {
        return dateDePassage;
    }

    @JsonProperty(DATE_DE_PASSAGE)
    public void setDateDePassage(LocalDate value) {
        this.dateDePassage = value;
    }

    @JsonProperty(HEURE_DEPART_REEL)
    public LocalTime getHeureDepartReel() {
        return heureDepartReel;
    }

    @JsonProperty(HEURE_DEPART_REEL)
    public void setHeureDepartReel(LocalTime value) {
        this.heureDepartReel = value;
    }

    @JsonProperty(ID_PASSAGE)
    public long getIDPassage() {
        return idPassage;
    }

    @JsonProperty(ID_PASSAGE)
    public void setIDPassage(long value) {
        this.idPassage = value;
    }

    @JsonProperty(NUMERO_DE_QUAI)
    public long getNumeroDeQuai() {
        return numeroDeQuai;
    }

    @JsonProperty(NUMERO_DE_QUAI)
    public void setNumeroDeQuai(int value) {
        this.numeroDeQuai = value;
    }

    @JsonProperty(TRAIN)
    public TrainDTO getTrain() {
        return train;
    }

    @JsonProperty(TRAIN)
    public void setTrain(TrainDTO value) {
        this.train = value;
    }

    @JsonProperty(HEURE_ARRIVEE_REEL)
    public LocalTime getHeureArriveeReel() {
        return heureArriveeReel;
    }

    @JsonProperty(HEURE_ARRIVEE_REEL)
    public void setHeureArriveeReel(LocalTime value) {
        this.heureArriveeReel = value;
    }

    @JsonProperty(MARQUER_ARRET)
    public Boolean getMarquerArret() {
        return marquerArret;
    }

    @JsonProperty(MARQUER_ARRET)
    public void setMarquerArret(Boolean value) {
        this.marquerArret = value;
    }

    @JsonProperty(EST_SUPPRIME)
    public Boolean getEstSupprime() {
        return estSupprime;
    }

    @JsonProperty(EST_SUPPRIME)
    public void setEstSupprime(Boolean estSupprime) {
        this.estSupprime = estSupprime;
    }

    @Override
    public String toString() {
        return "PassageDTO{" +
                "dateDePassage=" + dateDePassage +
                ", heureDepartReel=" + heureDepartReel +
                ", idPassage=" + idPassage +
                ", numeroDeQuai=" + numeroDeQuai +
                ", train=" + train +
                ", heureArriveeReel=" + heureArriveeReel +
                ", marquerArret=" + marquerArret +
                '}';
    }

}