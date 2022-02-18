package fr.miage.m1.sntp.models;

import javax.json.JsonArray;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.ws.rs.core.Link;
import java.net.URI;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

/**
 * @author Nathan
 */
@Entity
@Table(name = "Train")
public class Train {
    public static final String ID_TRAIN = "id_train";
    public static final String NOMBRE_DE_PLACE = "nombre_de_place";
    public static final String NUMERO_DE_TRAIN = "numero_de_train";
    public static final String TYPE_DE_TRAIN = "type_de_train";
    public static final String LIGNE_DE_TRAIN_ID_LIGNE_DE_TRAIN = "ligne_de_train_id_ligne_de_train";
    public static final String ID_ITINERAIRE = "id_itineraire";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_TRAIN, nullable = false)
    /**
     * Primary key
     */
    private Long id;
    /**
     * nombreDePlace
     */
    @Column(name = NOMBRE_DE_PLACE, nullable = false)
    private Integer nombreDePlace;
    /**
     * numeroDeTrain
     */
    @Column(name = NUMERO_DE_TRAIN, nullable = false)
    private Integer numeroDeTrain;
    /**
     * typeDeTrain
     */
    @Enumerated(EnumType.STRING)
    @Column(name = TYPE_DE_TRAIN)
    private TypeTrain typeDeTrain;
    /**
     * Foreign Key
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = LIGNE_DE_TRAIN_ID_LIGNE_DE_TRAIN)
    private LigneDeTrain ligneDeTrainIdLigneDeTrain;
    /**
     * Foreign Key
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = ID_ITINERAIRE)
    private Itineraire itineraireConcerner;

    @Transient
    private JsonArray links;

    /**
     * @return id of train line
     */
    public LigneDeTrain getLigneDeTrainIdLigneDeTrain() {
        return ligneDeTrainIdLigneDeTrain;
    }

    /**
     * @param ligneDeTrainIdLigneDeTrain
     */
    public void setLigneDeTrainIdLigneDeTrain(LigneDeTrain ligneDeTrainIdLigneDeTrain) {
        this.ligneDeTrainIdLigneDeTrain = ligneDeTrainIdLigneDeTrain;
    }

    /**
     * @return typeDeTrain
     */
    public TypeTrain getTypeDeTrain() {
        return typeDeTrain;
    }

    /**
     * @param typeDeTrain
     */
    public void setTypeDeTrain(TypeTrain typeDeTrain) {
        this.typeDeTrain = typeDeTrain;
    }

    /**
     * @return numeroDeTrain
     */
    public Integer getNumeroDeTrain() {
        return numeroDeTrain;
    }

    /**
     * @param numeroDeTrain
     */
    public void setNumeroDeTrain(Integer numeroDeTrain) {
        this.numeroDeTrain = numeroDeTrain;
    }

    /**
     * @return nombreDePlace
     */
    public Integer getNombreDePlace() {
        return nombreDePlace;
    }

    /**
     * @param nombreDePlace
     */
    public void setNombreDePlace(Integer nombreDePlace) {
        this.nombreDePlace = nombreDePlace;
    }

    /**
     * @return id
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
     * @return itineraireConcerner
     */
    @JsonbTransient
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
     * @return station depart
     */
    public String getStationDepart() {
        this.getItineraireConcerner().setArrets(this.getItineraireConcerner().getArrets().stream().sorted(Comparator.comparing(Arret::getPosition)).collect(Collectors.toCollection(LinkedHashSet::new)));
        Arret arret = (Arret) this.getItineraireConcerner().getArrets().toArray()[0];

        return arret.getGareConcerner().getNomGare();
    }

    /**
     * @return terminus of train
     */
    public String getTerminus() {
        int maxPosition = 0;
        String nomGareToReturn = "Error";
        for (var iti : itineraireConcerner.getArrets()) {
            if (iti.getPosition() > maxPosition) {
                maxPosition = iti.getPosition();
                nomGareToReturn = iti.getGareConcerner().getNomGare();
            }
        }

        return nomGareToReturn;
    }

    public JsonArray getLinks() {
        return links;
    }

    public void setLinks(JsonArray links) {
        this.links = links;
    }

}