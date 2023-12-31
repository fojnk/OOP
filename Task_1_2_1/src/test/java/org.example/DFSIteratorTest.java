package org.example;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * класс для тестрирования итератора DFS.
 */
public class DFSIteratorTest {
    /**
     * Тестирование метода next.
     *
     * @param expected       - ожидаемое значение
     * @param tree           - дерево
     * @param amountOfNextOp - количество операций next, выполненных до получения ответа
     */
    @ParameterizedTest
    @MethodSource("generateDataForNextAndHasNext")
    public void nextTest(Integer expected, Tree<Integer> tree, Integer amountOfNextOp) {
        Integer result = null;
        var dfs = new DFSIterator<Integer>(tree);
        for (int i = 0; i < amountOfNextOp; i++) {
            result = dfs.next();
        }
        Assertions.assertEquals(expected, result);
    }

    /**
     * Тестирование метода hasNext.
     *
     * @param expected          - ожидаемое значение
     * @param tree              - дерево
     * @param amountOfHasNextOp - количество операций next, выполненных до получения ответа
     */
    @ParameterizedTest
    @MethodSource("generateDataForNextAndHasNext")
    public void hasNextTest(Integer expected, Tree<Integer> tree, Integer amountOfHasNextOp) {
        boolean result = false;
        var dfs = new DFSIterator<Integer>(tree);
        for (int i = 0; i < amountOfHasNextOp; i++) {
            result = dfs.hasNext();
            dfs.next();
        }
        Assertions.assertEquals((boolean) (expected != null), result);
    }

    /**
     * генерация данных.
     *
     * @return - аргументы вида (ожидаемое значение элемента, дерево, количество операций next)
     */
    static Stream<Arguments> generateDataForNextAndHasNext() {
        var tree = new Tree<>(1);
        var a = tree.addChild(2);
        tree.addChild(3);
        a.addChild(4);

        return Stream.of(
                Arguments.arguments(1, tree, 1),
                Arguments.arguments(3, tree, 2),
                Arguments.arguments(2, tree, 3),
                Arguments.arguments(4, tree, 4)
        );
    }
}