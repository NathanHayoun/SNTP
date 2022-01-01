package fr.miage.m1.sntp.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ArretId implements Serializable {
    private static final long serialVersionUID = 8206435527755057412L;
    @Column(name = "id_gare", nullable = false)
    private Long idGare;
    @Column(name = "id_itineraire", nullable = false)
    private Long idItineraire;

    public Long getIdItineraire() {
        return idItineraire;
    }

    public void setIdItineraire(Long idTrain) {
        this.idItineraire = idTrain;
    }

    public Long getIdGare() {
        return idGare;
    }

    public void setIdGare(Long idGare) {
        this.idGare = idGare;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGare, idItineraire);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ArretId entity = (ArretId) o;
        return Objects.equals(this.idGare, entity.idGare) &&
                Objects.equals(this.idItineraire, entity.idItineraire);
    }
}