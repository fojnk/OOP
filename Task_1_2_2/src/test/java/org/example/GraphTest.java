package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.stream.Stream;
import static org.example.GraphLoader.LoadTxt;
import static org.junit.jupiter.api.Assertions.*;

/**
 * класс для тестов.
 */
public class GraphTest {

    private Path workingDir;

    /**
     * метод для тестирования основных функций всех типов графов.
     *
     * @param g - граф
     */
    @ParameterizedTest
    @MethodSource("generateClassesForCraphTest")
    public void basicGraphTest(Graph<Integer> g) {
        var v1 = new Vertex<>(1);
        var v2 = new Vertex<>(2);
        var v3 = new Vertex<>(3);
        var e1 = new Edge<>(v1, v2, 5.0);
        var e2 = new Edge<>(v2, v3, 2.0);
        var e3 = new Edge<>(v3, v2, 1.0);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        assertTrue(g.containsVertex(v1));
        assertTrue(g.containsVertex(v2));
        assertTrue(g.containsVertex(v3));
        g.addEdge(e1);
        assertTrue(g.containsEdge(e1));
        g.deleteVertex(v1);
        assertFalse(g.containsVertex(v1));
        assertFalse(g.containsEdge(e1));
        g.addEdge(e2);
        g.addEdge(e3);
        assertTrue(g.containsEdge(e2));
        assertTrue(g.containsEdge(e3));
        g.deleteEdge(e2);
        assertFalse(g.containsEdge(e2));
        assertTrue(g.containsEdge(e3));
        g.addEdge(e2);
        g.deleteVertex(v2);
        assertFalse(g.containsEdge(e2));
        assertFalse(g.containsEdge(e3));
    }

    /**
     * генерация разных типов графов.
     *
     * @return - разные графы
     */
    static Stream<Arguments> generateClassesForCraphTest() {
        return Stream.of(
                Arguments.arguments(new GraphIncidentList<>()),
                Arguments.arguments(new GraphAdjacencyMatrix<>()),
                Arguments.arguments(new GraphIncidenceMatrix<>())
        );
    }

    /**
     * метод для тестирования алгоритма Дейкстры.
     *
     * @param graphType    - тип графа
     * @param filename     - имя файла, из которого считываем граф
     * @param expected     - ожидаемый результат
     * @param amountOfVert - количество вершин
     */
    @ParameterizedTest
    @MethodSource("generateClassesForDijkstraTest")
    public void dijkstraTest(int graphType, String filename, HashMap<Integer, Double> expected,
                             int amountOfVert) {
        this.workingDir = Path.of("", "src");
        var asw = LoadTxt(graphType, String.valueOf(this.workingDir.resolve(filename))).dijkstra(1);
        for (int i = 1; i < amountOfVert + 1; i++) {
            assertEquals(expected.get(i), asw.get(i));
        }
    }

    /**
     * генерация данных для тестирования Дейкстры.
     *
     * @return - четверки вида(тип графа, имя файла, ожидаемое значение, кол-во вершин)
     */
    static Stream<Arguments> generateClassesForDijkstraTest() {
        HashMap<Integer, Double> answer = new HashMap<>();
        answer.put(1, 0.0);
        answer.put(2, 7.0);
        answer.put(3, 9.0);
        answer.put(4, 20.0);
        answer.put(5, 20.0);
        answer.put(6, 11.0);
        String filename = "test/java/org/example/test1.txt";
        return Stream.of(
                Arguments.arguments(1, filename, answer, 6),
                Arguments.arguments(2, filename, answer, 6),
                Arguments.arguments(3, filename, answer, 6)
        );
    }

    /**
     * метод для тестирования ошибок.
     *
     * @param g - граф
     */
    @ParameterizedTest
    @MethodSource("generateClassesForCraphTest")
    public void throwErrorsTest(Graph<Integer> g) {
        var thrown = false;
        var v1 = new Vertex<>(1);
        var v2 = new Vertex<>(2);
        var e1 = new Edge<>(v1, v2, 5.0);
        g.addVertex(v1);
        g.addVertex(v2);
        try {
            g.addEdge(e1);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertFalse(thrown);
        g.deleteVertex(v1);
        try {
            g.addEdge(e1);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    /**
     * метод для тестирования менее важных операций.
     *
     * @param g - граф
     */
    @ParameterizedTest
    @MethodSource("generateClassesForCraphTest")
    public void basicOpPart2Test(Graph<Integer> g) {
        var v1 = new Vertex<>(1);
        var v2 = new Vertex<>(2);
        var e1 = new Edge<>(v1, v2, 5.0);
        assertEquals(1, v1.getValue());
        assertEquals(2, v2.getValue());
        v2.changeValue(1);
        assertEquals(1, v2.getValue());
        v2.changeValue(2);
        assertEquals(2, v2.getValue());
        g.addVertex(v1);
        g.addVertex(v2);
        g.containsVertex(v1);
        g.containsVertex(v2);
        v1.changeValue(2);
        assertEquals(2, v1.getValue());
        g.addEdge(e1);
        e1.changeWeight(3.0);
        assertEquals(3.0, e1.getWeight());
    }
}