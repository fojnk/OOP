package org.example;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * класс, который представляет граф в виде списка инциндентности.
 *
 * @param <T> - параметр вершин
 */
public class GraphIncidentList<T> extends Graph<T> {
    private final HashMap<Vertex<T>, ArrayList<Edge<T>>> incidentList;

    /**
     * конструктор пустого графа, для тестов.
     */
    public GraphIncidentList() {
        super();
        this.incidentList = new HashMap<>();
    }

    /**
     * конструктор загружаемого графа.
     *
     * @param vertArray - список вершин
     * @param edgArray  - список ребер
     */
    public GraphIncidentList(ArrayList<Vertex<T>> vertArray, ArrayList<Edge<T>> edgArray) {
        super(vertArray, edgArray);
        incidentList = new HashMap<>();
        for (var vert : vertArray) {
            incidentList.put(vert, new ArrayList<>());
        }

        for (var edge : edgArray) {
            incidentList.get(edge.getSrc()).add(edge);
        }
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        if (!this.containsVertex(vertex)) {
            this.incidentList.put(vertex, new ArrayList<>());
            this.vertexes.put(this.maxVertId++, vertex);
        }
    }

    @Override
    public void deleteVertex(Vertex<T> vertex) {
        if (!this.incidentList.containsKey(vertex)) {
            return;
        }
        Integer id = null;
        ArrayList<Edge<T>> buf = new ArrayList<>();
        for (var vertId : this.vertexes.keySet()) {
            var vert = this.getVertexById(vertId);
            if (vert == vertex) {
                id = vertId;
            }
            for (var edge : incidentList.get(vert)) {
                if (edge.getDest() == vertex || edge.getSrc() == vertex) {
                    buf.add(edge);
                    if (this.containsEdge(edge)) {
                        this.edges.remove((Integer) this.getIdByEdge(edge));
                    }
                }
            }
        }
        for (var edge : buf) {
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
        for (var vertId : this.vertexes.keySet()) {
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
            for (var edge : incidentList.get(this.getVertexById(v))) {
                var vertId = (Integer) this.getIdByVertex(edge.getDest());
                if (distance.get(vertId) > distance.get(v) + edge.getWeight()) {
                    distance.replace(vertId, distance.get(v) + edge.getWeight());
                    listOfVert.add(vertId);
                }
            }
        }
        return distance;
    }
}