package fr.miage.m1.sntp.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "Station_PlanificationDesTrain", indexes = {
        @Index(name = "UK_hlo530wg290iqmos2sb5ieh9o", columnList = "planificationDesTrain_id_planification_des_train", unique = true)
})
public class StationPlanificationdestrain {
    @EmbeddedId
    private StationPlanificationdestrainId id;

    public StationPlanificationdestrainId getId() {
        return id;
    }

    public void setId(StationPlanificationdestrainId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StationPlanificationdestrain{" +
                "id=" + id +
                '}';
    }
}