package fr.miage.m1.sntp.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GareLignedetrainId implements Serializable {
    private static final long serialVersionUID = -6642710815792968169L;
    @Column(name = "Gare_id_gare", nullable = false)
    private Long gareIdGare;
    @Column(name = "ligneDeTrains_id_ligne_de_train", nullable = false)
    private Long lignedetrainsIdLigneDeTrain;

    public Long getLignedetrainsIdLigneDeTrain() {
        return lignedetrainsIdLigneDeTrain;
    }

    public void setLignedetrainsIdLigneDeTrain(Long lignedetrainsIdLigneDeTrain) {
        this.lignedetrainsIdLigneDeTrain = lignedetrainsIdLigneDeTrain;
    }

    public Long getGareIdGare() {
        return gareIdGare;
    }

    public void setGareIdGare(Long gareIdGare) {
        this.gareIdGare = gareIdGare;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lignedetrainsIdLigneDeTrain, gareIdGare);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GareLignedetrainId entity = (GareLignedetrainId) o;
        return Objects.equals(this.lignedetrainsIdLigneDeTrain, entity.lignedetrainsIdLigneDeTrain) &&
                Objects.equals(this.gareIdGare, entity.gareIdGare);
    }

    @Override
    public String toString() {
        return "GareLignedetrainId{" +
                "gareIdGare=" + gareIdGare +
                ", lignedetrainsIdLigneDeTrain=" + lignedetrainsIdLigneDeTrain +
                '}';
    }
}