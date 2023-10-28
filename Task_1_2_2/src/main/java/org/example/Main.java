package org.example;

import static org.example.GraphLoader.LoadTxt;

public class Main {
    public static void main(String[] args) {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%      Dijkstra tests     %");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("\n%%%%% Correct output %%%%%%");
        Double[] correct_output = new Double[]{0.0, 7.0, 9.0, 20.0, 20.0, 11.0};
        for (int i = 1; i < 7; i ++) {
            System.out.println("vertex id: " + i + "      dist: " + correct_output[i - 1]);
        }
        printRes("Task_1_2_2/src/main/java/org/example/graph_input.txt", 1, 1);
        printRes("Task_1_2_2/src/main/java/org/example/graph_input.txt", 2, 1);
        printRes("Task_1_2_2/src/main/java/org/example/graph_input.txt", 3, 1);

    }

    public static void printRes(String filename, int graph_type, int start_vert)  {
        var g = LoadTxt(graph_type, "Task_1_2_2/src/main/java/org/example/graph_input.txt");
        var answer = g.dijkstra(1);
        if (graph_type == 1){
            System.out.println("\n%%%%% GraphIncidentList %%%%%%");
        }
        else if (graph_type == 2) {
            System.out.println("\n%%%%% GraphAdjacencyMatrix %%%%%%");
        }
        else if (graph_type == 3) {
            System.out.println("\n%%%%% GraphIncidenceMatrix %%%%%%");
        }
        System.out.println("------------------------\n" + "start vertex_id: " + start_vert);
        for (var vert: answer.keySet()) {
            System.out.println("vertex id: " + vert + "      dist: " + answer.get(vert));
        }
        System.out.println("------------------------");
    }
}