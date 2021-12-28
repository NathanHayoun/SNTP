package fr.miage.m1.sntp.models;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class PlanificationDesTrain {
    @Id
    @Column(name = "id_planification_des_train", nullable = false)
    private Long id;

    @Column(name = "heureArrivee")
    private Instant heureArrivee;

    @Column(name = "heureDepart")
    private Instant heureDepart;

    @ManyToOne
    @JoinColumn(name = "train_concerne_id")
    private Train trainConcerne;

    public Train getTrainConcerne() {
        return trainConcerne;
    }

    public void setTrainConcerne(Train trainConcerne) {
        this.trainConcerne = trainConcerne;
    }

    public Instant getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Instant heureDepart) {
        this.heureDepart = heureDepart;
    }

    public Instant getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(Instant heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PlanificationDesTrain{" +
                "id=" + id +
                ", heureArrivee=" + heureArrivee +
                ", heureDepart=" + heureDepart +
                ", trainConcerne=" + trainConcerne +
                '}';
    }
}