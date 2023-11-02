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
     * @param graphType - тип графа
     * @param filename  - название файла, из которого загружается граф
     * @return - граф одного из трех типов или ничего
     */
    public static Graph<Integer> loadTxt(Integer graphType, String filename) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int vertAmount = scanner.nextInt();
        int edgesAmount = scanner.nextInt();

        ArrayList<Vertex<Integer>> vertices = new ArrayList<>(vertAmount + 1);
        ArrayList<Edge<Integer>> edges = new ArrayList<>(edgesAmount + 1);

        for (int i = 1; i < vertAmount + 1; i++) {
            vertices.add(new Vertex<>(scanner.nextInt()));
        }

        for (int i = 1; i < edgesAmount + 1; i++) {
            int srcId = scanner.nextInt();
            int destId = scanner.nextInt();
            double weight = scanner.nextDouble();
            edges.add(new Edge<>(vertices.get(srcId - 1), vertices.get(destId - 1), weight));
        }

        if (graphType == 1) {
            return new GraphIncidentList<>(vertices, edges);
        } else if (graphType == 2) {
            return new GraphAdjacencyMatrix<>(vertices, edges);
        } else if (graphType == 3) {
            return new GraphIncidenceMatrix<>(vertices, edges);
        }
        return null;
    }
}