package fr.miage.m1.sntp;

import fr.miage.m1.sntp.dao.ReservationDao;
import fr.miage.m1.sntp.dao.TicketDao;
import fr.miage.m1.sntp.dto.ArretDTO;
import fr.miage.m1.sntp.dto.ItineraireDTO;
import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.models.Ticket;
import fr.miage.m1.sntp.models.Voyageur;
import fr.miage.m1.sntp.services.ItineraireService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.Map.Entry;

@ApplicationScoped
public class Trajets {
    private String villeDepart;
    private String villeArrivee;
    @Inject
    @RestClient
    ItineraireService itineraireService;

    @Inject
    TicketDao ticketDao;

    @Inject
    ReservationDao reservationDao;

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

    public Reservation generer(String nomGareDepart, String nomGareArrive, LocalTime heureDepart, LocalDate localDate, Voyageur voyageur) throws CloneNotSupportedException {
        nomGareArrive = nomGareArrive.toUpperCase();
        nomGareDepart = nomGareDepart.toUpperCase();
        List<ItineraireDTO> itineraires = itineraireService.getItineraire(); // Récupération des itineraires
        Map<String, Node> nodes = new HashMap<>();

        for (ItineraireDTO itineraire : itineraires) {
            for (ArretDTO arret : itineraire.getArrets()) {
                String nomGare = arret.getGareConcerner().getNomGare();
                if (!nodes.containsKey(nomGare) && arret.getHeureDepart() != null && arret.getHeureDepart().isAfter(heureDepart) || Objects.equals(arret.getHeureDepart(), heureDepart)) {
                    nodes.put(arret.getGareConcerner().getNomGare().toUpperCase(Locale.ROOT), new Node(arret));
                }
            }
        }

        for (Entry<String, Node> entry : nodes.entrySet()) {
            Node node = entry.getValue();

            for (ItineraireDTO itineraire : itineraires) {
                Long position = 0L;
                ArretDTO arretPrecedent = null;

                for (ArretDTO arret : itineraire.getArrets()) {
                    if (arret.getGareConcerner().getNomGare().toUpperCase(Locale.ROOT).equals(entry.getKey())) {
                        arretPrecedent = arret;
                        position = arret.getPosition();
                    } else {
                        if (position != null && arretPrecedent != null && arret.getDoitMarquerArret()) {
                            Long size = arretPrecedent.getHeureDepart().until(arret.getHeureArrivee(), java.time.temporal.ChronoUnit.MINUTES);
                            node.addDestination(nodes.get(arret.getGareConcerner().getNomGare().toUpperCase(Locale.ROOT)), size);
                        }
                    }
                }
            }
        }


        Graph graph = new Graph();

        for (Entry<String, Node> entry : nodes.entrySet()) {
            graph.addNode(entry.getValue());
        }

        Graph graph1;
        graph1 = Dijkstra.calculateShortestPathFromSource(graph, nodes.get(nomGareDepart));
        Map<String, List<ArretDTO>> arrets = new LinkedHashMap<>();
        System.out.println(graph1.getNodes());
        for (Node node : graph1.getNodes()) {
            if (node.getArret().getGareConcerner().getNomGare().toUpperCase(Locale.ROOT).equals(nomGareArrive)) {
                for (Node adjacent : node.getShortestPath()) {
                    String ligneDeTrain = adjacent.getArret().getTrain().getLigneDeTrain();
                    if (!arrets.containsKey(ligneDeTrain)) {
                        arrets.put(ligneDeTrain, new ArrayList<>(List.of(adjacent.getArret())));
                    } else {
                        arrets.get(ligneDeTrain).add(adjacent.getArret());
                    }
                }
            }
        }
        //Genere un nombre aleatoire avec une borne maximum et minimum
        Set<Ticket> ticketList = new LinkedHashSet<>();
        Reservation reservation = new Reservation();
        double prix = arrets.values().stream().mapToDouble(List::size).sum() * 10;
        reservation.setVoyageur(voyageur);
        reservation.setPrix(prix);
        reservation.setDateDeReservation(localDate);
        reservationDao.save(reservation);
        int numeroEtape = 0;
        for (Entry<String, List<ArretDTO>> entry : arrets.entrySet()) {
            System.out.println("==============================");
            System.out.println("Ligne de train : " + entry.getKey());
            System.out.println("==============================");
            System.out.println(entry.getValue());
            System.out.println("==============================");
            numeroEtape++;
            ArretDTO arretFirst = entry.getValue().get(0);
            ArretDTO arretLast = entry.getValue().get(entry.getValue().size() - 1);
            Random random = new Random();
            int nombreAleatoire = random.nextInt(arrets.size());
            Ticket ticket = new Ticket();
            ticket.setDateDepart(localDate)
                    .setGareDepart(arretFirst.getGareConcerner().getNomGare())
                    .setGareArrivee(arretLast.getGareConcerner().getNomGare())
                    .setHeureArrivee(arretLast.getHeureArrivee())
                    .setHeureDepart(arretFirst.getHeureDepart())
                    .setNumeroEtape(numeroEtape)
                    .setPlace(nombreAleatoire)
                    .setIsReservable(arretFirst.getTrain().getTypeDeTrain().equals("TGV"))
                    .setNumeroTrain((int) arretFirst.getTrain().getNumeroDeTrain())
                    .setReservationConcernee(reservation);
            System.out.println(ticket);
            ticketList.add(ticket);
//            ticketDao.save(ticket);
        }

        reservation.setTickets(ticketList);

        System.out.println(graph1);

        return reservation;
    }
}
