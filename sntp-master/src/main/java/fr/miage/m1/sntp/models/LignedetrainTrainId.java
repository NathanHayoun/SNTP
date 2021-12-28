package fr.miage.m1.sntp.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LignedetrainTrainId implements Serializable {
    private static final long serialVersionUID = -8579492548067704090L;
    @Column(name = "LigneDeTrain_id_ligne_de_train", nullable = false)
    private Long lignedetrainIdLigneDeTrain;
    @Column(name = "trainQuiEmprunteCetteLigne_train_id", nullable = false)
    private Long trainquiempruntecetteligneTrainId;

    public Long getTrainquiempruntecetteligneTrainId() {
        return trainquiempruntecetteligneTrainId;
    }

    public void setTrainquiempruntecetteligneTrainId(Long trainquiempruntecetteligneTrainId) {
        this.trainquiempruntecetteligneTrainId = trainquiempruntecetteligneTrainId;
    }

    public Long getLignedetrainIdLigneDeTrain() {
        return lignedetrainIdLigneDeTrain;
    }

    public void setLignedetrainIdLigneDeTrain(Long lignedetrainIdLigneDeTrain) {
        this.lignedetrainIdLigneDeTrain = lignedetrainIdLigneDeTrain;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lignedetrainIdLigneDeTrain, trainquiempruntecetteligneTrainId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LignedetrainTrainId entity = (LignedetrainTrainId) o;
        return Objects.equals(this.lignedetrainIdLigneDeTrain, entity.lignedetrainIdLigneDeTrain) &&
                Objects.equals(this.trainquiempruntecetteligneTrainId, entity.trainquiempruntecetteligneTrainId);
    }

    @Override
    public String toString() {
        return "LignedetrainTrainId{" +
                "lignedetrainIdLigneDeTrain=" + lignedetrainIdLigneDeTrain +
                ", trainquiempruntecetteligneTrainId=" + trainquiempruntecetteligneTrainId +
                '}';
    }
}