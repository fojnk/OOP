package org.example;

import java.util.ArrayList;
import java.util.HashMap;

/*Идея заключается в том, чтобы создать абстрактный класс, от которого
в последствии смогу унаследоваться разные типы графов (матрица смежности и т.д),
однако есть одно ущемление, связанное с хранением вершин и ребер. Т.к вершина может
быть чем угодно -> мы можем находить их только по id, поэтому скорее всего ввод
будет неоптимизированным и неудобным SORRY :(.
 */

/**
 * Создаю абстрактный класс, чтобы далее была возможность унаследоваться от него.
 * @param <T> - Тип вершины
 * @param <N> - Численный тип веса для ребра
 */
public abstract class Graph<T, N> {
    private final HashMap<Integer, Vertex<T>> vertexes;
    private final HashMap<Integer, Edge<T, N>> edges;

    public Graph(Vertex<T>[] ver_array, Edge<T, N>[] edg_array) {
        int i = 0;
        vertexes = new HashMap<>();
        for (var vert: ver_array) {
            vertexes.put(i, vert);
            i++;
        }
        i = 0;
        edges = new HashMap<>();
        for (var edg: edg_array) {
            edges.put(i, edg);
            i++;
        }
    }
    /**
     * метод для получения вершины по id.
     * @param Id - номер вершины по порядку поступления в граф
     * @return - вершина
     */
    public Vertex<T> getVertexById(Integer Id) {
        return vertexes.get(Id);
    }

    /**
     * метод для изменения вершины по id.
     * @param key - номер вершины
     * @param value - новая вершина
     */
    public void changeVertexById(Integer key, Vertex<T> value) {
        vertexes.put(key, value);
    }

    /**
     * Метод для изменения ребро по id.
     * @param key - номер ребра
     * @param value - новое значение
     */
    public void changeEdgeById(Integer key, Edge<T, N> value) {
        edges.put(key, value);
    }

    /**
     * метод для получения ребра по id.
     * @param Id - номер ребра
     * @return - ребро
     */
    public Edge<T, N> getEdgeById(Integer Id) {
        return edges.get(Id);
    }

    /**
     * метод для получения списка вершин.
     * @return - список вершин
     */
    public ArrayList<Vertex<T>> getListOfVertexes() {
        return new ArrayList<>(vertexes.values());
    }

    /**
     * метод для получения списка ребер.
     * @return - список ребер
     */
    public ArrayList<Edge<T, N>> getListOfEdges() {
        return new ArrayList<>(edges.values());
    }


    /**
     * абстрактный метод добавления вершины.
     * @param vertex - добавляемая вершина
     */
    public abstract void addVertex(Vertex<T> vertex);

    /**
     * абстрактный метод удаления вершины.
     * @param vertex - удаляемая вершина
     */
    public abstract void deleteVertex(Vertex<T> vertex);

    /**
     * абстрактный метод добавления ребра.
     * @param edge - ребро
     */
    public abstract void addEdge(Edge<T, N> edge);

    /**
     * абстрактный метод удаления ребра.
     * @param edge - ребро
     */
    public abstract void deleteEdge(Edge<T, N> edge);

    public abstract HashMap<Vertex<T>, N> dijkstra(Vertex<T> start);
}