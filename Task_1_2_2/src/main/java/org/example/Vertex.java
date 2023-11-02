package org.example;

/**
 * класс для описания вершины графа.
 *
 * @param <T> - тип вершины
 */
public class Vertex<T> {
    private T value;

    /**
     * конструктор вершины.
     *
     * @param value - входное значение
     */
    public Vertex(T value) {
        this.value = value;
    }

    /**
     * метод для получения значения вершниы.
     *
     * @return - значение вершины
     */
    public T getValue() {
        return this.value;
    }

    /**
     * метод для изменения значения вершины.
     *
     * @param value - новое значение
     */
    public void changeValue(T value) {
        this.value = value;
    }
}
