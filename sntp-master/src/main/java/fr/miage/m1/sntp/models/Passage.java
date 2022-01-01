package fr.miage.m1.sntp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Passage")
public class Passage extends Arret {

    @Column(name = "date_de_passage", nullable = false)
    private LocalDate dateDePassage;

    @Column(name = "numero_de_quai")
    private Integer numeroDeQuai;

    public LocalDate getDateDePassage() {
        return dateDePassage;
    }

    public void setDateDePassage(LocalDate dateDePassage) {
        this.dateDePassage = dateDePassage;
    }

    public Integer getNumeroDeQuai() {
        return numeroDeQuai;
    }

    public void setNumeroDeQuai(Integer numeroDeQuai) {
        this.numeroDeQuai = numeroDeQuai;
    }
}