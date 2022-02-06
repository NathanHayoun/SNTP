package fr.miage.m1.sntp;

import fr.miage.m1.sntp.dto.ArretDTO;
import fr.miage.m1.sntp.dto.ItineraireDTO;
import fr.miage.m1.sntp.services.ArretService;
import fr.miage.m1.sntp.services.ItineraireService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class Trajets {
    private String villeDepart;
    private String villeArrivee;
    @Inject
    @RestClient
    ItineraireService itineraireService;

//    public Trajets(String villeDepart, String villeArrivee) {
//        this.villeDepart = villeDepart;
//        this.villeArrivee = villeArrivee;
//    }

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

    public Object generer(long idGareDepart, long idGareArrivee, LocalTime heureDepart) throws CloneNotSupportedException {
        System.out.println("generer");
        List<ItineraireDTO> itineraires = itineraireService.getItineraire();
        Graph graph = new Graph();
        System.out.println("bb");

        // Récupération des arrets de départ et d'arrivée
        for (ItineraireDTO itineraire : itineraires) {
            boolean premierArret = true;
            Node lastNode = null;
            Node arretEnCours;

            for (ArretDTO arret : itineraire.getArrets()) {
                if (premierArret) {
                    lastNode = new Node(arret);
                    premierArret = false;
                    System.out.println(lastNode.toString());
                    System.out.println("aaaa");
                } else {
                    arretEnCours = new Node(arret);
                    lastNode.addDestination(arretEnCours, (int) lastNode.getArret().getHeureArrivee().until(arret.getHeureArrivee(), ChronoUnit.MINUTES));
                    lastNode = arretEnCours;
                }

            }

//            for (ArretDTO arret : itineraire.getArrets()) {
//                // Récupère l'arret de départ
//                if (arret.getGareConcerner().getId() == idGareDepart && arret.getDoitMarquerArret().equals(true) && arret.getHeureDepart() != null && (arret.getHeureDepart().isBefore(heureDepart) || arret.getHeureDepart().equals(heureDepart))) {
//                    arretDepartNode = new Node(arret);
//                } else {
//                    if (listeArrets.stream().anyMatch(station -> Objects.equals(station.getGareConcerner().getId(), arret.getGareConcerner().getId()) && arret.getHeureDepart().isAfter(station.getHeureDepart()))) {
//
//                    } else {
//                        listeArrets.add(arret);
//                    }
//                }
//            }

//            if (arretDepartNode != null) {
//                boolean premierArret = true;
//                Node lastNode = null;
//                // On récupère les arrets qui suivent l'arret de départ
//                for (ArretDTO arret : listeArrets) {
//                    if (arret.getDoitMarquerArret()) {
//                        if (premierArret) {
//                            lastNode = new Node(arret);
//                            // Ajout d'un noeud après l'arret de départ
//                            arretDepartNode.addDestination(lastNode, (int) arretDepartNode.getArret().getHeureDepart().until(arret.getHeureArrivee(), ChronoUnit.MINUTES));
//                            premierArret = false;
//                        } else {
//                            // Ajout d'un noeud après l'arret précédent
//                            lastNode.addDestination(new Node(arret), (int) lastNode.getArret().getHeureArrivee().until(arret.getHeureArrivee(), ChronoUnit.MINUTES));
//                            lastNode = new Node(arret);
//                        }
//
//                    }
//                }
//                // Ajout de la lite de noeuds au graph
//                graph.addNode(lastNode);
//                graph = Dijkstra.calculateShortestPathFromSource(graph, arretDepartNode);
//            }
        }

        // arretDepartNode va changer à chaque itération

//        if (listeDeparts.isEmpty() || listeArrivees.isEmpty()) {
//            System.out.println("Aucun trajet n'a été trouvé");
//        } else {
//            Graph graph = new Graph();
//
//            System.out.println("itineraires :");
//            System.out.println(itineraires);
//
//            System.out.println("listeDeparts :");
//            System.out.println(listeDeparts);
//
//            System.out.println("listeArrivees :");
//            System.out.println(listeArrivees);
//        }
        return null;
    }
}
