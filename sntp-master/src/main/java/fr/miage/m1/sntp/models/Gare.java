/*
 * Copyright (c) 2021. Nathan Hayoun
 */

package fr.miage.m1.sntp.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Gare {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_gare", nullable = false)
    private Long idGare;

    private String nomGare;
    @OneToMany
    private Set<LigneDeTrain> ligneDeTrains;

    public Gare(String nomGare) {
        this.nomGare = nomGare;
        this.ligneDeTrains = null;
    }

    public Gare() {

    }

    public Long getIdGare() {
        return idGare;
    }

    public void setIdGare(Long idGare) {
        this.idGare = idGare;
    }

    public String getNomGare() {
        return nomGare;
    }

    public void setNomGare(String nomGare) {
        this.nomGare = nomGare;
    }

    public Set<LigneDeTrain> getLigneDeTrains() {
        return ligneDeTrains;
    }

    public void setLigneDeTrains(Set<LigneDeTrain> ligneDeTrains) {
        this.ligneDeTrains = ligneDeTrains;
    }

    @Override
    public String toString() {
        return "Gare{" +
                "nomGare='" + nomGare + '\'' +
                ", ligneDeTrains=" + ligneDeTrains +
                '}';
    }
}
