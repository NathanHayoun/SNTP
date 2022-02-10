package fr.miage.m1.sntp.models;

import javax.json.bind.annotation.JsonbTransient;
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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_reservation")
    @JsonbTransient
    private Reservation reservationConcernee;

    public Ticket() {
    }

    public Integer getPlace() {
        return place;
    }

    public Ticket setPlace(Integer place) {
        this.place = place;
        return this;
    }

    public Integer getNumeroEtape() {
        return numeroEtape;
    }

    public Ticket setNumeroEtape(Integer numeroEtape) {
        this.numeroEtape = numeroEtape;

        return this;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public Ticket setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
        return this;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public Ticket setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
        return this;
    }

    public String getGareDepart() {
        return gareDepart;
    }

    public Ticket setGareDepart(String gareDepart) {
        this.gareDepart = gareDepart;
        return this;
    }

    public String getGareArrivee() {
        return gareArrivee;
    }

    public Ticket setGareArrivee(String gareArrivee) {
        this.gareArrivee = gareArrivee;
        return this;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public Ticket setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
        return this;
    }

    public boolean getIsReservable() {
        return isReservable;
    }

    public Ticket setIsReservable(Boolean isReservable) {
        this.isReservable = isReservable;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Ticket setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getNumeroTrain() {
        return numeroTrain;
    }

    public Ticket setNumeroTrain(Integer numeroTrain) {
        this.numeroTrain = numeroTrain;
        return this;
    }

    public Boolean getReservable() {
        return isReservable;
    }

    public Reservation getReservationConcernee() {
        return reservationConcernee;
    }

    public Ticket setReservationConcernee(Reservation reservationConcernee) {
        this.reservationConcernee = reservationConcernee;
        return this;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                ", numeroTrain=" + numeroTrain +
                ", isReservable=" + isReservable +
                ", dateDepart=" + dateDepart +
                ", gareArrivee='" + gareArrivee + '\'' +
                ", gareDepart='" + gareDepart + '\'' +
                ", heureArrivee=" + heureArrivee +
                ", heureDepart=" + heureDepart +
                ", numeroEtape=" + numeroEtape +
                ", place=" + place +
                '}';
    }
}