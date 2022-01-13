package fr.miage.m1.sntp.driver;

import fr.miage.m1.sntp.application.Train;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Emule un driver présent sur chaque train pour envoyé les coordoness du train en temps réel
 */
@XmlRootElement
@ApplicationScoped
public class Driver {
    @XmlAttribute
    private final String numeroDeTrain;
    @XmlAttribute
    private Double latitude;
    @XmlAttribute
    private Double longitude;

    public Driver() {
        //Genere une position aléatoire pour émuler un vrai driver
        this.latitude = 48.85829371711738;
        this.longitude = 2.294535456331897;
        this.numeroDeTrain = String.valueOf(Train.numeroDeTrain);
    }

    public void updateValue() {
        this.latitude += 0.2;
        this.longitude += 0.2;
    }

    public Double getLatitude() {
        return latitude + 2;
    }


    public Double getLongitude() {
        return longitude + 2;
    }

    public String getNumeroDeTrain() {
        return numeroDeTrain;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "numeroDeTrain=" + numeroDeTrain +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
