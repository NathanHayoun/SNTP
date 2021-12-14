package fr.miage.m1.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Stopped {
    @EmbeddedId
    private StoppedId id;

    @ManyToOne
    @JoinColumn(name = "incident_id")
    private Incident incident;

    @Column(name = "scheduled_date", nullable = false)
    private LocalDate scheduledDate;

    @Column(name = "real_date", nullable = false)
    private LocalDate realDate;

    @Column(name = "scheduled_arrival_hour")
    private LocalTime scheduledArrivalHour;

    @Column(name = "scheduled_real_hour")
    private LocalTime scheduledRealHour;

    @Column(name = "scheduled_departure_hour")
    private LocalTime scheduledDepartureHour;

    @Column(name = "scheduled_departure_real")
    private LocalTime scheduledDepartureReal;

    @Column(name = "train_track_number", nullable = false)
    private Integer trainTrackNumber;

    @Column(name = "scheduled_passengers_numbers", nullable = false)
    private Integer scheduledPassengersNumbers;

    public Integer getScheduledPassengersNumbers() {
        return scheduledPassengersNumbers;
    }

    public void setScheduledPassengersNumbers(Integer scheduledPassengersNumbers) {
        this.scheduledPassengersNumbers = scheduledPassengersNumbers;
    }

    public Integer getTrainTrackNumber() {
        return trainTrackNumber;
    }

    public void setTrainTrackNumber(Integer trainTrackNumber) {
        this.trainTrackNumber = trainTrackNumber;
    }

    public LocalTime getScheduledDepartureReal() {
        return scheduledDepartureReal;
    }

    public void setScheduledDepartureReal(LocalTime scheduledDepartureReal) {
        this.scheduledDepartureReal = scheduledDepartureReal;
    }

    public LocalTime getScheduledDepartureHour() {
        return scheduledDepartureHour;
    }

    public void setScheduledDepartureHour(LocalTime scheduledDepartureHour) {
        this.scheduledDepartureHour = scheduledDepartureHour;
    }

    public LocalTime getScheduledRealHour() {
        return scheduledRealHour;
    }

    public void setScheduledRealHour(LocalTime scheduledRealHour) {
        this.scheduledRealHour = scheduledRealHour;
    }

    public LocalTime getScheduledArrivalHour() {
        return scheduledArrivalHour;
    }

    public void setScheduledArrivalHour(LocalTime scheduledArrivalHour) {
        this.scheduledArrivalHour = scheduledArrivalHour;
    }

    public LocalDate getRealDate() {
        return realDate;
    }

    public void setRealDate(LocalDate realDate) {
        this.realDate = realDate;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    public StoppedId getId() {
        return id;
    }

    public void setId(StoppedId id) {
        this.id = id;
    }
}