package fr.miage.m1.sntp.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "Gare_LigneDeTrain", indexes = {
        @Index(name = "UK_krtqcofgbd25x4dfoke8bho9", columnList = "ligneDeTrains_id_ligne_de_train", unique = true)
})
public class GareLignedetrain {
    @EmbeddedId
    private GareLignedetrainId id;

    public GareLignedetrainId getId() {
        return id;
    }

    public void setId(GareLignedetrainId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GareLignedetrain{" +
                "id=" + id +
                '}';
    }
}