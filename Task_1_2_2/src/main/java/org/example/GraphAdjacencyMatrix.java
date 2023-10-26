package org.example;

import org.example.Graph;

import java.util.HashMap;

public class GraphAdjacencyMatrix<T, N> extends Graph<T, N> {

    public GraphAdjacencyMatrix(Vertex<T>[] ver_array, Edge<T, N>[] edg_array) {
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