package fr.miage.m1.sntp.graph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
    public static final Logger logger = LoggerFactory.getLogger(Dijkstra.class);
    public static final String CURRENT_NODE_IS_NULL = "Le node courrant est null";
    public static final long DISTANCE_BASE = 0L;
    public static final String UTILITY_CLASS = "classe utilitaire";

    private Dijkstra() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(DISTANCE_BASE);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);

            if (currentNode != null) {
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
            } else {
                logger.error(CURRENT_NODE_IS_NULL);
            }
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

    private static void calculateMinimumDistance(Node evaluationNode, Long edgeWeigh, Node sourceNode) {
        long sourceDistance = sourceNode.getDistance();

        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
