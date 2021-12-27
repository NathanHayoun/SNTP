/*
 * Copyright (c) 2021. Nathan Hayoun
 */

package fr.miage.m1.sntp.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class PlanificationDesTrain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_planification_des_train", nullable = false)
    private Long idPlanificationDesTrain;

    @ManyToOne
    @JoinColumn(name = "train_concerne_id")
    private Train trainConcerne;
    private LocalDateTime heureArrivee;
    private LocalDateTime heureDepart;

    public PlanificationDesTrain(Train train, LocalDateTime heureArrivee, LocalDateTime heureDepart) {
        this.heureArrivee = heureArrivee;
        this.heureDepart = heureDepart;
        this.trainConcerne = train;
    }

    public PlanificationDesTrain() {

    }

    public Long getIdPlanificationDesTrain() {
        return idPlanificationDesTrain;
    }

    public void setIdPlanificationDesTrain(Long idPlanificationDesTrain) {
        this.idPlanificationDesTrain = idPlanificationDesTrain;
    }

    public Train getTrainConcerne() {
        return trainConcerne;
    }

    public void setTrainConcerne(Train trainConcerne) {
        this.trainConcerne = trainConcerne;
    }

    public LocalDateTime getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalDateTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public LocalDateTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalDateTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    @Override
    public String toString() {
        return "PlanificationDesTrain{" +
                "trainConcerne=" + trainConcerne +
                ", heureArrivee=" + heureArrivee +
                ", heureDepart=" + heureDepart +
                '}';
    }
}
