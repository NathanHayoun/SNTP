package fr.miage.m1.sntp.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Gare")
public class Gare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gare", nullable = false)
    private Long id;

    @Column(name = "nom_gare")
    private String nomGare;

    @OneToMany(mappedBy = "gareConcerner", fetch = FetchType.EAGER)
    private Set<Arret> trainsQuiPasseDansLaGare;

    public String getNomGare() {
        return nomGare;
    }

    public void setNomGare(String nomGare) {
        this.nomGare = nomGare;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Arret> getTrainsQuiPasseDansLaGare() {
        return trainsQuiPasseDansLaGare;
    }

    public void setTrainsQuiPasseDansLaGare(Set<Arret> trainsQuiPasseDansLaGare) {
        this.trainsQuiPasseDansLaGare = trainsQuiPasseDansLaGare;
    }
}