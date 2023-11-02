package org.example;

import static org.example.GraphLoader.loadTxt;

/**
 * главный класс.
 */
public class Main {

    /**
     * входная точка программы.
     *
     * @param args - аргументы командной строки
     */
    public static void main(String[] args) {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%      Dijkstra tests     %");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("\n%%%%% Correct output %%%%%%");
        Double[] correctOutput = new Double[]{0.0, 7.0, 9.0, 20.0, 20.0, 11.0};
        for (int i = 1; i < 7; i++) {
            System.out.println("vertex id: " + i + "      dist: " + correctOutput[i - 1]);
        }
        printRes("Task_1_2_2/src/main/java/org/example/graph_input.txt", graphType.incidenceList, 1);
        printRes("Task_1_2_2/src/main/java/org/example/graph_input.txt", graphType.adjacencyMatrix, 1);
        printRes("Task_1_2_2/src/main/java/org/example/graph_input.txt", graphType.incidenceMatrix, 1);

    }

    /**
     * метод для вывода матриц на экран.
     *
     * @param filename  - название файла загрузки
     * @param type - тип создаваемого графа
     * @param startVert - начальная вершина для алгоритма Дейкстры
     */
    public static void printRes(String filename, graphType type, int startVert) {
        var g = loadTxt(type, "Task_1_2_2/src/main/java/org/example/graph_input.txt");
        var answer = g.dijkstra(1);
        switch (type) {
            case incidenceList:
                System.out.println("\n%%%%% GraphIncidentList %%%%%%");
                break;
            case adjacencyMatrix:
                System.out.println("\n%%%%% GraphAdjacencyMatrix %%%%%%");
                break;
            case incidenceMatrix:
                System.out.println("\n%%%%% GraphIncidenceMatrix %%%%%%");
                break;
            default:
                System.out.println("unknown type");
                break;
        }
        System.out.println("------------------------\n" + "start vertex_id: " + startVert);
        for (var vert : answer.keySet()) {
            System.out.println("vertex id: " + vert + "      dist: " + answer.get(vert));
        }
        System.out.println("------------------------");
    }
}