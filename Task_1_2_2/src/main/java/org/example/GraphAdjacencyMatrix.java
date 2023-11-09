package org.example;

import java.util.*;

/**
 * класс представляющий граф в виде матрицы смежности.
 *
 * @param <T> - тип вершины
 */
public class GraphAdjacencyMatrix<T> extends Graph<T> {

    HashMap<Vertex<T>, HashMap<Vertex<T>, Double>> adjacencyMatrix;

    /**
     * конструктор для пустого класса.
     */
    public GraphAdjacencyMatrix() {
        super();
        this.adjacencyMatrix = new HashMap<>();
    }

    /**
     * конструктор загружаемого графа.
     *
     * @param vertArray - список вершин
     * @param edgArray  - список ребер
     */
    public GraphAdjacencyMatrix(ArrayList<Vertex<T>> vertArray, ArrayList<Edge<T>> edgArray) {
        super(vertArray, edgArray);
        adjacencyMatrix = new HashMap<>();
        for (var vert : vertArray) {
            adjacencyMatrix.put(vert, new HashMap<>());
        }

        for (var edge : edgArray) {
            adjacencyMatrix.get(edge.getSrc()).put(edge.getDest(), edge.getWeight());
        }
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        if (!adjacencyMatrix.containsKey(vertex)) {
            adjacencyMatrix.put(vertex, new HashMap<>());
            vertexes.put(this.maxVertId++, vertex);
        }
    }

    @Override
    public void deleteVertex(Vertex<T> vertex) {
        if (!adjacencyMatrix.containsKey(vertex)) {
            return;
        }
        Integer id = null;
        for (var entry : vertexes.entrySet()) {
            var vert = entry.getValue();
            if (entry.getValue() == vertex) {
                id = entry.getKey();
                for (var destVert : adjacencyMatrix.get(vert).keySet()) {
                    var e = this.getEdge(vertex, destVert);
                    if (this.containsEdge(e)) {
                        this.edges.remove((Integer) this.getIdByEdge(e));
                    }
                }
            }
            if (adjacencyMatrix.get(vert).containsKey(vertex)) {
                var e = this.getEdge(vert, vertex);
                if (this.containsEdge(e)) {
                    edges.remove((Integer) this.getIdByEdge(e));
                }
            }
            adjacencyMatrix.get(vert).remove(vertex);
        }
        adjacencyMatrix.remove(vertex);
        vertexes.remove(id);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        if (!adjacencyMatrix.containsKey(edge.getSrc())
                || !adjacencyMatrix.containsKey(edge.getDest())) {
            throw new IllegalArgumentException();
        }
        this.adjacencyMatrix.get(edge.getSrc()).put(edge.getDest(), edge.getWeight());
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
        for (var vertId : vertexes.keySet()) {
            marked.put(vertId, false);
            distance.put(vertId, Double.POSITIVE_INFINITY);
        }
        ArrayList<Integer> listOfVert = new ArrayList<>();
        distance.replace(start, (double) 0);
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
            for (var vert : adjacencyMatrix.get(this.getVertexById(v)).keySet()) {
                var vertId = (Integer) this.getIdByVertex(vert);
                if (distance.get(vertId) > distance.get(v) + adjacencyMatrix.get(getVertexById(v)).get(vert)) {
                    distance.replace(vertId, distance.get(v) + adjacencyMatrix.get(getVertexById(v)).get(vert));
                    listOfVert.add(vertId);
                }
            }
        }
        return distance;
    }

    @Override
    public Boolean changeEdgeValue(Edge<T> edge, Double value) {
        if (!edges.containsValue(edge)) return false;

        adjacencyMatrix.get(edge.getSrc()).replace(edge.getDest(), value);
        var edgId = this.getIdByEdge(edge);
        var newEdge = new Edge<T>(edge.getSrc(), edge.getDest(), value);
        edges.replace((Integer) edgId, newEdge);
        return true;
    }
}