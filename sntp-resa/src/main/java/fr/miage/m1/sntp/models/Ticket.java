package fr.miage.m1.sntp.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket", nullable = false)
    private Long id;

    @Column(name = "numero_train", nullable = false)
    private Integer numeroTrain;

    @Column(name = "is_reservable", nullable = false)
    private Boolean isReservable;

    @Column(name = "date_depart")
    private LocalDate dateDepart;

    @Column(name = "gare_arrivee")
    private String gareArrivee;

    @Column(name = "gare_depart")
    private String gareDepart;

    @Column(name = "heure_arrivee")
    private LocalTime heureArrivee;

    @Column(name = "heure_depart")
    private LocalTime heureDepart;

    @Column(name = "numero_etape", nullable = false)
    private Integer numeroEtape;

    @Column(name = "place")
    private Integer place;

    @ManyToOne
    @MapsId("idReservation")
    @JoinColumn(name = "id_reservation")
    private Reservation reservationConcernee;

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getNumeroEtape() {
        return numeroEtape;
    }

    public void setNumeroEtape(Integer numeroEtape) {
        this.numeroEtape = numeroEtape;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public String getGareDepart() {
        return gareDepart;
    }

    public void setGareDepart(String gareDepart) {
        this.gareDepart = gareDepart;
    }

    public String getGareArrivee() {
        return gareArrivee;
    }

    public void setGareArrivee(String gareArrivee) {
        this.gareArrivee = gareArrivee;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public boolean getIsReservable() {
        return isReservable;
    }

    public void setIsReservable(Boolean isReservable) {
        this.isReservable = isReservable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroTrain() {
        return numeroTrain;
    }

    public void setNumeroTrain(Integer numeroTrain) {
        this.numeroTrain = numeroTrain;
    }

    public Boolean getReservable() {
        return isReservable;
    }

    public void setReservable(Boolean reservable) {
        isReservable = reservable;
    }

    public Reservation getReservationConcernee() {
        return reservationConcernee;
    }

    public void setReservationConcernee(Reservation reservationConcernee) {
        this.reservationConcernee = reservationConcernee;
    }
}