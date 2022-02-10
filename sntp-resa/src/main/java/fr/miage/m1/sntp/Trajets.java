package fr.miage.m1.sntp;

import fr.miage.m1.sntp.dao.ReservationDao;
import fr.miage.m1.sntp.dao.TicketDao;
import fr.miage.m1.sntp.dto.ArretDTO;
import fr.miage.m1.sntp.dto.ItineraireDTO;
import fr.miage.m1.sntp.graph.Dijkstra;
import fr.miage.m1.sntp.graph.Graph;
import fr.miage.m1.sntp.graph.Node;
import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.models.Ticket;
import fr.miage.m1.sntp.models.Voyageur;
import fr.miage.m1.sntp.services.ItineraireService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jgroups.util.Tuple;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.Map.Entry;

@ApplicationScoped
public class Trajets {
    @Inject
    @RestClient
    ItineraireService itineraireService;
    @Inject
    TicketDao ticketDao;
    @Inject
    ReservationDao reservationDao;

    public Reservation generer(String nomGareDepart, String nomGareArrive, LocalTime heureDepart, LocalDate localDate, Voyageur voyageur) {
        nomGareArrive = nomGareArrive.toUpperCase();
        nomGareDepart = nomGareDepart.toUpperCase();
        List<ItineraireDTO> itineraires = itineraireService.getItineraire(); // Récupération des itineraires
        Map<String, Node> nodes = new HashMap<>();
        getAllNodes(heureDepart, itineraires, nodes);
        getAllDestinationForNodes(itineraires, nodes);
        Graph graph = getGraph(nodes);

        return generateReservationAndTicket(nomGareDepart, nomGareArrive, localDate, voyageur, itineraires, nodes, graph);
    }

    private Reservation generateReservationAndTicket(String nomGareDepart, String nomGareArrive, LocalDate localDate, Voyageur voyageur, List<ItineraireDTO> itineraires, Map<String, Node> nodes, Graph graph) {
        Graph graph1;
        graph1 = Dijkstra.calculateShortestPathFromSource(graph, nodes.get(nomGareDepart));
        Map<String, Tuple<ArretDTO, ArretDTO>> arrets = new LinkedHashMap<>();
        Node nodeFinal = null;
        generateTupleForTicket(nomGareArrive, graph1, arrets, nodeFinal);
        double prix = generateGoodTimeForArrivalsAndGetPrice(itineraires, arrets);
        Set<Ticket> ticketList = new LinkedHashSet<>();
        Reservation reservation = new Reservation();
        reservation.setVoyageur(voyageur);
        reservation.setPrix(prix);
        reservation.setDateDeReservation(localDate);
        reservation = reservationDao.save(reservation);
        reservation = reservationDao.findById(reservation.getId());
        persistTicket(localDate, arrets, ticketList, reservation);
        reservation.setTickets(ticketList);

        System.out.println(reservation);

        return reservation;
    }

    private void persistTicket(LocalDate localDate, Map<String, Tuple<ArretDTO, ArretDTO>> arrets, Set<Ticket> ticketList, Reservation reservation) {
        int numeroEtape = 0;

        for (Entry<String, Tuple<ArretDTO, ArretDTO>> entry : arrets.entrySet()) {
            numeroEtape++;
            ArretDTO arretFirst = entry.getValue().getVal1();
            ArretDTO arretLast = entry.getValue().getVal2();
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
            ticketList.add(ticket);
            ticketDao.save(ticket);
        }
    }

    private double generateGoodTimeForArrivalsAndGetPrice(List<ItineraireDTO> itineraires, Map<String, Tuple<ArretDTO, ArretDTO>> arrets) {
        double prix = 0;
        for (Entry<String, Tuple<ArretDTO, ArretDTO>> entry : arrets.entrySet()) {
            ArretDTO arretDTO = entry.getValue().getVal1();
            ItineraireDTO itineraireSave = null;
            for (ItineraireDTO itineraireDTO : itineraires) {
                for (ArretDTO arretDTO1 : itineraireDTO.getArrets()) {
                    if (arretDTO1 == arretDTO) {
                        itineraireSave = itineraireDTO;
                        prix += itineraireDTO.getArrets().size() * 10;
                    }
                }
            }
            if (itineraireSave != null) {
                ArretDTO arretDTOFinal = entry.getValue().getVal2();
                for (ArretDTO arretToCheck : itineraireSave.getArrets()) {
                    if (arretDTOFinal.getGareConcerner().getNomGare().equals(arretToCheck.getGareConcerner().getNomGare())) {
                        entry.getValue().setVal2(arretToCheck);
                    }
                }
            }
        }

        return prix;
    }

    private void generateTupleForTicket(String nomGareArrive, Graph graph1, Map<String, Tuple<ArretDTO, ArretDTO>> arrets, Node nodeFinal) {
        for (Node node : graph1.getNodes()) {
            if (node.getArret().getGareConcerner().getNomGare().toUpperCase(Locale.ROOT).equals(nomGareArrive)) {
                nodeFinal = node;
                String ligneDeTrainPrecedent = null;

                for (Node adjacent : node.getShortestPath()) {
                    String ligneDeTrain = adjacent.getArret().getTrain().getLigneDeTrain();

                    if (!Objects.equals(ligneDeTrain, ligneDeTrainPrecedent)) {
                        if (arrets.get(ligneDeTrainPrecedent) != null) {
                            arrets.get(ligneDeTrainPrecedent).setVal2(adjacent.getArret());
                        }
                        ligneDeTrainPrecedent = ligneDeTrain;
                    }
                    if (!arrets.containsKey(ligneDeTrain)) {
                        Tuple<ArretDTO, ArretDTO> tuple = new Tuple<>(adjacent.getArret(), null);
                        arrets.put(ligneDeTrain, tuple);
                    }
                }
            }
        }
        for (Entry<String, Tuple<ArretDTO, ArretDTO>> entry : arrets.entrySet()) {
            if (entry.getValue().getVal2() == null) {
                entry.getValue().setVal2(nodeFinal.getArret());
            }
        }
    }

    private Graph getGraph(Map<String, Node> nodes) {
        Graph graph = new Graph();

        for (Entry<String, Node> entry : nodes.entrySet()) {
            graph.addNode(entry.getValue());
        }

        return graph;
    }

    private void getAllDestinationForNodes(List<ItineraireDTO> itineraires, Map<String, Node> nodes) {
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
    }

    private void getAllNodes(LocalTime heureDepart, List<ItineraireDTO> itineraires, Map<String, Node> nodes) {
        for (ItineraireDTO itineraire : itineraires) {
            for (ArretDTO arret : itineraire.getArrets()) {
                String nomGare = arret.getGareConcerner().getNomGare();

                if (!nodes.containsKey(nomGare) && arret.getHeureDepart() != null && arret.getHeureDepart().isAfter(heureDepart) || Objects.equals(arret.getHeureDepart(), heureDepart)) {
                    nodes.put(arret.getGareConcerner().getNomGare().toUpperCase(Locale.ROOT), new Node(arret));
                }
            }
        }
    }
}
