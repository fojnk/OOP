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
     * @param type - тип графа
     * @param filename - название файла
     * @return - граф
     */
    public static Graph<Integer> loadTxt(GraphType type, String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));

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

            switch (type) {
                case incidenceList:
                    return new GraphIncidentList<>(vertices, edges);
                case adjacencyMatrix:
                    return new GraphAdjacencyMatrix<>(vertices, edges);
                case incidenceMatrix:
                    return new GraphIncidenceMatrix<>(vertices, edges);
                default:
                    System.out.println("unknown type");
                    break;
            }
            return null;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}