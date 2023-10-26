package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphIncidentList<T, N> extends Graph<T, N> {

    private final HashMap<Vertex<T>, ArrayList<Edge<T, N>>> incidentList;

    public GraphIncidentList(Vertex<T>[] ver_array, Edge<T, N>[] edg_array) {
        super(ver_array, edg_array);
        incidentList = new HashMap<>();
        for (var vert: ver_array) {
            incidentList.put(vert, new ArrayList<>());
        }

        for (var edge: edg_array) {
            incidentList.get(edge.getSrc()).add(edge);
        }
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
        this.incidentList.get(edge.getSrc()).add(edge);
    }

    @Override
    public void deleteEdge(Edge<T, N> edge) {
        this.incidentList.get(edge.getSrc()).remove(edge);
    }

    @Override
    public HashMap<Vertex<T>, N> dijkstra(Vertex<T> start) {
        HashMap<Vertex<T>, Boolean> marked = new HashMap<>();
        HashMap<Vertex<T>, Double> distance = new HashMap<>();
        for (var vert: getListOfVertexes()) {
            marked.put(vert, false);
            distance.put(vert, Double.POSITIVE_INFINITY);
        }
    }
}