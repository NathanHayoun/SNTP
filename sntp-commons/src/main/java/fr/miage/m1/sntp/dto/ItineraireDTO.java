package fr.miage.m1.sntp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nathan Hayoun
 */
public class ItineraireDTO {
    public static final String ARRETS = "arrets";
    public static final String ID = "id";
    private List<ArretDTO> arrets;
    private long id;

    @JsonProperty(ARRETS)
    public List<ArretDTO> getArrets() {
        return arrets.stream().sorted(Comparator.comparing(ArretDTO::getPosition)).collect(Collectors.toList());
    }

    @JsonProperty(ARRETS)
    public void setArrets(List<ArretDTO> value) {
        this.arrets = value;
    }

    @JsonProperty(ID)
    public long getID() {
        return id;
    }

    @JsonProperty(ID)
    public void setID(long value) {
        this.id = value;
    }
}
