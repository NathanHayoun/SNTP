package fr.miage.m1.sntp.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "Itineraire_Station", indexes = {
        @Index(name = "UK_kxtiyjdd3nx7cg22eq09igyfj", columnList = "stations_station_id", unique = true)
})
public class ItineraireStation {
    @EmbeddedId
    private ItineraireStationId id;

    public ItineraireStationId getId() {
        return id;
    }

    public void setId(ItineraireStationId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ItineraireStation{" +
                "id=" + id +
                '}';
    }
}