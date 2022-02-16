package fr.miage.m1.sntp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nathan Hayoun
 */
public class ItineraireDTO {
    private List<ArretDTO> arrets;
    private long id;

    @JsonProperty("arrets")
    public List<ArretDTO> getArrets() {
        return arrets.stream().sorted(Comparator.comparing(ArretDTO::getPosition)).collect(Collectors.toList());
    }

    @JsonProperty("arrets")
    public void setArrets(List<ArretDTO> value) {
        this.arrets = value;
    }

    @JsonProperty("id")
    public long getID() {
        return id;
    }

    @JsonProperty("id")
    public void setID(long value) {
        this.id = value;
    }
}
