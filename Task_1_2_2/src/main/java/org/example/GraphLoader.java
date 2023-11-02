package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * класс для ввода графа из файла.
 */
public class GraphLoader {
    /**
     * метод для загрузки графа из файла.
     *
     * @param graph_type - тип графа
     * @param filename   - название файла, из которого загружается граф
     * @return - граф одного из трех типов или ничего
     */
    public static Graph<Integer> LoadTxt(Integer graph_type, String filename) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int vertx_amount = scanner.nextInt();
        int edges_amount = scanner.nextInt();

        ArrayList<Vertex<Integer>> vertices = new ArrayList<>(vertx_amount + 1);
        ArrayList<Edge<Integer>> edges = new ArrayList<>(edges_amount + 1);

        for (int i = 1; i < vertx_amount + 1; i++) {
            vertices.add(new Vertex<>(scanner.nextInt()));
        }

        for (int i = 1; i < edges_amount + 1; i++) {
            int src_id = scanner.nextInt();
            int dest_id = scanner.nextInt();
            double weight = scanner.nextDouble();
            edges.add(new Edge<>(vertices.get(src_id - 1), vertices.get(dest_id - 1), weight));
        }

        if (graph_type == 1) {
            return new GraphIncidentList<>(vertices, edges);
        } else if (graph_type == 2) {
            return new GraphAdjacencyMatrix<>(vertices, edges);
        } else if (graph_type == 3) {
            return new GraphIncidenceMatrix<>(vertices, edges);
        }
        return null;
    }
}