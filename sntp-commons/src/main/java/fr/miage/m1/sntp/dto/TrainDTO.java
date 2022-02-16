package fr.miage.m1.sntp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TrainDTO {
    public static final String NUMERO_DE_TRAIN = "numeroDeTrain";
    public static final String TERMINUS = "terminus";
    public static final String TYPE_DE_TRAIN = "typeDeTrain";
    public static final String LIGNE_DE_TRAIN = "ligneDeTrain";
    public static final String DEPART = "depart";
    public static final String ITINERAIRE = "itineraire";
    public static final String ARRETS_SUIVANT = "arretsSuivant";
    private Integer numeroDeTrain;
    private String terminus;
    private String typeDeTrain;
    private String ligneDeTrain;
    private String depart;
    private ItineraireDTO itineraire;
    private List<String> arretsSuivant;

    @JsonProperty(NUMERO_DE_TRAIN)
    public long getNumeroDeTrain() {
        return numeroDeTrain;
    }

    @JsonProperty(NUMERO_DE_TRAIN)
    public void setNumeroDeTrain(int value) {
        this.numeroDeTrain = value;
    }

    @JsonProperty(TERMINUS)
    public String getTerminus() {
        return terminus;
    }

    @JsonProperty(TERMINUS)
    public void setTerminus(String value) {
        this.terminus = value;
    }

    @JsonProperty(TYPE_DE_TRAIN)
    public String getTypeDeTrain() {
        return typeDeTrain;
    }

    @JsonProperty(TYPE_DE_TRAIN)
    public void setTypeDeTrain(String value) {
        this.typeDeTrain = value;
    }

    @JsonProperty(LIGNE_DE_TRAIN)
    public String getLigneDeTrain() {
        return ligneDeTrain;
    }

    @JsonProperty(LIGNE_DE_TRAIN)
    public void setLigneDeTrain(String value) {
        this.ligneDeTrain = value;
    }

    @JsonProperty(DEPART)
    public String getDepart() {
        return depart;
    }

    @JsonProperty(DEPART)
    public void setDepart(String value) {
        this.depart = value;
    }

    @JsonProperty(ITINERAIRE)
    public ItineraireDTO getItineraire() {
        return itineraire;
    }

    @JsonProperty(ITINERAIRE)
    public void setItineraire(ItineraireDTO itineraire) {
        this.itineraire = itineraire;
    }

    @JsonProperty(ARRETS_SUIVANT)
    public List<String> getArretsSuivant() {
        return arretsSuivant;
    }

    @JsonProperty(ARRETS_SUIVANT)
    public void setArretsSuivant(List<String> arretsSuivant) {
        this.arretsSuivant = arretsSuivant;
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
