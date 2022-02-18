package fr.miage.m1.sntp.models;

import javax.json.JsonArray;
import javax.persistence.*;

/**
 * @author Nathan
 */
@Entity
@Table(name = "LigneDeTrain")
public class LigneDeTrain {
    public static final String ID_LIGNE_DE_TRAIN = "id_ligne_de_train";
    public static final String NOM_LIGNE = "nom_ligne";
    /**
     * Primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_LIGNE_DE_TRAIN, nullable = false)
    private Long id;

    /**
     * Name of line
     */
    @Column(name = NOM_LIGNE)
    private String nomLigne;

    @Transient
    private JsonArray links;

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

    public JsonArray getLinks() {
        return links;
    }

    public void setLinks(JsonArray links) {
        this.links = links;
    }

}