package fr.miage.m1.sntp.metier;

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
    public static final String KEY_SPLIT_REGEX = "/";
    public static final String TGV = "TGV";
    private static final Random random = new Random();
    @Inject
    @RestClient
    ItineraireService itineraireService;
    @Inject
    TicketDao ticketDao;
    @Inject
    ReservationDao reservationDao;

    public Reservation generer(String nomGareDepart, String nomGareArrive, LocalTime heureDepart, LocalDate localDate,
                               Voyageur voyageur, Long idReservation) {
        List<ItineraireDTO> itineraires = itineraireService.getItineraire();
        Map<String, Node> nodes = getAllNodes(heureDepart, itineraires);

        return generateReservationAndTicket(nomGareDepart, nomGareArrive, localDate, voyageur, itineraires, nodes,
                idReservation);
    }

    private Map<String, Node> getAllNodes(LocalTime heureDepart, List<ItineraireDTO> itineraires) {
        Map<String, Node> nodes = new LinkedHashMap<>();

        for (ItineraireDTO itineraire : itineraires) {
            for (ArretDTO arret : itineraire.getArrets()) {
                String nomGare = arret.getGareConcerner().getNomGare();

                if (!nodes.containsKey(nomGare + arret.getTrain().getLigneDeTrain()) &&
                        arret.getHeureDepart() == null ||
                        arret.getHeureDepart().isAfter(heureDepart) || Objects.equals(arret.getHeureDepart(),
                        heureDepart)) {
                    nodes.put(arret.getGareConcerner().getNomGare() + "/" + arret.getTrain().getLigneDeTrain(),
                            new Node(arret));
                }
            }
        }
        getAllDestinationForNodes(itineraires, nodes);

        return nodes;
    }

    private void getAllDestinationForNodes(List<ItineraireDTO> itineraires, Map<String, Node> nodes) {
        for (Entry<String, Node> entry : nodes.entrySet()) {
            Node node = entry.getValue();
            String key = entry.getKey();
            String ligneDeTrainDuNode = key.split(KEY_SPLIT_REGEX)[1];
            ArretDTO arretDuNode = node.getArret();
            Long posistionDuNodeDansSaLigne = arretDuNode.getPosition();

            for (ItineraireDTO itineraireDTO : itineraires) {
                ArretDTO arretPrecedent = null;

                for (ArretDTO arretDTO : itineraireDTO.getArrets()) {
                    if (arretDTO.getTrain().getLigneDeTrain().equals(ligneDeTrainDuNode)) {
                        if (arretDTO.getDoitMarquerArret() && arretDTO.getPosition() - 1 == posistionDuNodeDansSaLigne) {
                            String gareConcerner = arretDTO.getGareConcerner().getNomGare();
                            String keyToSearch = gareConcerner + "/" + ligneDeTrainDuNode;
                            Node nodePourAjouter = nodes.get(keyToSearch);
                            assert arretPrecedent != null;
                            Long size = arretPrecedent.getHeureDepart().until(arretDTO.getHeureArrivee(),
                                    java.time.temporal.ChronoUnit.MINUTES);
                            node.addDestination(nodePourAjouter, size);
                        }
                        arretPrecedent = arretDTO;
                    } else if (arretDTO.getGareConcerner().getNomGare().equals(arretDuNode.getGareConcerner().getNomGare()) &&
                            arretDTO.getHeureDepart() != null &&
                            arretDuNode.getHeureArrivee() != null &&
                            arretDTO.getHeureDepart().isAfter(arretDuNode.getHeureArrivee())) {
                        String gareConcerner = arretDTO.getGareConcerner().getNomGare();
                        String keyToSearch = gareConcerner + "/" + arretDTO.getTrain().getLigneDeTrain();
                        Node nodePourAjouter = nodes.get(keyToSearch);
                        assert arretPrecedent != null;
                        node.addDestination(nodePourAjouter, 5L);
                    }
                }
            }
        }
    }

    private Reservation generateReservationAndTicket(String nomGareDepart, String nomGareArrive, LocalDate
            localDate, Voyageur voyageur, List<ItineraireDTO> itineraires, Map<String, Node> nodes, Long idReservation) {
        Graph graph1;
        graph1 = getShortedPath(nomGareDepart, nomGareArrive, nodes);

        Map<String, Tuple<ArretDTO, ArretDTO>> arrets = generateTupleForTicket(nomGareArrive, graph1);
        double prix = generateGoodTimeForArrivalsAndGetPrice(itineraires, arrets);
        Set<Ticket> ticketList = new LinkedHashSet<>();
        Reservation reservation;

        if (idReservation == 0) {
            reservation = new Reservation();
            reservation.setVoyageur(voyageur);
            reservation.setPrix(prix);
            reservation.setDateDeReservation(localDate);
            reservation = reservationDao.save(reservation);
            reservation = reservationDao.findById(reservation.getId());
            reservation.setTickets(generateTicketList(localDate, arrets, ticketList, reservation));
        } else {
            reservation = reservationDao.findById(idReservation);
            double newPrix = prix - reservation.getPrix();
            reservation.setPrix(newPrix);
            reservation.setDateDeReservation(localDate);

            for (Ticket ticketsExistant : reservation.getTickets()) {
                ticketDao.delete(ticketsExistant);
            }
            reservation.setPrix(prix);
            reservation.setTickets(generateTicketList(localDate, arrets, ticketList, reservation));
            reservationDao.update(reservation);
        }

        return reservation;
    }

    private Graph getShortedPath(String nomGareDepart, String nomGareArrivee, Map<String, Node> nodes) {
        for (Entry<String, Node> nodeEntry : nodes.entrySet()) {
            String key = nodeEntry.getKey();
            String ligneDeTrain = key.split(KEY_SPLIT_REGEX)[1];
            if (ligneDeTrain.contains(nomGareArrivee) && ligneDeTrain.contains(nomGareDepart)) {

                return Dijkstra.calculateShortestPathFromSource(generateGraph(nodes), nodeEntry.getValue());
            }
        }
        Long size = null;
        Graph graphARetourner = null;

        for (Entry<String, Node> nodeEntry : nodes.entrySet()) {
            String gareDepartDuNode = nodeEntry.getKey().split(KEY_SPLIT_REGEX)[0];
            if (gareDepartDuNode.equals(nomGareDepart)) {
                Graph graph = Dijkstra.calculateShortestPathFromSource(generateGraph(nodes), nodeEntry.getValue());
                for (Node node : graph.getNodes()) {
                    if (node.getArret().getGareConcerner().getNomGare().equals(nomGareArrivee)) {
                        if (graphARetourner == null) {
                            size = calculateSizeOfPath(node);
                            graphARetourner = graph;
                        }
                        Long sizeCourrant = calculateSizeOfPath(node);

                        if (sizeCourrant < size) {
                            graphARetourner = graph;
                            size = sizeCourrant;
                        }
                    }
                }
            }
        }

        return graphARetourner;
    }

    private Long calculateSizeOfPath(Node node) {
        Long sizeCourrant = 0L;
        for (Node nodeDansGraphCourant : node.getShortestPath()) {
            sizeCourrant += nodeDansGraphCourant.getDistance();
        }

        return sizeCourrant;
    }

    private Map<String, Tuple<ArretDTO, ArretDTO>> generateTupleForTicket(String nomGareArrive,
                                                                          Graph graphShortedPath) {
        Map<String, Tuple<ArretDTO, ArretDTO>> arrets = new LinkedHashMap<>();
        Node nodeFinal = null;

        for (Node node : graphShortedPath.getNodes()) {
            if (node.getArret().getGareConcerner().getNomGare().equals(nomGareArrive)) {
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

        return arrets;
    }

    private Set<Ticket> generateTicketList(LocalDate localDate, Map<String, Tuple<ArretDTO, ArretDTO>> arrets,
                                           Set<Ticket> ticketList, Reservation reservation) {
        int numeroEtape = 0;

        for (Entry<String, Tuple<ArretDTO, ArretDTO>> entry : arrets.entrySet()) {
            numeroEtape++;
            ArretDTO arretFirst = entry.getValue().getVal1();
            ArretDTO arretLast = entry.getValue().getVal2();
            int nombreAleatoire = random.nextInt(arrets.size());
            Ticket ticket = new Ticket();
            ticket.setDateDepart(localDate)
                    .setGareDepart(arretFirst.getGareConcerner().getNomGare())
                    .setGareArrivee(arretLast.getGareConcerner().getNomGare())
                    .setHeureArrivee(arretLast.getHeureArrivee())
                    .setHeureDepart(arretFirst.getHeureDepart())
                    .setNumeroEtape(numeroEtape)
                    .setPlace(nombreAleatoire)
                    .setIsReservable(arretFirst.getTrain().getTypeDeTrain().equals(TGV))
                    .setNumeroTrain((int) arretFirst.getTrain().getNumeroDeTrain())
                    .setReservationConcernee(reservation);
            ticketList.add(ticket);
        }
        return ticketList;
    }

    private double generateGoodTimeForArrivalsAndGetPrice
            (List<ItineraireDTO> itineraires, Map<String, Tuple<ArretDTO, ArretDTO>> arrets) {
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
                    if (arretDTOFinal.getGareConcerner().getNomGare()
                            .equals(arretToCheck.getGareConcerner().getNomGare())) {
                        entry.getValue().setVal2(arretToCheck);
                    }
                }
            }
        }

        return prix;
    }

    private Graph generateGraph(Map<String, Node> nodes) {
        Graph graph = new Graph();

        for (Entry<String, Node> entry : nodes.entrySet()) {
            graph.addNode(entry.getValue());
        }

        return graph;
    }
}
