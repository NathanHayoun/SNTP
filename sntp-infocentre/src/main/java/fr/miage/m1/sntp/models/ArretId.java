package fr.miage.m1.sntp.models;

import org.hibernate.Hibernate;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Nathan
 * Use to mapped Arret Many To Many
 */
@Embeddable
public class ArretId implements Serializable {
    /**
     * Many to Many must implement serializable
     */
    private static final long serialVersionUID = 8206435527755057412L;
    /**
     * Composed primary key
     */
    @Column(name = "id_gare", nullable = false)
    @JsonbTransient
    private Long idGare;
    /**
     * Composed primary key
     */
    @Column(name = "id_itineraire", nullable = false)
    @JsonbTransient
    private Long idItineraire;

    /**
     * @return idItineraire
     */
    public Long getIdItineraire() {
        return idItineraire;
    }

    /**
     * @param idTrain
     */
    public void setIdItineraire(Long idTrain) {
        this.idItineraire = idTrain;
    }

    /**
     * @return idGare
     */
    public Long getIdGare() {
        return idGare;
    }

    /**
     * @param idGare
     */
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

    @Override
    public String toString() {
        return "ArretId{" +
                "idGare=" + idGare +
                ", idItineraire=" + idItineraire +
                '}';
    }
}