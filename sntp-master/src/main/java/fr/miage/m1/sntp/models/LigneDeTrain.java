
/*
 * Copyright (c) 2021. Nathan Hayoun
 */

package fr.miage.m1.sntp.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class LigneDeTrain {
    private String nomLigne;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_ligne_de_train", nullable = false)
    private Long idLigneDeTrain;
    @ManyToOne
    @JoinColumn(name = "itineraire_id_itineraire")
    private Itineraire itineraire;
    @OneToMany
    private Set<Train> trainQuiEmprunteCetteLigne;

    public LigneDeTrain(String nomLigne) {
        this.nomLigne = nomLigne;
        this.trainQuiEmprunteCetteLigne = null;
    }

    public LigneDeTrain() {

    }

    public Long getIdLigneDeTrain() {
        return idLigneDeTrain;
    }

    public void setIdLigneDeTrain(Long idLigneDeTrain) {
        this.idLigneDeTrain = idLigneDeTrain;
    }

    public String getNomLigne() {
        return nomLigne;
    }

    public void setNomLigne(String nomLigne) {
        this.nomLigne = nomLigne;
    }

    public Itineraire getItineraire() {
        return itineraire;
    }

    public void setItineraire(Itineraire itineraire) {
        this.itineraire = itineraire;
    }

    public Set<Train> getTrainQuiEmprunteCetteLigne() {
        return trainQuiEmprunteCetteLigne;
    }

    public void setTrainQuiEmprunteCetteLigne(Set<Train> trainQuiEmprunteCetteLigne) {
        this.trainQuiEmprunteCetteLigne = trainQuiEmprunteCetteLigne;
    }

    @Override
    public String toString() {
        return this.getNomLigne();
    }
}
