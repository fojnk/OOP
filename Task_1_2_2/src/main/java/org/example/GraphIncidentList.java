package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphIncidentList<T> extends Graph<T> {

    private final HashMap<Vertex<T>, ArrayList<Edge<T>>> incidentList;

    public GraphIncidentList() {
        super();
        this.incidentList = new HashMap<>();
    }

    public GraphIncidentList(ArrayList<Vertex<T>> ver_array, ArrayList<Edge<T>> edg_array) {
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
        if (!this.containsVertex(vertex)){
            this.incidentList.put(vertex, new ArrayList<>());
            this.vertexes.put(this.maxVertId++, vertex);
        }
    }

    @Override
    public void deleteVertex(Vertex<T> vertex) {
        if (!this.incidentList.containsKey(vertex)) { return; }
        Integer id = null;
        ArrayList<Edge<T>> buf = new ArrayList<>();
        for (var vert_id: this.vertexes.keySet()) {
            var vert = this.getVertexById(vert_id);
            if (vert == vertex) {
                id = vert_id;
            }
            for (var edge: incidentList.get(vert)) {
                if (edge.getDest() == vertex || edge.getSrc() == vertex) {
                    buf.add(edge);
                    if (this.containsEdge(edge)){
                        this.edges.remove((Integer) this.getIdByEdge(edge));
                    }
                }
            }
        }
        for (var edge: buf) {
            this.incidentList.get(edge.getSrc()).remove(edge);
        }
        this.vertexes.remove(id);
        this.incidentList.remove(vertex);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        if (!this.incidentList.containsKey(edge.getSrc()) ||
        !this.incidentList.containsKey(edge.getDest())) {
            throw new IllegalArgumentException();
        }
        this.incidentList.get(edge.getSrc()).add(edge);
        this.edges.put(this.maxEdgeId++, edge);
    }

    @Override
    public void deleteEdge(Edge<T> edge) {
        this.incidentList.get(edge.getSrc()).remove(edge);
        this.edges.remove((Integer) this.getIdByEdge(edge));
    }

    @Override
    public HashMap<Integer, Double> dijkstra(Integer start) {
        HashMap<Integer, Boolean> marked = new HashMap<>();
        HashMap<Integer, Double> distance = new HashMap<>();
        for (var vert_id: this.vertexes.keySet()) {
            marked.put(vert_id, false);
            distance.put(vert_id, Double.POSITIVE_INFINITY);
        }
        ArrayList<Integer> list_of_vert = new ArrayList<>();
        distance.replace(start, Double.POSITIVE_INFINITY, (double) 0);
        list_of_vert.add(start);
        while (!list_of_vert.isEmpty()) {
            Double min_dist = Double.POSITIVE_INFINITY;
            Integer v = null;
            for (var vert: list_of_vert){
                if (min_dist > distance.get(vert)) {
                    v = vert;
                    min_dist = distance.get(vert);
                }
            }
            list_of_vert.remove(v);
            if (marked.get(v)) { continue; }
            marked.replace(v, true);
            for (var edge: incidentList.get(this.getVertexById(v))) {
                var vert_id = (Integer)this.getIdByVertex(edge.getDest());
                if (distance.get(vert_id) > distance.get(v) + edge.getWeight()) {
                    distance.replace(vert_id, distance.get(v) + edge.getWeight());
                    list_of_vert.add(vert_id);
                }
            }
        }
        return distance;
    }
}