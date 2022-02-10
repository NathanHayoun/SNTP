package fr.miage.m1.sntp.models;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

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
    private Boolean doitMarquerArret;

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @MapsId("idGare")
    @JoinColumn(name = "id_gare")
    /**
     * Station where the stop is located
     */
    private Gare gareConcerner;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
    @OneToMany(mappedBy = "arret", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    private Set<Passage> passages;

    @Transient
    private Passage passageDuJour;

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
    public Boolean getDoitMarquerArret() {
        return doitMarquerArret;
    }

    /**
     * @param doitMarquerArret
     */
    public void setDoitMarquerArret(Boolean doitMarquerArret) {
        this.doitMarquerArret = doitMarquerArret;
    }

    /**
     * @return position
     */
    public Integer getPosition() {
        return position;
    }

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

    public Passage getPassageDuJour() {
        return this.passageDuJour;
    }

    public void setPassageDuJour(Passage passageDuJour) {
        this.passageDuJour = passageDuJour;
    }


    public Map<String, Object> getTrain() {
        Map<String, Object> infoTrain = new HashMap<>();
        Train train = this.getItineraireConcerner().getTrain();
        infoTrain.put("numeroDeTrain", train.getNumeroDeTrain());
        infoTrain.put("typeDeTrain", train.getTypeDeTrain());
        infoTrain.put("ligneDeTrain", train.getLigneDeTrainIdLigneDeTrain().getNomLigne());
        infoTrain.put("depart", train.getStationDepart());
        infoTrain.put("terminus", train.getTerminus());
        List<String> arretsSuivant = new ArrayList<>();
        this.getItineraireConcerner().setArrets(this.getItineraireConcerner().getArrets().stream().sorted(Comparator.comparing(Arret::getPosition)).collect(Collectors.toCollection(LinkedHashSet::new)));

        for (Arret arretSuivant : this.getItineraireConcerner().getArrets()) {
            Passage passageDuJour = null;

            for (Passage passage : arretSuivant.getPassages()) {
                if (Objects.equals(passage.getDateDePassage(), LocalDate.now())) {
                    passageDuJour = passage;

                    break;
                }
            }
            if (passageDuJour == null) {
                continue;
            }
            if (Boolean.TRUE.equals(
                    arretSuivant.getPosition() > this.getPosition() &&
                            passageDuJour.getMarquerArret()) &&
                    Boolean.FALSE.equals(passageDuJour.getEstSupprime())) {
                arretsSuivant.add(arretSuivant.getGareConcerner().getNomGare());
            }
        }
        infoTrain.put("arretsSuivant", arretsSuivant);

        return infoTrain;
    }

    @Override
    public String toString() {
        return "Arret{" +
                "id=" + id +
                ", doitMarquerArret=" + doitMarquerArret +
                ", position=" + position +
                ", heureArrivee=" + heureArrivee +
                ", heureDepart=" + heureDepart +
                ", gareConcerner=" + gareConcerner +
                ", itineraireConcerner=" + itineraireConcerner +
                '}';
    }
}