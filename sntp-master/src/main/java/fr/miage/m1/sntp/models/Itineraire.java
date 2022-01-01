package fr.miage.m1.sntp.models;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Set;

/**
 * @author Nathan
 */
@Entity
@Table(name = "Itineraire")
public class Itineraire {
    /**
     * Primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_itineraire", nullable = false)
    private Long id;
    /**
     * Train
     */
    @OneToOne(mappedBy = "itineraireConcerner")
    @JoinColumn(name = "id_train")
    @JsonbTransient
    private Train train;

    /**
     * arrets
     */
    @OneToMany(mappedBy = "itineraireConcerner", fetch = FetchType.EAGER)
    private Set<Arret> arrets;

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
     * @return train
     */
    public Train getTrain() {
        return train;
    }

    /**
     * @param train
     */
    public void setTrain(Train train) {
        this.train = train;
    }

    /**
     * @return arrets
     */
    public Set<Arret> getArrets() {
        return arrets;
    }

    /**
     * @param arrets
     */
    public void setArrets(Set<Arret> arrets) {
        this.arrets = arrets;
    }
}

