package fr.miage.m1.sntp.models;

import javax.persistence.*;

@Entity
public class LigneDeTrain {
    @Id
    @Column(name = "id_ligne_de_train", nullable = false)
    private Long id;

    @Column(name = "nomLigne")
    private String nomLigne;

    @ManyToOne
    @JoinColumn(name = "itineraire_id_itineraire")
    private Itineraire itineraireIdItineraire;

    public Itineraire getItineraireIdItineraire() {
        return itineraireIdItineraire;
    }

    public void setItineraireIdItineraire(Itineraire itineraireIdItineraire) {
        this.itineraireIdItineraire = itineraireIdItineraire;
    }

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

    @Override
    public String toString() {
        return "LigneDeTrain{" +
                "id=" + id +
                ", nomLigne='" + nomLigne + '\'' +
                ", itineraireIdItineraire=" + itineraireIdItineraire +
                '}';
    }
}