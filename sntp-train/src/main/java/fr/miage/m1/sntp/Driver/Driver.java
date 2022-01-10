package fr.miage.m1.sntp.Driver;

import fr.miage.m1.sntp.driver.helper.DriverHelper;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

/**
 * Emule un driver présent sur chaque train pour envoyé les coordoness du train en temps réel
 */
@XmlRootElement
@ApplicationScoped
public class Driver {

    private Double latitude;
    private Double longitude;

    public Driver() {
        //Genere une position aléatoire pour émuler un vrai driver
        double minLat = -90.00;
        double maxLat = 90.00;
        this.latitude = minLat + (Math.random() * ((maxLat - minLat) + 1));
        double minLon = 0.00;
        double maxLon = 180.00;
        this.longitude = minLon + (Math.random() * ((maxLon - minLon) + 1));
    }

    public Map<String, Double> getPositition() {
        Map<String, Double> position = new HashMap<>();
        latitude = latitude + 5;
        position.put(DriverHelper.LATITUDE_KEY, latitude);
        longitude = longitude + 5;
        position.put(DriverHelper.LONGITUDE_KEY, longitude);

        return position;
    }
}
