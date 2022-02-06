package fr.miage.m1.sntp.dto;

import com.fasterxml.jackson.annotation.*;

public class TrainDTO {
    private Integer numeroDeTrain;
    private String terminus;
    private String typeDeTrain;
    private String ligneDeTrain;
    private String depart;
    private ItineraireDTO itineraire;

    @JsonProperty("numeroDeTrain")
    public long getNumeroDeTrain() {
        return numeroDeTrain;
    }

    @JsonProperty("numeroDeTrain")
    public void setNumeroDeTrain(int value) {
        this.numeroDeTrain = value;
    }

    @JsonProperty("terminus")
    public String getTerminus() {
        return terminus;
    }

    @JsonProperty("terminus")
    public void setTerminus(String value) {
        this.terminus = value;
    }

    @JsonProperty("typeDeTrain")
    public String getTypeDeTrain() {
        return typeDeTrain;
    }

    @JsonProperty("typeDeTrain")
    public void setTypeDeTrain(String value) {
        this.typeDeTrain = value;
    }

    @JsonProperty("lingeDeTrain")
    public String getLigneDeTrain() {
        return ligneDeTrain;
    }

    @JsonProperty("lingeDeTrain")
    public void setLigneDeTrain(String value) {
        this.ligneDeTrain = value;
    }

    @JsonProperty("depart")
    public String getDepart() {
        return depart;
    }

    @JsonProperty("depart")
    public void setDepart(String value) {
        this.depart = value;
    }

    @JsonProperty("itineraire")
    public ItineraireDTO getItineraire() {
        return itineraire;
    }

    @JsonProperty("itineraire")
    public void setItineraire(ItineraireDTO itineraire) {
        this.itineraire = itineraire;
    }


    @Override
    public String toString() {
        return "TrainDTO{" +
                "numeroDeTrain=" + numeroDeTrain +
                ", terminus='" + terminus + '\'' +
                ", typeDeTrain='" + typeDeTrain + '\'' +
                ", lingeDeTrain='" + ligneDeTrain + '\'' +
                ", depart='" + depart + '\'' +
                '}';
    }
}
