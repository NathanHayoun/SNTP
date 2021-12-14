package fr.miage.m1.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StoppedId implements Serializable {
    private static final long serialVersionUID = -9182600219351536981L;
    @Column(name = "station_id", nullable = false)
    private Integer stationId;
    @Column(name = "train_id", nullable = false)
    private Integer trainId;

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainId, stationId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StoppedId entity = (StoppedId) o;
        return Objects.equals(this.trainId, entity.trainId) &&
                Objects.equals(this.stationId, entity.stationId);
    }
}