package fr.miage.m1.sntp.dto;

import com.fasterxml.jackson.annotation.*;

import java.time.LocalTime;
import java.util.List;

public class ArretDTO {
    private Boolean doitMarquerArret;
    private GareDTO gareConcerner;
    private LocalTime heureDepart;
    private LocalTime heureArrivee;
    private List<PassageDTO> passages;
    private Integer position;
    private TrainDTO train;

    @JsonProperty("doitMarquerArret")
    public Boolean getDoitMarquerArret() {
        return doitMarquerArret;
    }

    @JsonProperty("doitMarquerArret")
    public void setDoitMarquerArret(Integer value) {
        this.doitMarquerArret =  value == 1;
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

    @JsonProperty("passages")
    public List<PassageDTO> getPassages() {
        return passages;
    }

    @JsonProperty("passages")
    public void setPassages(List<PassageDTO> value) {
        this.passages = value;
    }

    @JsonProperty("position")
    public long getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(Integer value) {
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
                ", passages=" + passages +
                ", position=" + position +
                ", train=" + train +
                '}';
    }
}