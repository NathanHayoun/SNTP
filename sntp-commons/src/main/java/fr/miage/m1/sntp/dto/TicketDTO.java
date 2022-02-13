package fr.miage.m1.sntp.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TicketDTO {

    private Integer numeroTrain;
    private boolean isReservable;
    private LocalDate dateDeDepart;
    private String gareArrivee;
    private String gareDepart;
    private LocalTime heureArrivee;
    private LocalTime heureDepart;
    private int numeroEtape;
    private int place;

    public Integer getNumeroTrain() {
        return numeroTrain;
    }

    public void setNumeroTrain(Integer numeroTrain) {
        this.numeroTrain = numeroTrain;
    }

    public boolean isReservable() {
        return isReservable;
    }

    public void setReservable(boolean reservable) {
        isReservable = reservable;
    }

    public LocalDate getDateDeDepart() {
        return dateDeDepart;
    }

    public void setDateDeDepart(LocalDate dateDeDepart) {
        this.dateDeDepart = dateDeDepart;
    }

    public String getGareArrivee() {
        return gareArrivee;
    }

    public void setGareArrivee(String gareArrivee) {
        this.gareArrivee = gareArrivee;
    }

    public String getGareDepart() {
        return gareDepart;
    }

    public void setGareDepart(String gareDepart) {
        this.gareDepart = gareDepart;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public int getNumeroEtape() {
        return numeroEtape;
    }

    public void setNumeroEtape(int numeroEtape) {
        this.numeroEtape = numeroEtape;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
