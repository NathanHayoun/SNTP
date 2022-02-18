package fr.miage.m1.sntp.models;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Ticket {
    public static final String ID_TICKET = "id_ticket";
    public static final String NUMERO_TRAIN = "numero_train";
    public static final String IS_RESERVABLE = "is_reservable";
    public static final String DATE_DEPART = "date_depart";
    public static final String GARE_ARRIVEE = "gare_arrivee";
    public static final String GARE_DEPART = "gare_depart";
    public static final String HEURE_ARRIVEE = "heure_arrivee";
    public static final String HEURE_DEPART = "heure_depart";
    public static final String NUMERO_ETAPE = "numero_etape";
    public static final String PLACE = "place";
    public static final String ID_RESERVATION = "id_reservation";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_TICKET, nullable = false)
    private Long id;
    @Column(name = NUMERO_TRAIN, nullable = false)
    private Integer numeroTrain;
    @Column(name = IS_RESERVABLE, nullable = false)
    private Boolean isReservable;
    @Column(name = DATE_DEPART)
    private LocalDate dateDepart;
    @Column(name = GARE_ARRIVEE)
    private String gareArrivee;
    @Column(name = GARE_DEPART)
    private String gareDepart;
    @Column(name = HEURE_ARRIVEE)
    private LocalTime heureArrivee;
    @Column(name = HEURE_DEPART)
    private LocalTime heureDepart;
    @Column(name = NUMERO_ETAPE, nullable = false)
    private Integer numeroEtape;
    @Column(name = PLACE)
    private Integer place;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = ID_RESERVATION)
    @JsonbTransient
    private Reservation reservationConcernee;

    public Ticket() {
        //NOP
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