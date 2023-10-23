package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphIncidentList<T, N> extends Graph<T, N> {

    private HashMap<Vertex<T>, ArrayList<Vertex<T>>> incidentList;

    public GraphIncidentList() {
        this.incidentList = new HashMap<>();
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        this.incidentList.put(vertex, new ArrayList<>());
    }

    @Override
    public void deleteVertex(Vertex<T> vertex) {
        this.incidentList.remove(vertex);
    }

    @Override
    public void addEdge(Edge<T, N> edge) {
        this.incidentList.get(edge.getSrc()).add(edge.getDest());
    }

    @Override
    public void deleteEdge(Edge<T, N> edge) {
        this.incidentList.get(edge.getSrc()).remove(edge.getDest());
    }
}