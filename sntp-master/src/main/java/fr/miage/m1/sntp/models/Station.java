package fr.miage.m1.sntp.models;

import javax.persistence.*;

@Entity
public class Station {
    @Id
    @Column(name = "station_id", nullable = false)
    private Long id;

    @Column(name = "doitMarquerArret", nullable = false)
    private Boolean doitMarquerArret = false;

    @Column(name = "nbQuais", nullable = false)
    private Integer nbQuais;

    @Column(name = "position", nullable = false)
    private Integer position;

    @ManyToOne
    @JoinColumn(name = "gare_id_gare")
    private Gare gareIdGare;

    public Gare getGareIdGare() {
        return gareIdGare;
    }

    public void setGareIdGare(Gare gareIdGare) {
        this.gareIdGare = gareIdGare;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getNbQuais() {
        return nbQuais;
    }

    public void setNbQuais(Integer nbQuais) {
        this.nbQuais = nbQuais;
    }

    public Boolean getDoitMarquerArret() {
        return doitMarquerArret;
    }

    public void setDoitMarquerArret(Boolean doitMarquerArret) {
        this.doitMarquerArret = doitMarquerArret;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}