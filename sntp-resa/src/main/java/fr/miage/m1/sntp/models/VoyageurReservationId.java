package fr.miage.m1.sntp.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VoyageurReservationId implements Serializable {
    private static final long serialVersionUID = -1685965452829930455L;
    @Column(name = "Voyageur_id_voyageur", nullable = false)
    private Long voyageurIdVoyageur;
    @Column(name = "reservations_id_reservation", nullable = false)
    private Long reservationsIdReservation;

    public Long getReservationsIdReservation() {
        return reservationsIdReservation;
    }

    public void setReservationsIdReservation(Long reservationsIdReservation) {
        this.reservationsIdReservation = reservationsIdReservation;
    }

    public Long getVoyageurIdVoyageur() {
        return voyageurIdVoyageur;
    }

    public void setVoyageurIdVoyageur(Long voyageurIdVoyageur) {
        this.voyageurIdVoyageur = voyageurIdVoyageur;
    }

    @Override
    public int hashCode() {
        return Objects.hash(voyageurIdVoyageur, reservationsIdReservation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VoyageurReservationId entity = (VoyageurReservationId) o;
        return Objects.equals(this.voyageurIdVoyageur, entity.voyageurIdVoyageur) &&
                Objects.equals(this.reservationsIdReservation, entity.reservationsIdReservation);
    }
}