package fr.miage.m1.sntp.models;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class PlanificationDesTrain {
    @Id
    @Column(name = "id_planification_des_train", nullable = false)
    private Long id;

    @Column(name = "heureArrivee")
    private LocalTime heureArrivee;

    @Column(name = "heureDepart")
    private LocalTime heureDepart;

    @ManyToOne
    @JoinColumn(name = "train_concerne_id")
    private Train trainConcerne;

    public Train getTrainConcerne() {
        return trainConcerne;
    }

    public void setTrainConcerne(Train trainConcerne) {
        this.trainConcerne = trainConcerne;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}