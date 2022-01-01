package fr.miage.m1.sntp.models;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

/**
 * @author Nathan
 */
@Entity
@Table(name = "Arret")
public class Arret {

    @EmbeddedId
    @JsonbTransient
    /**
     * @Id use for relation Many To Many
     */
    private ArretId id;

    @Column(name = "doit_marquer_arret", nullable = false)
    /**
     * If the train has to stop
     */
    private Integer doitMarquerArret;

    /**
     * Position of the stop in relation to the train
     */
    @Column(name = "position", nullable = false)
    private Integer position;

    /**
     * Usual arrival time
     */
    @Column(name = "heureArrivee")
    private LocalTime heureArrivee;

    /**
     * Usual depart time
     */
    @Column(name = "heureDepart")
    private LocalTime heureDepart;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idGare")
    @JoinColumn(name = "id_gare")
    /**
     * Station where the stop is located
     */
    private Gare gareConcerner;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idItineraire")
    @JoinColumn(name = "id_itineraire")
    @JsonbTransient
    /**
     * Route in which the stop is located
     */
    private Itineraire itineraireConcerner;

    /**
     * Daily passage
     */
    @OneToMany(mappedBy = "arret", fetch = FetchType.EAGER)
    private Set<Passage> passages;

    /**
     * @return id
     */
    public ArretId getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(ArretId id) {
        this.id = id;
    }

    /**
     * @return doitMarquerArret
     */
    public Integer getDoitMarquerArret() {
        return doitMarquerArret;
    }

    /**
     * @param doitMarquerArret
     */
    public void setDoitMarquerArret(Integer doitMarquerArret) {
        this.doitMarquerArret = doitMarquerArret;
    }

    /**
     * @return position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * @param position
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * @return heureArrivee
     */
    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    /**
     * @param heureArrivee
     */
    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    /**
     * @return heureArrivee
     */
    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    /**
     * @param heureDepart
     */
    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    /**
     * @return gareConcerner
     */
    public Gare getGareConcerner() {
        return gareConcerner;
    }

    /**
     * @param gare
     */
    public void setGareConcerner(Gare gare) {
        this.gareConcerner = gare;
    }

    /**
     * @return itineraireConcerner
     */
    public Itineraire getItineraireConcerner() {
        return itineraireConcerner;
    }

    /**
     * @param itineraireConcerner
     */
    public void setItineraireConcerner(Itineraire itineraireConcerner) {
        this.itineraireConcerner = itineraireConcerner;
    }

    /**
     * @return passages
     */
    public Set<Passage> getPassages() {
        return passages;
    }

    /**
     * @param passages
     */
    public void setPassages(Set<Passage> passages) {
        this.passages = passages;
    }
}