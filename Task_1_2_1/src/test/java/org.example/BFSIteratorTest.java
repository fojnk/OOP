package org.example;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * класса тестов для BFS.
 */
public class BFSIteratorTest {
    /**
     * метод для тестирования фунции next.
     *
     * @param expected       - ожидаемое значение
     * @param tree           - дерево
     * @param amountOfNextOp - количество операций next, выполненных до получения ответа
     */
    @ParameterizedTest
    @MethodSource("generateDataForNextAndHasNext")
    public void nextTest(Integer expected, Tree<Integer> tree, Integer amountOfNextOp) {
        Integer result = null;
        var bfs = new BFSIterator<>(tree);
        for (int i = 0; i < amountOfNextOp; i++) {
            result = bfs.next();
        }
        Assertions.assertEquals(expected, result);
    }

    /**
     * метод для тестирования hasNext.
     *
     * @param expected          - ожидаемое значение
     * @param tree              - дерево
     * @param amountOfHasNextOp - количество операций next, выполненных до получения ответа
     */
    @ParameterizedTest
    @MethodSource("generateDataForNextAndHasNext")
    public void hasNextTest(Integer expected, Tree<Integer> tree, Integer amountOfHasNextOp) {
        boolean result = false;
        var bfs = new BFSIterator<>(tree);
        for (int i = 0; i < amountOfHasNextOp; i++) {
            result = bfs.hasNext();
            bfs.next();
        }
        Assertions.assertEquals(expected != null, result);
    }

    /**
     * генерация данных.
     *
     * @return - аргументы вида (ожидаемое значение элемента, дерево, количество операций next)
     */
    static Stream<Arguments> generateDataForNextAndHasNext() {
        Tree<Integer> tree = new Tree<>(1);
        var a = tree.addChild(2);
        tree.addChild(3);
        a.addChild(4);

        return Stream.of(
                Arguments.arguments(1, tree, 1),
                Arguments.arguments(2, tree, 2),
                Arguments.arguments(3, tree, 3),
                Arguments.arguments(4, tree, 4)
        );
    }
}