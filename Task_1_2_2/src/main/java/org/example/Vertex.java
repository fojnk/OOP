package org.example;

public class Vertex<T> {
    private final T value;

    public Vertex(T value) {
        this.value = value;
    }

    public T getValue() { return this.value; }
}
