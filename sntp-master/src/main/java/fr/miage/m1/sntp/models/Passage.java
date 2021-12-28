package fr.miage.m1.sntp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class Passage {
    @Id
    @Column(name = "id_planification_des_train", nullable = false)
    private Long id;

    @Column(name = "dateDePassage")
    private Instant dateDePassage;

    @Column(name = "numeroDeQuai", nullable = false)
    private Integer numeroDeQuai;

    public Integer getNumeroDeQuai() {
        return numeroDeQuai;
    }

    public void setNumeroDeQuai(Integer numeroDeQuai) {
        this.numeroDeQuai = numeroDeQuai;
    }

    public Instant getDateDePassage() {
        return dateDePassage;
    }

    public void setDateDePassage(Instant dateDePassage) {
        this.dateDePassage = dateDePassage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Passage{" +
                "id=" + id +
                ", dateDePassage=" + dateDePassage +
                ", numeroDeQuai=" + numeroDeQuai +
                '}';
    }
}