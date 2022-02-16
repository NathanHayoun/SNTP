package fr.miage.m1.sntp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;

/**
 * @author Nathan Hayoun
 */
public class ArretDTO {
    public static final String DOIT_MARQUER_ARRET = "doitMarquerArret";
    public static final String GARE_CONCERNER = "gareConcerner";
    public static final String HEURE_DEPART = "heureDepart";
    public static final String PASSAGE_DU_JOUR = "passageDuJour";
    public static final String POSITION = "position";
    public static final String TRAIN = "train";
    public static final String HEURE_ARRIVEE = "heureArrivee";
    private Boolean doitMarquerArret;
    private GareDTO gareConcerner;
    private LocalTime heureDepart;
    private LocalTime heureArrivee;
    private PassageDTO passageDuJour;
    private Long position;
    private TrainDTO train;

    @JsonProperty(DOIT_MARQUER_ARRET)
    public Boolean getDoitMarquerArret() {
        return doitMarquerArret;
    }

    @JsonProperty(DOIT_MARQUER_ARRET)
    public void setDoitMarquerArret(Boolean value) {
        this.doitMarquerArret = value;
    }

    @JsonProperty(GARE_CONCERNER)
    public GareDTO getGareConcerner() {
        return gareConcerner;
    }

    @JsonProperty(GARE_CONCERNER)
    public void setGareConcerner(GareDTO value) {
        this.gareConcerner = value;
    }

    @JsonProperty(HEURE_DEPART)
    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    @JsonProperty(HEURE_DEPART)
    public void setHeureDepart(LocalTime value) {
        this.heureDepart = value;
    }

    @JsonProperty(PASSAGE_DU_JOUR)
    public PassageDTO getPassageDuJour() {
        return passageDuJour;
    }

    @JsonProperty(PASSAGE_DU_JOUR)
    public void setPassageDuJour(PassageDTO value) {
        this.passageDuJour = value;
    }

    @JsonProperty(POSITION)
    public Long getPosition() {
        return position;
    }

    @JsonProperty(POSITION)
    public void setPosition(Long value) {
        this.position = value;
    }

    @JsonProperty(TRAIN)
    public TrainDTO getTrain() {
        return train;
    }

    @JsonProperty(TRAIN)
    public void setTrain(TrainDTO value) {
        this.train = value;
    }

    @JsonProperty(HEURE_ARRIVEE)
    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    @JsonProperty(HEURE_ARRIVEE)
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