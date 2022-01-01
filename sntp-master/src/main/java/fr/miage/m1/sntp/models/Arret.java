package fr.miage.m1.sntp.models;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "Arret")
public class Arret {
    @EmbeddedId
    @JsonbTransient
    private ArretId id;

    @Column(name = "doit_marquer_arret", nullable = false)
    private Integer doitMarquerArret;

    @Column(name = "position", nullable = false)
    private Integer position;

    @Column(name = "heureArrivee")
    private LocalTime heureArrivee;

    @Column(name = "heureDepart")
    private LocalTime heureDepart;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idGare")
    @JoinColumn(name = "id_gare")
    private Gare gareConcerner;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idItineraire")
    @JoinColumn(name = "id_itineraire")
    @JsonbTransient
    private Itineraire itineraireConcerner;

    @OneToMany(mappedBy = "arret", fetch = FetchType.EAGER)
    private Set<Passage> passages;

    public ArretId getId() {
        return id;
    }

    public void setId(ArretId id) {
        this.id = id;
    }

    public Integer getDoitMarquerArret() {
        return doitMarquerArret;
    }

    public void setDoitMarquerArret(Integer doitMarquerArret) {
        this.doitMarquerArret = doitMarquerArret;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public Gare getGareConcerner() {
        return gareConcerner;
    }

    public void setGareConcerner(Gare gare) {
        this.gareConcerner = gare;
    }

    public Itineraire getItineraireConcerner() {
        return itineraireConcerner;
    }

    public void setItineraireConcerner(Itineraire itineraireConcerner) {
        this.itineraireConcerner = itineraireConcerner;
    }

    public Set<Passage> getPassages() {
        return passages;
    }

    public void setPassages(Set<Passage> passages) {
        this.passages = passages;
    }
}