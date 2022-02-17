package fr.miage.m1.sntp.driver;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Etan Guir
 * Emule un driver présent sur chaque train pour envoyé les coordoness du train en temps réel
 */
@XmlRootElement
@ApplicationScoped
public class Driver {
    private static final double LATITUDE_START = 48.85829371711738;
    private static final double LONGITUDE_START = 2.294535456331897;
    private static final double NUMBER_FOR_UPDATE_VALUE = 0.1;
    @ConfigProperty(name = "fr.miage.m1.sntp.numero.train", defaultValue = "2587")
    @XmlAttribute
    String numeroDeTrain;
    @XmlAttribute
    private Double latitude;
    @XmlAttribute
    private Double longitude;

    public Driver() {
        // Génère une position aléatoire pour émuler un vrai driver
        this.latitude = LATITUDE_START;
        this.longitude = LONGITUDE_START;
        this.numeroDeTrain = String.valueOf(numeroDeTrain);
    }

    public void updateValue() {
        this.latitude += NUMBER_FOR_UPDATE_VALUE;
        this.longitude += NUMBER_FOR_UPDATE_VALUE;
    }

    public Double getLatitude() {
        return latitude + NUMBER_FOR_UPDATE_VALUE;
    }

    public Double getLongitude() {
        return longitude + NUMBER_FOR_UPDATE_VALUE;
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
