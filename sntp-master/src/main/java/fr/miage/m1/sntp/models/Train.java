package fr.miage.m1.sntp.models;

import javax.persistence.*;

@Entity
@Table(name = "Train")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_train", nullable = false)
    private Long id;

    @Column(name = "nombre_de_place", nullable = false)
    private Integer nombreDePlace;

    @Column(name = "numero_de_train", nullable = false)
    private Integer numeroDeTrain;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_de_train")
    private TypeTrain typeDeTrain;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ligne_de_train_id_ligne_de_train")
    private LigneDeTrain ligneDeTrainIdLigneDeTrain;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_itineraire")
    private Itineraire itineraireConcerner;

    public LigneDeTrain getLigneDeTrainIdLigneDeTrain() {
        return ligneDeTrainIdLigneDeTrain;
    }

    public void setLigneDeTrainIdLigneDeTrain(LigneDeTrain ligneDeTrainIdLigneDeTrain) {
        this.ligneDeTrainIdLigneDeTrain = ligneDeTrainIdLigneDeTrain;
    }

    public TypeTrain getTypeDeTrain() {
        return typeDeTrain;
    }

    public void setTypeDeTrain(TypeTrain typeDeTrain) {
        this.typeDeTrain = typeDeTrain;
    }

    public Integer getNumeroDeTrain() {
        return numeroDeTrain;
    }

    public void setNumeroDeTrain(Integer numeroDeTrain) {
        this.numeroDeTrain = numeroDeTrain;
    }

    public Integer getNombreDePlace() {
        return nombreDePlace;
    }

    public void setNombreDePlace(Integer nombreDePlace) {
        this.nombreDePlace = nombreDePlace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getStationDepart() {
//        for (Arrets iti : itineraire) {
//            if (iti.getPosition() == 1) {
//                return iti.getStation().getNomGare();
//            }
//        }
//        return "Error";
//    }
//
//    public String getTerminus() {
//        int maxPosition = 0;
//        String nomGareToReturn = "Error";
//        for (var iti : itineraire) {
//            if (iti.getPosition() > maxPosition) {
//                maxPosition = iti.getPosition();
//                nomGareToReturn = iti.getStation().getNomGare();
//            }
//        }
//        return nomGareToReturn;
//    }


}