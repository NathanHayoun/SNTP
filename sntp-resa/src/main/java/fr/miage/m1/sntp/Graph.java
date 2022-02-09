package fr.miage.m1.sntp;

import fr.miage.m1.sntp.dto.ArretDTO;

import java.util.*;

public class Graph {
    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "Graph: " +
                "nodes=" + nodes;
    }
}

class Node {
    private ArretDTO arret;

    private List<Node> shortestPath = new LinkedList<>();

    private Long distance = Long.MAX_VALUE;

    Map<Node, Long> adjacentNodes = new HashMap<>();

    public void addDestination(Node destination, Long distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(ArretDTO arret) {
        this.arret = arret;
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
        for(Node node : shortestPath) {
            shortedPath.add(node.getArret().getGareConcerner().getNomGare());
        }
        return "Node: " +
                "arret=" + arret.getGareConcerner().getNomGare() +
                ", distance=" + distance + "minutes / chemin : " + shortedPath;
    }
}

class Dijkstra {
    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0L);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Node, Long> adjacencyPair :
                    currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                long edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        long lowestDistance = Long.MAX_VALUE;
        for (Node node : unsettledNodes) {
            long nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(Node evaluationNode,
                                                 Long edgeWeigh, Node sourceNode) {
        long sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}