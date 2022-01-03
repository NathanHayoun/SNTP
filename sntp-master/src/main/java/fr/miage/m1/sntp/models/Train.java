package fr.miage.m1.sntp.models;

import javax.persistence.*;

/**
 * @author Nathan
 */
@Entity
@Table(name = "Train")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_train", nullable = false)
    /**
     * Primary key
     */
    private Long id;
    /**
     * nombreDePlace
     */
    @Column(name = "nombre_de_place", nullable = false)
    private Integer nombreDePlace;
    /**
     * numeroDeTrain
     */
    @Column(name = "numero_de_train", nullable = false)
    private Integer numeroDeTrain;
    /**
     * typeDeTrain
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type_de_train")
    private TypeTrain typeDeTrain;
    /**
     * Foreign Key
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ligne_de_train_id_ligne_de_train")
    private LigneDeTrain ligneDeTrainIdLigneDeTrain;
    /**
     * Foreign Key
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_itineraire")
    private Itineraire itineraireConcerner;

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
        for (Arret iti : itineraireConcerner.getArrets()) {
            if (iti.getPosition() == 1) {
                return iti.getGareConcerner().getNomGare();
            }
        }
        return "Error";
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
}