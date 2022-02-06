package fr.miage.m1.sntp.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;

public class PassageDTO {
    private LocalDate dateDePassage;
    private LocalTime heureDepartReel;
    private long idPassage;
    private int numeroDeQuai;
    private TrainDTO train;
    private LocalTime heureArriveeReel;
    private Boolean marquerArret;

    @JsonProperty("dateDePassage")
    public LocalDate getDateDePassage() {
        return dateDePassage;
    }

    @JsonProperty("dateDePassage")
    public void setDateDePassage(LocalDate value) {
        this.dateDePassage = value;
    }

    @JsonProperty("heureDepartReel")
    public LocalTime getHeureDepartReel() {
        return heureDepartReel;
    }

    @JsonProperty("heureDepartReel")
    public void setHeureDepartReel(LocalTime value) {
        this.heureDepartReel = value;
    }

    @JsonProperty("idPassage")
    public long getIDPassage() {
        return idPassage;
    }

    @JsonProperty("idPassage")
    public void setIDPassage(long value) {
        this.idPassage = value;
    }

    @JsonProperty("numeroDeQuai")
    public long getNumeroDeQuai() {
        return numeroDeQuai;
    }

    @JsonProperty("numeroDeQuai")
    public void setNumeroDeQuai(int value) {
        this.numeroDeQuai = value;
    }

    @JsonProperty("train")
    public TrainDTO getTrain() {
        return train;
    }

    @JsonProperty("train")
    public void setTrain(TrainDTO value) {
        this.train = value;
    }

    @JsonProperty("heureArriveeReel")
    public LocalTime getHeureArriveeReel() {
        return heureArriveeReel;
    }

    @JsonProperty("heureArriveeReel")
    public void setHeureArriveeReel(LocalTime value) {
        this.heureArriveeReel = value;
    }

    @JsonProperty("marquerArret")
    public Boolean getMarquerArret() {
        return marquerArret;
    }

    @JsonProperty("marquerArret")
    public void setMarquerArret(Boolean value) {
        this.marquerArret = value;
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