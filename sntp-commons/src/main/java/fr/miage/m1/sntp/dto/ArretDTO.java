package fr.miage.m1.sntp.dto;

import java.time.LocalTime;

public class ArretDTO {
    private Boolean doitMarquerArret;
    private GareDTO gareConcerner;
    private LocalTime heureDepart;
    private LocalTime heureArrivee;
    private Integer position;

    public ArretDTO() {
    }

    public ArretDTO(Boolean doitMarquerArret, GareDTO gare, LocalTime heureDepart, LocalTime heureArrivee, Integer position) {
        this.doitMarquerArret = doitMarquerArret;
        this.gareConcerner = gare;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.position = position;
    }

    public Boolean getDoitMarquerArret() {
        return doitMarquerArret;
    }

    public void setDoitMarquerArret(Integer doitMarquerArret) {
        this.doitMarquerArret = doitMarquerArret == 1;
    }

    public GareDTO getGareConcerner() {
        return gareConcerner;
    }

    public void setGareConcerner(GareDTO gareConcerner) {
        this.gareConcerner = gareConcerner;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "ArretDTO{" +
                "doitMarquerArret=" + doitMarquerArret +
                ", gare=" + gareConcerner +
                ", heureDepart=" + heureDepart +
                ", heureArrivee=" + heureArrivee +
                ", position=" + position +
                '}';
    }
}
