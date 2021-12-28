package fr.miage.m1.sntp.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "LigneDeTrain_Train", indexes = {
        @Index(name = "UK_s1hasjwspgtltxpd4a7n2nt9q", columnList = "trainQuiEmprunteCetteLigne_train_id", unique = true)
})
public class LignedetrainTrain {
    @EmbeddedId
    private LignedetrainTrainId id;

    public LignedetrainTrainId getId() {
        return id;
    }

    public void setId(LignedetrainTrainId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LignedetrainTrain{" +
                "id=" + id +
                '}';
    }
}