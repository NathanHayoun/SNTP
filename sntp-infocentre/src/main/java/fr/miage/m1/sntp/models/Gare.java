package fr.miage.m1.sntp.models;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Nathan
 */
@Entity
@Table(name = "Gare")
public class Gare {
    public static final String ID_GARE = "id_gare";
    public static final String NOM_GARE = "nom_gare";
    public static final String GARE_CONCERNER = "gareConcerner";
    /**
     * Primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_GARE, nullable = false)
    private Long id;
    /**
     * Name of station
     */
    @Column(name = NOM_GARE)
    private String nomGare;
    /**
     * Train passing in the station
     */
    @OneToMany(mappedBy = GARE_CONCERNER, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
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
        return trainsQuiPasseDansLaGare.stream().sorted(Comparator.comparing(Arret::getHeureArrivee, Comparator.nullsLast(Comparator.reverseOrder()))).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * @param trainsQuiPasseDansLaGare
     */
    public void setTrainsQuiPasseDansLaGare(Set<Arret> trainsQuiPasseDansLaGare) {
        this.trainsQuiPasseDansLaGare = trainsQuiPasseDansLaGare;
    }

}