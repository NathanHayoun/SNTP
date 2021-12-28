package fr.miage.m1.sntp.models;

import javax.persistence.*;

@Entity
public class Train {
    @Id
    @Column(name = "train_id", nullable = false)
    private Long id;

    @Column(name = "nbPlace", nullable = false)
    private Integer nbPlace;

    @Column(name = "numeroDeTrain", nullable = false)
    private Integer numeroDeTrain;

    @Enumerated(EnumType.STRING)
    @Column(name = "typeDeTrain", columnDefinition = "enum (Types#CHAR)")
    private TypeTrain typeDeTrain;

    @ManyToOne
    @JoinColumn(name = "ligne_de_train_id_ligne_de_train")
    private LigneDeTrain ligneDeTrainIdLigneDeTrain;

    public LigneDeTrain getLigneDeTrainIdLigneDeTrain() {
        return ligneDeTrainIdLigneDeTrain;
    }

    public void setLigneDeTrainIdLigneDeTrain(LigneDeTrain ligneDeTrainIdLigneDeTrain) {
        this.ligneDeTrainIdLigneDeTrain = ligneDeTrainIdLigneDeTrain;
    }

    public TypeTrain getTypeDeTrain() {
        return typeDeTrain;
    }

    public void setTypeDeTrain(TypeTrain typeDeTrain) {
        this.typeDeTrain = typeDeTrain;
    }

    public Integer getNumeroDeTrain() {
        return numeroDeTrain;
    }

    public void setNumeroDeTrain(Integer numeroDeTrain) {
        this.numeroDeTrain = numeroDeTrain;
    }

    public Integer getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(Integer nbPlace) {
        this.nbPlace = nbPlace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}