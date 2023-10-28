package org.example;

public class Vertex<T> {
    private T value;

    public Vertex() {
        this.value = null;
    }

    public Vertex(T value) {
        this.value = value;
    }

    public T getValue() { return this.value; }
    public void changeValue(T value) { this.value = value; }
}
