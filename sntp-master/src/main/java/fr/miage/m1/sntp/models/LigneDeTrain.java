package fr.miage.m1.sntp.models;

import javax.persistence.*;

@Entity
@Table(name = "LigneDeTrain")
public class LigneDeTrain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ligne_de_train", nullable = false)
    private Long id;

    @Column(name = "nom_ligne")
    private String nomLigne;

    public String getNomLigne() {
        return nomLigne;
    }

    public void setNomLigne(String nomLigne) {
        this.nomLigne = nomLigne;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}