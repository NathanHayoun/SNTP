package fr.miage.m1.sntp.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TicketDTO {
    private Integer numeroTrain;
    private boolean isReservable;
    private String gareArrivee;
    private String gareDepart;
    private String heureArrivee;
    private String heureDepart;
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

    public String getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(String heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
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
