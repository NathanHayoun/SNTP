package fr.miage.m1.sntp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Gare {
    @Id
    @Column(name = "id_gare", nullable = false)
    private Long id;

    @Column(name = "nomGare")
    private String nomGare;

    public String getNomGare() {
        return nomGare;
    }

    public void setNomGare(String nomGare) {
        this.nomGare = nomGare;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Gare{" +
                "id=" + id +
                ", nomGare='" + nomGare + '\'' +
                '}';
    }
}