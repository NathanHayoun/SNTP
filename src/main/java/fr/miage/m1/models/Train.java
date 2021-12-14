package fr.miage.m1.models;

import javax.persistence.*;

@Entity
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_id", nullable = false)
    private Integer id;

    @Column(name = "train_number", nullable = false)
    private String trainNumber;

    @Column(name = "train_locate")
    private Double trainLocate;

    @Column(name = "number_of_passengers", nullable = false)
    private Integer numberOfPassengers;

    @ManyToOne(optional = false)
    @JoinColumn(name = "train_type_id", nullable = false)
    private TrainType trainType;

    public TrainType getTrainType() {
        return trainType;
    }

    public void setTrainType(TrainType trainType) {
        this.trainType = trainType;
    }

    public Integer getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(Integer numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public Double getTrainLocate() {
        return trainLocate;
    }

    public void setTrainLocate(Double trainLocate) {
        this.trainLocate = trainLocate;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}