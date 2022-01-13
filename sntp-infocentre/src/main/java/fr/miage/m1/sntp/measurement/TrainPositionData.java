package fr.miage.m1.sntp.measurement;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Measurement(name = "train")
public class TrainPositionData implements Serializable {
    @Column(name = "NUMERO_TRAIN", tag = true)
    @XmlAttribute(name = "numeroDeTrain")
    private String numeroDeTrain;
    @Column(name = "latitude")
    @XmlAttribute(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    @XmlAttribute(name = "longitude")
    private Double longitude;

    public TrainPositionData() {
    }

    public String getNumeroDeTrain() {
        return numeroDeTrain;
    }

    public void setNumeroDeTrain(String numeroDeTrain) {
        this.numeroDeTrain = numeroDeTrain;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = Double.parseDouble(latitude);
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = Double.parseDouble(longitude);
    }

    @Override
    public String toString() {
        return "TrainPositionData{" +
                "numeroDeTrain=" + numeroDeTrain +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
