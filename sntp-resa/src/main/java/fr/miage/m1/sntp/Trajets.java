package fr.miage.m1.sntp;

import fr.miage.m1.sntp.dto.ArretDTO;
import fr.miage.m1.sntp.dto.ItineraireDTO;
import fr.miage.m1.sntp.services.ArretService;
import fr.miage.m1.sntp.services.ItineraireService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trajets {
    private String villeDepart;
    private String villeArrivee;
    @Inject
    @RestClient
    ItineraireService itineraireService;

    public Trajets(String villeDepart, String villeArrivee) {
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    @Override
    public String toString() {
        return "Trajets{" +
                "villeDepart='" + villeDepart + '\'' +
                ", villeArrivee='" + villeArrivee + '\'' +
                '}';
    }

    public Object generer(long idGareDepart, long idGareArrivee) {
        List<ItineraireDTO> itineraires = itineraireService.getItineraire();

        // Récupération des arrets de départ et d'arrivée
        var listeDeparts = new ArrayList<ArretDTO>();
        var listeArrivees = new ArrayList<ArretDTO>();
        for (ItineraireDTO itineraire : itineraires) {
            for (ArretDTO arret : itineraire.getArrets()) {
                if (arret.getGareConcerner().getId() == idGareDepart && arret.getDoitMarquerArret().equals(true) && arret.getHeureDepart() != null) {
                    listeDeparts.add(arret);
                }
                if (arret.getGareConcerner().getId() == idGareArrivee && arret.getDoitMarquerArret().equals(true) && arret.getHeureArrivee() != null) {
                    listeArrivees.add(arret);
                }
            }
        }

        if (listeDeparts.isEmpty() || listeArrivees.isEmpty()) {
            System.out.println("Aucun trajet n'a été trouvé");
        }



        return null;
    }
}
