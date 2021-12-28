package fr.miage.m1.sntp.models;

import javax.persistence.*;

@Entity
public class Itineraire {
    @Id
    @Column(name = "id_itineraire", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "depart_id_gare")
    private Gare departIdGare;

    @ManyToOne
    @JoinColumn(name = "terminus_id_gare")
    private Gare terminusIdGare;

    public Gare getTerminusIdGare() {
        return terminusIdGare;
    }

    public void setTerminusIdGare(Gare terminusIdGare) {
        this.terminusIdGare = terminusIdGare;
    }

    public Gare getDepartIdGare() {
        return departIdGare;
    }

    public void setDepartIdGare(Gare departIdGare) {
        this.departIdGare = departIdGare;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Itineraire{" +
                "id=" + id +
                ", departIdGare=" + departIdGare +
                ", terminusIdGare=" + terminusIdGare +
                '}';
    }
}