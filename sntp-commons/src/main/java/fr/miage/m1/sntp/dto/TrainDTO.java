package fr.miage.m1.sntp.dto;

import com.fasterxml.jackson.annotation.*;

public class TrainDTO {
    private Integer numeroDeTrain;
    private String terminus;
    private String typeDeTrain;
    private String lingeDeTrain;
    private String depart;

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
    public String getLingeDeTrain() {
        return lingeDeTrain;
    }

    @JsonProperty("lingeDeTrain")
    public void setLingeDeTrain(String value) {
        this.lingeDeTrain = value;
    }

    @JsonProperty("depart")
    public String getDepart() {
        return depart;
    }

    @JsonProperty("depart")
    public void setDepart(String value) {
        this.depart = value;
    }

    @Override
    public String toString() {
        return "TrainDTO{" +
                "numeroDeTrain=" + numeroDeTrain +
                ", terminus='" + terminus + '\'' +
                ", typeDeTrain='" + typeDeTrain + '\'' +
                ", lingeDeTrain='" + lingeDeTrain + '\'' +
                ", depart='" + depart + '\'' +
                '}';
    }
}
