
/*
 * Copyright (c) 2021. Nathan Hayoun
 */

package fr.miage.m1.sntp.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Itineraire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_itineraire", nullable = false)
    private Long idItineraire;

    @ManyToOne
    @JoinColumn(name = "depart_id_gare")
    private Gare depart;
    @ManyToOne
    @JoinColumn(name = "terminus_id_gare")
    private Gare terminus;
    @OneToMany
    private Set<Station> stations;

    public Itineraire(Set<Station> stations) {
        generateGareDepartEtTerminus(stations);
    }

    public Itineraire() {

    }

    public Long getIdItineraire() {
        return idItineraire;
    }

    public void setIdItineraire(Long idItineraire) {
        this.idItineraire = idItineraire;
    }

    private void generateGareDepartEtTerminus(Set<Station> stations) {
        //NOP
    }

    public Gare getDepart() {
        return depart;
    }

    public void setDepart(Gare depart) {
        this.depart = depart;
    }

    public Gare getTerminus() {
        return terminus;
    }

    public void setTerminus(Gare terminus) {
        this.terminus = terminus;
    }

    public Set<Station> getStations() {
        return stations;
    }

    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return "Itineraire{" +
                "depart=" + depart +
                ", terminus=" + terminus + " " + stations.toString() + " " +
                '}';
    }
}
