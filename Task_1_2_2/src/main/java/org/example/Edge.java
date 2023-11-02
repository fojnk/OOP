package org.example;

/**
 * класс для представления ребра.
 *
 * @param <T> - тип, который будет являться типов вершины.
 */
public class Edge<T> {

    private final Vertex<T> src;
    private final Vertex<T> dest;
    private Double weight;

    /**
     * конструктор ребра.
     *
     * @param src    - вершина начала ребра
     * @param dest   - вершина окончания ребра
     * @param weight - вес
     */
    public Edge(Vertex<T> src, Vertex<T> dest, Double weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    /**
     * метод для получения веса.
     *
     * @return - вес
     */
    public Double getWeight() {
        return this.weight;
    }

    /**
     * метод для получения начальной вершины.
     *
     * @return - вершина
     */
    public Vertex<T> getSrc() {
        return this.src;
    }

    /**
     * метод для получения конечной вершины.
     *
     * @return - конечная вершина
     */
    public Vertex<T> getDest() {
        return this.dest;
    }

    /**
     * метод для изменения веса ребра.
     *
     * @param weight - вес
     */
    public void changeWeight(Double weight) {
        this.weight = weight;
    }
}