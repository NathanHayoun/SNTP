/*
 * Copyright (c) 2021. Nathan Hayoun
 */

package fr.miage.m1.sntp.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Train {
    private int numeroDeTrain;
    @ManyToOne
    @JoinColumn(name = "ligne_de_train_id_ligne_de_train")
    private LigneDeTrain ligneDeTrain;
    private int nbPlace;
    @Enumerated(EnumType.ORDINAL)
    private TypeDeTrain typeDeTrain;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "train_id", nullable = false)
    private Long trainId;

    Train(int numeroDeTrain, LigneDeTrain ligneDeTrain, int nbPlace, TypeDeTrain typeDeTrain) {
        this.numeroDeTrain = numeroDeTrain;
        this.ligneDeTrain = ligneDeTrain;
        this.nbPlace = nbPlace;
        this.typeDeTrain = typeDeTrain;
    }

    public Train() {

    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public int numeroDeTrain() {
        return numeroDeTrain;
    }

    public LigneDeTrain ligneDeTrain() {
        return ligneDeTrain;
    }

    public int nbPlace() {
        return nbPlace;
    }

    public TypeDeTrain typeDeTrain() {
        return typeDeTrain;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Train) obj;
        return this.numeroDeTrain == that.numeroDeTrain &&
                Objects.equals(this.ligneDeTrain, that.ligneDeTrain) &&
                this.nbPlace == that.nbPlace &&
                Objects.equals(this.typeDeTrain, that.typeDeTrain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroDeTrain, ligneDeTrain, nbPlace, typeDeTrain);
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public TypeDeTrain getTypeDeTrain() {
        return typeDeTrain;
    }

    public int getNumeroDeTrain() {
        return numeroDeTrain;
    }

    public LigneDeTrain getLigneDeTrain() {
        return ligneDeTrain;
    }

    public Itineraire getItineraire() {
        return this.ligneDeTrain.getItineraire();
    }

    @Override
    public String toString() {
        return "Numero de train " + numeroDeTrain + " ligneDeTrain " + ligneDeTrain.toString() + " itineraire " + getItineraire();
    }
}
