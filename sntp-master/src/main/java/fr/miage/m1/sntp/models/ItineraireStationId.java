package fr.miage.m1.sntp.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItineraireStationId implements Serializable {
    private static final long serialVersionUID = -2255824711037496006L;
    @Column(name = "Itineraire_id_itineraire", nullable = false)
    private Long itineraireIdItineraire;
    @Column(name = "stations_station_id", nullable = false)
    private Long stationsStationId;

    public Long getStationsStationId() {
        return stationsStationId;
    }

    public void setStationsStationId(Long stationsStationId) {
        this.stationsStationId = stationsStationId;
    }

    public Long getItineraireIdItineraire() {
        return itineraireIdItineraire;
    }

    public void setItineraireIdItineraire(Long itineraireIdItineraire) {
        this.itineraireIdItineraire = itineraireIdItineraire;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationsStationId, itineraireIdItineraire);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ItineraireStationId entity = (ItineraireStationId) o;
        return Objects.equals(this.stationsStationId, entity.stationsStationId) &&
                Objects.equals(this.itineraireIdItineraire, entity.itineraireIdItineraire);
    }

    @Override
    public String toString() {
        return "ItineraireStationId{" +
                "itineraireIdItineraire=" + itineraireIdItineraire +
                ", stationsStationId=" + stationsStationId +
                '}';
    }
}