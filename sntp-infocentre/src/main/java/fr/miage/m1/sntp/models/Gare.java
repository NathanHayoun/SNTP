package fr.miage.m1.sntp.models;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Set;

/**
 * @author Nathan
 */
@Entity
@Table(name = "Gare")
public class Gare {
    /**
     * Primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gare", nullable = false)
    private Long id;
    /**
     * Name of station
     */
    @Column(name = "nom_gare")
    private String nomGare;
    /**
     * Train passing in the station
     */
    @OneToMany(mappedBy = "gareConcerner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonbTransient
    private Set<Arret> trainsQuiPasseDansLaGare;

    /**
     * @return name of station
     */
    public String getNomGare() {
        return nomGare;
    }

    /**
     * @param nomGare
     */
    public void setNomGare(String nomGare) {
        this.nomGare = nomGare;
    }

    /**
     * @return primary key
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

    /**
     * @return trainsQuiPasseDansLaGare
     */
    public Set<Arret> getTrainsQuiPasseDansLaGare() {
        return trainsQuiPasseDansLaGare;
    }

    /**
     * @param trainsQuiPasseDansLaGare
     */
    public void setTrainsQuiPasseDansLaGare(Set<Arret> trainsQuiPasseDansLaGare) {
        this.trainsQuiPasseDansLaGare = trainsQuiPasseDansLaGare;
    }

}