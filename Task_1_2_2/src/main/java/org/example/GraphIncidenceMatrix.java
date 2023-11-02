package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphIncidenceMatrix<T> extends Graph<T> {
    HashMap<Vertex<T>, HashMap<Edge<T>, Integer>> incidenceMatrix;

    /**
     * конструктор пустого графа, для тестов.
     */
    public GraphIncidenceMatrix() {
        super();
        this.incidenceMatrix = new HashMap<>();
    }

    /**
     * конструктор загружаемого из файла графа.
     *
     * @param ver_array - список вершин
     * @param edg_array - список ребер
     */
    public GraphIncidenceMatrix(ArrayList<Vertex<T>> ver_array, ArrayList<Edge<T>> edg_array) {
        super(ver_array, edg_array);
        incidenceMatrix = new HashMap<>();
        for (var vert : ver_array) {
            incidenceMatrix.put(vert, new HashMap<>());
        }

        for (var edge : edg_array) {
            incidenceMatrix.get(edge.getSrc()).put(edge, 1);
            incidenceMatrix.get(edge.getDest()).put(edge, -1);
        }
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        if (!this.incidenceMatrix.containsKey(vertex)) {
            incidenceMatrix.put(vertex, new HashMap<>());
            this.vertexes.put(this.maxVertId++, vertex);
        }
    }

    @Override
    public void deleteVertex(Vertex<T> vertex) {
        if (!this.incidenceMatrix.containsKey(vertex)) {
            return;
        }
        Integer id = null;
        ArrayList<Edge<T>> buf = new ArrayList<>();
        for (var vert_id : this.vertexes.keySet()) {
            var vert = this.getVertexById(vert_id);
            if (vert == vertex) {
                id = vert_id;
            }
        }
        for (var edge : this.getListOfEdges()) {
            if (edge.getSrc() == vertex || edge.getDest() == vertex) {
                buf.add(edge);
                incidenceMatrix.get(edge.getDest()).remove(edge);
                incidenceMatrix.get(edge.getSrc()).remove(edge);
            }
        }
        for (var edge : buf) {
            if (this.containsEdge(edge)) {
                this.edges.remove((Integer) this.getIdByEdge(edge));
            }
        }
        incidenceMatrix.remove(vertex);
        this.vertexes.remove(id);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        if (!this.incidenceMatrix.containsKey(edge.getSrc()) ||
                !this.incidenceMatrix.containsKey(edge.getDest())) {
            throw new IllegalArgumentException();
        }
        incidenceMatrix.get(edge.getSrc()).put(edge, 1);
        incidenceMatrix.get(edge.getDest()).put(edge, -1);
        this.edges.put(this.maxEdgeId++, edge);
    }

    @Override
    public void deleteEdge(Edge<T> edge) {
        incidenceMatrix.get(edge.getSrc()).remove(edge);
        incidenceMatrix.get(edge.getDest()).remove((edge));
        this.edges.remove((Integer) this.getIdByEdge(edge));
    }

    @Override
    public HashMap<Integer, Double> dijkstra(Integer start) {
        HashMap<Integer, Boolean> marked = new HashMap<>();
        HashMap<Integer, Double> distance = new HashMap<>();
        for (var vert_id : this.vertexes.keySet()) {
            marked.put(vert_id, false);
            distance.put(vert_id, Double.POSITIVE_INFINITY);
        }
        ArrayList<Integer> list_of_vert = new ArrayList<>();
        distance.replace(start, Double.POSITIVE_INFINITY, (double) 0);
        list_of_vert.add(start);
        while (!list_of_vert.isEmpty()) {
            Double min_dist = Double.POSITIVE_INFINITY;
            Integer v = null;
            for (var vert : list_of_vert) {
                if (min_dist > distance.get(vert)) {
                    v = vert;
                    min_dist = distance.get(vert);
                }
            }
            list_of_vert.remove(v);
            if (marked.get(v)) {
                continue;
            }
            marked.replace(v, true);
            for (var edge : incidenceMatrix.get(this.getVertexById(v)).keySet()) {
                if (incidenceMatrix.get(this.getVertexById(v)).get(edge) == 1) {
                    var vert = (Integer) this.getIdByVertex(edge.getDest());
                    if (distance.get(vert) > distance.get(v) + edge.getWeight()) {
                        distance.replace(vert, distance.get(v) + edge.getWeight());
                        list_of_vert.add(vert);
                    }
                }
            }
        }
        return distance;
    }
}