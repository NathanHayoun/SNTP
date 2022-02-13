package fr.miage.m1.sntp.graph;

import java.util.LinkedHashSet;
import java.util.Set;

public class Graph {
    private Set<Node> nodes = new LinkedHashSet<>();

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
                "nodes = " + nodes;
    }
}