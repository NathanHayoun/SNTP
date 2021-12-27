/*
 * Copyright (c) 2021. Nathan Hayoun
 */

package fr.miage.m1.sntp.models;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Station {
    @OneToMany
    private final Set<PlanificationDesTrain> planificationDesTrain = new LinkedHashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "station_id", nullable = false)
    private Long stationId;
    @ManyToOne
    @JoinColumn(name = "gare_id_gare")
    private Gare gare;
    private int position;
    @ManyToOne
    @JoinColumn(name = "passage_id_planification_des_train")
    private Passage passage;
    private boolean doitMarquerArret;
    private int nbQuais;

    public Station(Gare gare, int position, boolean doitMarquerArret) {
        this.gare = gare;
        this.position = position;
        this.doitMarquerArret = doitMarquerArret;
    }

    public Station() {

    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Passage getPassage() {
        return passage;
    }

    public void setPassage(Passage passage) {
        this.passage = passage;
    }

    public boolean isDoitMarquerArret() {
        return doitMarquerArret;
    }

    public void setDoitMarquerArret(boolean doitMarquerArret) {
        this.doitMarquerArret = doitMarquerArret;
    }

    public Gare getGare() {
        return gare;
    }

    public void setGare(Gare gare) {
        this.gare = gare;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Set<PlanificationDesTrain> getPlanificationDesTrain() {
        return planificationDesTrain;
    }

    public void setPlanificationDesTrain(PlanificationDesTrain planificationDesTrain) {
        this.planificationDesTrain.add(planificationDesTrain);
    }

    public int getNbQuais() {
        return nbQuais;
    }

    public void setNbQuais(int nbQuais) {
        this.nbQuais = nbQuais;
    }

    @Override
    public String toString() {
        return "Station{" +
                "gare=" + gare +
                ", position=" + position +
                ", passage=" + passage +
                ", doitMarquerArret=" + doitMarquerArret + " " +
                '}';
    }
}
