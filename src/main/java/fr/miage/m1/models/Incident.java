package fr.miage.m1.models;

import javax.persistence.*;

@Entity
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incident_id", nullable = false)
    private Integer id;

    @Column(name = "incident_name", nullable = false)
    private String incidentName;

    @Column(name = "incident_duration_seconds", nullable = false)
    private Integer incidentDurationSeconds;

    public Integer getIncidentDurationSeconds() {
        return incidentDurationSeconds;
    }

    public void setIncidentDurationSeconds(Integer incidentDurationSeconds) {
        this.incidentDurationSeconds = incidentDurationSeconds;
    }

    public String getIncidentName() {
        return incidentName;
    }

    public void setIncidentName(String incidentName) {
        this.incidentName = incidentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}