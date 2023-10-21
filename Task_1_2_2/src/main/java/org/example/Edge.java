package org.example;

import org.example.Vertex;

public class Edge<T, N> {

    private Vertex<T> src;
    private Vertex<T> dest;
    private N weight;

    public Edge(Vertex<T> src, Vertex<T> dest, N weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public N getWeight() { return this.weight; }
}