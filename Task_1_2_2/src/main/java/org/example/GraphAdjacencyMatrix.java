package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphAdjacencyMatrix<T> extends Graph<T> {

    HashMap<Vertex<T>, HashMap<Vertex<T>, Boolean>> adjacencyMatrix;

    public GraphAdjacencyMatrix() {
        super();
        this.adjacencyMatrix = new HashMap<>();
    }
    public GraphAdjacencyMatrix(ArrayList<Vertex<T>> ver_array, ArrayList<Edge<T>> edg_array) {
        super(ver_array, edg_array);
        adjacencyMatrix = new HashMap<>();
        for (var vert: ver_array) {
            adjacencyMatrix.put(vert, new HashMap<>());
        }

        for (var edge: edg_array) {
            adjacencyMatrix.get(edge.getSrc()).put(edge.getDest(), true);
        }
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        if (!this.adjacencyMatrix.containsKey(vertex)) {
        adjacencyMatrix.put(vertex, new HashMap<>());
        this.vertexes.put(this.maxEdgeId++, vertex);
        }
    }

    @Override
    public void deleteVertex(Vertex<T> vertex) {
        if (!this.adjacencyMatrix.containsKey(vertex)) { return; }
        Integer id = null;
        for (var vert_id: this.vertexes.keySet()) {
            var vert = this.getVertexById(vert_id);
            if (vert == vertex) {
                id = vert_id;
                for (var dest_vert: this.adjacencyMatrix.get(vert).keySet()) {
                    var e = this.getEdge(vertex, dest_vert);
                    if (this.containsEdge(e)) {
                        this.edges.remove((Integer) this.getIdByEdge(e));
                    }
                }
            }
            if (this.adjacencyMatrix.get(vert).containsKey(vertex)) {
                var e = this.getEdge(vert, vertex);
                if (this.containsEdge(e)) {
                    this.edges.remove((Integer) this.getIdByEdge(e));
                }
            }
            this.adjacencyMatrix.get(vert).remove(vertex);
        }
        this.adjacencyMatrix.remove(vertex);
        this.vertexes.remove(id);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        if (!this.adjacencyMatrix.containsKey(edge.getSrc()) ||
                !this.adjacencyMatrix.containsKey(edge.getDest())) {
            throw new IllegalArgumentException();
        }
        this.adjacencyMatrix.get(edge.getSrc()).put(edge.getDest(), true);
        this.edges.put(this.maxEdgeId++, edge);
    }

    @Override
    public void deleteEdge(Edge<T> edge) {
        this.adjacencyMatrix.get(edge.getSrc()).remove(edge.getDest());
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
            for (var vert: adjacencyMatrix.get(this.getVertexById(v)).keySet()) {
                if (adjacencyMatrix.get(this.getVertexById(v)).get(vert)) {
                    var edge = this.getEdge(this.getVertexById(v), vert);
                    var vert_id = (Integer)this.getIdByVertex(vert);
                    if (distance.get(vert_id) > distance.get(v) + edge.getWeight()) {
                        distance.replace(vert_id, distance.get(v) + edge.getWeight());
                        list_of_vert.add(vert_id);
                    }
                }
            }
        }
        return distance;
    }
}