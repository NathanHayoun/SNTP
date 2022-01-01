package fr.miage.m1.sntp.models;

import javax.persistence.*;

/**
 * @author Nathan
 */
@Entity
@Table(name = "LigneDeTrain")
public class LigneDeTrain {
    /**
     * Primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ligne_de_train", nullable = false)
    private Long id;

    /**
     * Name of line
     */
    @Column(name = "nom_ligne")
    private String nomLigne;

    /**
     * @return nomLigne
     */
    public String getNomLigne() {
        return nomLigne;
    }

    /**
     * @param nomLigne
     */
    public void setNomLigne(String nomLigne) {
        this.nomLigne = nomLigne;
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

}