package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphIncidenceMatrix<T> extends Graph<T> {
    HashMap<Vertex<T>, HashMap<Edge<T>, Double>> incidenceMatrix;

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
     * @param vertArray - список вершин
     * @param edgArray  - список ребер
     */
    public GraphIncidenceMatrix(ArrayList<Vertex<T>> vertArray, ArrayList<Edge<T>> edgArray) {
        super(vertArray, edgArray);
        incidenceMatrix = new HashMap<>();
        for (var vert : vertArray) {
            incidenceMatrix.put(vert, new HashMap<>());
        }

        for (var edge : edgArray) {
            incidenceMatrix.get(edge.getSrc()).put(edge, edge.getWeight());
            incidenceMatrix.get(edge.getDest()).put(edge, -edge.getWeight());
        }
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        if (!incidenceMatrix.containsKey(vertex)) {
            incidenceMatrix.put(vertex, new HashMap<>());
            vertexes.put(this.maxVertId++, vertex);
        }
    }

    @Override
    public void deleteVertex(Vertex<T> vertex) {
        if (!incidenceMatrix.containsKey(vertex)) {
            return;
        }
        Integer id = null;
        ArrayList<Edge<T>> buf = new ArrayList<>();
        for (var vertId : vertexes.keySet()) {
            var vert = this.getVertexById(vertId);
            if (vert == vertex) {
                id = vertId;
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
                edges.remove((Integer) this.getIdByEdge(edge));
            }
        }
        incidenceMatrix.remove(vertex);
        vertexes.remove(id);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        if (!incidenceMatrix.containsKey(edge.getSrc())
                || !incidenceMatrix.containsKey(edge.getDest())) {
            throw new IllegalArgumentException();
        }
        incidenceMatrix.get(edge.getSrc()).put(edge, edge.getWeight());
        incidenceMatrix.get(edge.getDest()).put(edge, -edge.getWeight());
        edges.put(this.maxEdgeId++, edge);
    }

    @Override
    public void deleteEdge(Edge<T> edge) {
        incidenceMatrix.get(edge.getSrc()).remove(edge);
        incidenceMatrix.get(edge.getDest()).remove((edge));
        edges.remove((Integer) this.getIdByEdge(edge));
    }

    @Override
    public HashMap<Integer, Double> dijkstra(Integer start) {
        HashMap<Integer, Boolean> marked = new HashMap<>();
        HashMap<Integer, Double> distance = new HashMap<>();
        for (var vertId : vertexes.keySet()) {
            marked.put(vertId, false);
            distance.put(vertId, Double.POSITIVE_INFINITY);
        }
        ArrayList<Integer> listOfVert = new ArrayList<>();
        distance.replace(start, Double.POSITIVE_INFINITY, (double) 0);
        listOfVert.add(start);
        while (!listOfVert.isEmpty()) {
            Double minDist = Double.POSITIVE_INFINITY;
            Integer v = null;
            for (var vert : listOfVert) {
                if (minDist > distance.get(vert)) {
                    v = vert;
                    minDist = distance.get(vert);
                }
            }
            listOfVert.remove(v);
            if (marked.get(v)) {
                continue;
            }
            marked.replace(v, true);
            for (var edge : incidenceMatrix.get(this.getVertexById(v)).keySet()) {
                if (incidenceMatrix.get(this.getVertexById(v)).get(edge) > 0) {
                    var vert = (Integer) this.getIdByVertex(edge.getDest());
                    if (distance.get(vert) > distance.get(v) + edge.getWeight()) {
                        distance.replace(vert, distance.get(v) + edge.getWeight());
                        listOfVert.add(vert);
                    }
                }
            }
        }
        return distance;
    }

    @Override
    public Boolean changeEdgeValue(Edge<T> edge, Double value) {
        if (!edges.containsValue(edge)) {
            return false;
        }
        incidenceMatrix.get(edge.getSrc()).replace(edge, value);
        incidenceMatrix.get(edge.getSrc()).replace(edge, -value);
        var edgId = this.getIdByEdge(edge);
        var newEdge = new Edge<T>(edge.getSrc(), edge.getDest(), value);
        edges.replace((Integer) edgId, newEdge);
        return true;
    }
}