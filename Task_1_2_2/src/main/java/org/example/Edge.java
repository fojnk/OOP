package org.example;

public class Edge<T> {

    private final Vertex<T> src;
    private final Vertex<T> dest;
    private Double weight;

    public Edge(Vertex<T> src, Vertex<T> dest, Double weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public Double getWeight() { return this.weight; }
    public Vertex<T> getSrc() { return this.src; }
    public Vertex<T> getDest() { return this.dest; }

    public void changeWeight(Double weight) { this.weight = weight; }
}