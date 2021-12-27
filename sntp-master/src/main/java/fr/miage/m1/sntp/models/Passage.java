/*
 * Copyright (c) 2021. Nathan Hayoun
 */

package fr.miage.m1.sntp.models;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Passage extends PlanificationDesTrain {
    private LocalDateTime dateDePassage;
    private int numeroDeQuai;

    public Passage(Train train, LocalDateTime heureArrivee, LocalDateTime heureDepart, LocalDateTime dateDePassage) {
        super(train, heureArrivee, heureDepart);
        this.dateDePassage = dateDePassage;
    }

    public Passage() {

    }

    public LocalDateTime getDateDePassage() {
        return dateDePassage;
    }

    public void setDateDePassage(LocalDateTime dateDePassage) {
        this.dateDePassage = dateDePassage;
    }

    public int getNumeroDeQuai() {
        return numeroDeQuai;
    }

    public void setNumeroDeQuai(int numeroDeQuai) {
        this.numeroDeQuai = numeroDeQuai;
    }

    @Override
    public String toString() {
        return "Passage{" +
                "dateDePassage=" + dateDePassage +
                '}';
    }
}
