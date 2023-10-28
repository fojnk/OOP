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
 */
public abstract class Graph<T> {
    protected final HashMap<Integer, Vertex<T>> vertexes;
    protected int maxVertId;
    protected final HashMap<Integer, Edge<T>> edges;

    protected int maxEdgeId;

    public Graph() {
        this.vertexes = new HashMap<>();
        this.edges = new HashMap<>();
        this.maxEdgeId = 1;
        this.maxVertId = 1;
    }

    public Graph(ArrayList<Vertex<T>> ver_array, ArrayList<Edge<T>> edg_array) {
        int i = 1;
        vertexes = new HashMap<>();
        for (var vert: ver_array) {
            vertexes.put(i, vert);
            i++;
        }
        this.maxVertId = i;
        i = 1;
        edges = new HashMap<>();
        for (var edg: edg_array) {
            edges.put(i, edg);
            i++;
        }
        this.maxEdgeId = i;
    }
    /**
     * метод для получения вершины по id.
     * @param Id - номер вершины по порядку поступления в граф
     * @return - вершина
     */
    public Vertex<T> getVertexById(Integer Id) {
        return vertexes.get(Id);
    }

    public Object getIdByVertex(Vertex<T> vert) {
        for (var vert_id: this.vertexes.keySet()) {
            if (this.getVertexById(vert_id) == vert) {
                return vert_id;
            }
        }
        return null;
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
    public void changeEdgeById(Integer key, Edge<T> value) {
        edges.put(key, value);
    }

    /**
     * метод для получения ребра по id.
     * @param Id - номер ребра
     * @return - ребро
     */
    public Edge<T> getEdgeById(Integer Id) {
        return edges.get(Id);
    }

    public Object getIdByEdge(Edge<T> e) {
        for (var edge_id: this.edges.keySet()) {
            if (this.getEdgeById(edge_id) == e) {
                return edge_id;
            }
        }
        return null;
    }

    public Boolean containsVertex(Vertex<T> vertex) {
        for (var vert: this.vertexes.values()) {
            if (vert == vertex) {
                return true;
            }
        }
        return false;
    }

    public Boolean containsEdge(Edge<T> edge) {
        for (var e: this.edges.values()) {
            if (e == edge) {
                return true;
            }
        }
        return false;
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
    public ArrayList<Edge<T>> getListOfEdges() {
        return new ArrayList<>(edges.values());
    }

    public Edge<T> getEdge(Vertex<T> src, Vertex<T> dest) {
        for (var edge: this.edges.values()) {
            if (edge.getSrc() == src && edge.getDest() == dest) {
                return edge;
            }
        }
        return null;
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
    public abstract void addEdge(Edge<T> edge);

    /**
     * абстрактный метод удаления ребра.
     * @param edge - ребро
     */
    public abstract void deleteEdge(Edge<T> edge);

    public abstract HashMap<Integer, Double> dijkstra(Integer start);
}