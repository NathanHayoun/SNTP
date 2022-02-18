package fr.miage.m1.sntp.models;

import javax.json.Json;
import javax.json.JsonArray;
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

    public static final String ID_DE_TRAIN = "idDeTrain";
    public static final String NUMERO_DE_TRAIN = "numeroDeTrain";
    public static final String TYPE_DE_TRAIN = "typeDeTrain";
    public static final String LIGNE_DE_TRAIN = "ligneDeTrain";
    public static final String DEPART = "depart";
    public static final String TERMINUS = "terminus";
    public static final String ARRETS_SUIVANT = "arretsSuivant";
    public static final String DOIT_MARQUER_ARRET = "doit_marquer_arret";
    public static final String POSITION = "position";
    public static final String HEURE_ARRIVEE = "heureArrivee";
    public static final String HEURE_DEPART = "heureDepart";
    public static final String ID_GARE_MAP_ID = "idGare";
    public static final String ID_GARE = "id_gare";
    public static final String ID_ITINERAIRE_MAP_ID = "idItineraire";
    public static final String ID_ITINERAIRE = "id_itineraire";
    public static final String ARRET = "arret";
    @EmbeddedId
    @JsonbTransient
    /**
     * @Id use for relation Many To Many
     */
    private ArretId id;

    @Column(name = DOIT_MARQUER_ARRET, nullable = false)
    /**
     * If the train has to stop
     */
    private Boolean doitMarquerArret;

    /**
     * Position of the stop in relation to the train
     */
    @Column(name = POSITION, nullable = false)
    private Integer position;

    /**
     * Usual arrival time
     */
    @Column(name = HEURE_ARRIVEE)
    private LocalTime heureArrivee;

    /**
     * Usual depart time
     */
    @Column(name = HEURE_DEPART)
    private LocalTime heureDepart;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @MapsId(ID_GARE_MAP_ID)
    @JoinColumn(name = ID_GARE)
    /**
     * Station where the stop is located
     */
    private Gare gareConcerner;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId(ID_ITINERAIRE_MAP_ID)
    @JoinColumn(name = ID_ITINERAIRE)
    @JsonbTransient
    /**
     * Route in which the stop is located
     */
    private Itineraire itineraireConcerner;

    /**
     * Daily passage
     */
    @OneToMany(mappedBy = ARRET, fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    private Set<Passage> passages;

    @Transient
    private Passage passageDuJour;

    @Transient
    private JsonArray links;

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
        infoTrain.put(ID_DE_TRAIN, train.getId());
        infoTrain.put(NUMERO_DE_TRAIN, train.getNumeroDeTrain());
        infoTrain.put(TYPE_DE_TRAIN, train.getTypeDeTrain());
        infoTrain.put(LIGNE_DE_TRAIN, train.getLigneDeTrainIdLigneDeTrain().getNomLigne());
        infoTrain.put(DEPART, train.getStationDepart());
        infoTrain.put(TERMINUS, train.getTerminus());

        List<String> arretsSuivant = new ArrayList<>();
        this.getItineraireConcerner().setArrets(this.getItineraireConcerner().getArrets().stream().sorted(Comparator.comparing(Arret::getPosition)).collect(Collectors.toCollection(LinkedHashSet::new)));

        for (Arret arretSuivant : this.getItineraireConcerner().getArrets()) {
            Passage passageDeCeJour = null;

            for (Passage passage : arretSuivant.getPassages()) {
                if (Objects.equals(passage.getDateDePassage(), LocalDate.now())) {
                    passageDeCeJour = passage;

                    break;
                }
            }
            if (passageDeCeJour == null) {
                continue;
            }
            if (Boolean.TRUE.equals(
                    arretSuivant.getPosition() > this.getPosition() &&
                            passageDeCeJour.getMarquerArret()) &&
                    Boolean.FALSE.equals(passageDeCeJour.getEstSupprime()) || passageDeCeJour.getEstSupprime() == null) {
                arretsSuivant.add(arretSuivant.getGareConcerner().getNomGare());
            }
        }
        infoTrain.put(ARRETS_SUIVANT, arretsSuivant);

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

    public void setLinks(JsonArray links) {
        this.links = links;
    }

    public JsonArray getLinks() {
        return links;
    }
}