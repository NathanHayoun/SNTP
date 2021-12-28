package fr.miage.m1.sntp.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StationPlanificationdestrainId implements Serializable {
    private static final long serialVersionUID = 3390979331915005462L;
    @Column(name = "Station_station_id", nullable = false)
    private Long stationStationId;
    @Column(name = "planificationDesTrain_id_planification_des_train", nullable = false)
    private Long planificationdestrainIdPlanificationDesTrain;

    public Long getPlanificationdestrainIdPlanificationDesTrain() {
        return planificationdestrainIdPlanificationDesTrain;
    }

    public void setPlanificationdestrainIdPlanificationDesTrain(Long planificationdestrainIdPlanificationDesTrain) {
        this.planificationdestrainIdPlanificationDesTrain = planificationdestrainIdPlanificationDesTrain;
    }

    public Long getStationStationId() {
        return stationStationId;
    }

    public void setStationStationId(Long stationStationId) {
        this.stationStationId = stationStationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(planificationdestrainIdPlanificationDesTrain, stationStationId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StationPlanificationdestrainId entity = (StationPlanificationdestrainId) o;
        return Objects.equals(this.planificationdestrainIdPlanificationDesTrain, entity.planificationdestrainIdPlanificationDesTrain) &&
                Objects.equals(this.stationStationId, entity.stationStationId);
    }

    @Override
    public String toString() {
        return "StationPlanificationdestrainId{" +
                "stationStationId=" + stationStationId +
                ", planificationdestrainIdPlanificationDesTrain=" + planificationdestrainIdPlanificationDesTrain +
                '}';
    }
}