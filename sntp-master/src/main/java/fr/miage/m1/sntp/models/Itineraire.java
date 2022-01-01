package fr.miage.m1.sntp.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Itineraire")
public class Itineraire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_itineraire", nullable = false)
    private Long id;

    @OneToOne(mappedBy = "itineraireConcerner")
    @JoinColumn(name = "id_train")
    private Train train;

    @OneToMany(mappedBy = "itineraireConcerner", fetch = FetchType.EAGER)
    private Set<Arret> arrets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Set<Arret> getArrets() {
        return arrets;
    }

    public void setArrets(Set<Arret> arrets) {
        this.arrets = arrets;
    }
}

