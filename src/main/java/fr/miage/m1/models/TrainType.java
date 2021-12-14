package fr.miage.m1.models;

import javax.persistence.*;

@Entity
@Table(name = "Train_Type")
public class TrainType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_type_id", nullable = false)
    private Integer id;

    @Column(name = "train_type_name", nullable = false)
    private String trainTypeName;

    public TrainType() {
    }

    public TrainType(String trainTypeName) {
        this.trainTypeName = trainTypeName;
    }

    public String getTrainTypeName() {
        return trainTypeName;
    }

    public void setTrainTypeName(String trainTypeName) {
        this.trainTypeName = trainTypeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}