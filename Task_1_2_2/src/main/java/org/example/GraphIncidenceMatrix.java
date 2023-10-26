package org.example;

import java.util.HashMap;

public class GraphIncidenceMatrix<T, N> extends Graph<T, N> {

    public GraphIncidenceMatrix(Vertex<T>[] ver_array, Edge<T, N>[] edg_array) {
        super(ver_array, edg_array);
    }

    @Override
    public void addVertex(Vertex<T> vertex) {

    }

    @Override
    public void deleteVertex(Vertex<T> vertex) {

    }

    @Override
    public void addEdge(Edge<T, N> edge) {

    }

    @Override
    public void deleteEdge(Edge<T, N> edge) {

    }

    @Override
    public HashMap<Vertex<T>, N> dijkstra(Vertex<T> start) {
        return null;
    }
}