package fr.miage.m1.sntp.graph;

import fr.miage.m1.sntp.dto.ArretDTO;

import java.util.*;

public class Node {
    Map<Node, Long> adjacentNodes = new HashMap<>();
    private ArretDTO arret;
    private List<Node> shortestPath = new LinkedList<>();
    private Long distance = Long.MAX_VALUE;

    public Node(ArretDTO arret) {
        this.arret = arret;
    }

    public void addDestination(Node destination, Long distance) {
        adjacentNodes.put(destination, distance);
    }

    public ArretDTO getArret() {
        return arret;
    }

    public void setArret(ArretDTO arret) {
        this.arret = arret;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Map<Node, Long> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Long> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    @Override
    public String toString() {
        List<String> shortedPath = new ArrayList<>();
        for (Node node : shortestPath) {
            shortedPath.add(node.getArret().getGareConcerner().getNomGare());
        }
        return "Node: " +
                "arret=" + arret.getGareConcerner().getNomGare() +
                ", distance=" + distance + "minutes / chemin : " + shortedPath;
    }
}
