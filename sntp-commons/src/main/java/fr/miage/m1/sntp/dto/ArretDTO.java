package fr.miage.m1.sntp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;

public class ArretDTO {
    private Boolean doitMarquerArret;
    private GareDTO gareConcerner;
    private LocalTime heureDepart;
    private LocalTime heureArrivee;
    private PassageDTO passageDuJour;
    private Long position;

    private TrainDTO train;

    @JsonProperty("doitMarquerArret")
    public Boolean getDoitMarquerArret() {
        return doitMarquerArret;
    }

    @JsonProperty("doitMarquerArret")
    public void setDoitMarquerArret(Boolean value) {
        this.doitMarquerArret = value;
    }

    @JsonProperty("gareConcerner")
    public GareDTO getGareConcerner() {
        return gareConcerner;
    }

    @JsonProperty("gareConcerner")
    public void setGareConcerner(GareDTO value) {
        this.gareConcerner = value;
    }

    @JsonProperty("heureDepart")
    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    @JsonProperty("heureDepart")
    public void setHeureDepart(LocalTime value) {
        this.heureDepart = value;
    }

    @JsonProperty("passageDuJour")
    public PassageDTO getPassageDuJour() {
        return passageDuJour;
    }

    @JsonProperty("passageDuJour")
    public void setPassageDuJour(PassageDTO value) {
        this.passageDuJour = value;
    }

    @JsonProperty("position")
    public Long getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(Long value) {
        this.position = value;
    }

    @JsonProperty("train")
    public TrainDTO getTrain() {
        return train;
    }

    @JsonProperty("train")
    public void setTrain(TrainDTO value) {
        this.train = value;
    }

    @JsonProperty("heureArrivee")
    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    @JsonProperty("heureArrivee")
    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    @Override
    public String toString() {
        return "ArretDTO{" +
                "doitMarquerArret=" + doitMarquerArret +
                ", gareConcerner=" + gareConcerner +
                ", heureDepart=" + heureDepart +
                ", heureArrivee=" + heureArrivee +
                ", passageDuJour=" + passageDuJour +
                ", position=" + position +
                ", train=" + train +
                '}';
    }
}