package org.example;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * класс представляющий граф в виде матрицы смежности.
 *
 * @param <T> - тип вершины
 */
public class GraphAdjacencyMatrix<T> extends Graph<T> {

    HashMap<Vertex<T>, HashMap<Vertex<T>, Boolean>> adjacencyMatrix;

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
        if (!this.adjacencyMatrix.containsKey(vertex)) {
            return;
        }
        Integer id = null;
        for (var vertId : this.vertexes.keySet()) {
            var vert = this.getVertexById(vertId);
            if (vert == vertex) {
                id = vertId;
                for (var destVert : this.adjacencyMatrix.get(vert).keySet()) {
                    var e = this.getEdge(vertex, destVert);
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
            for (var vert : adjacencyMatrix.get(this.getVertexById(v)).keySet()) {
                if (adjacencyMatrix.get(this.getVertexById(v)).get(vert)) {
                    var edge = this.getEdge(this.getVertexById(v), vert);
                    var vertId = (Integer) this.getIdByVertex(vert);
                    if (distance.get(vertId) > distance.get(v) + edge.getWeight()) {
                        distance.replace(vertId, distance.get(v) + edge.getWeight());
                        listOfVert.add(vertId);
                    }
                }
            }
        }
        return distance;
    }
}