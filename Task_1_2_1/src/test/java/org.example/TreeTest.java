package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * класс для тестрирования дерева.
 */
public class TreeTest {
    /**
     * метод для тестирования добавления элементов.
     *
     * @param expected - ожидаемое значение
     * @param input    - входное значение
     */
    @ParameterizedTest
    @MethodSource("generateDataForAddChild")
    public void addChildTest(Integer expected, Integer input) {
        Assertions.assertEquals(expected, input);
    }

    /**
     * данные для метода добавления элементов.
     *
     * @return - аргументы вида (ожидаемое значение, значение из элемента)
     */
    static Stream<Arguments> generateDataForAddChild() {
        Tree<Integer> tree = new Tree<>(1);
        var a = tree.addChild(2);
        tree.addChild(3);
        a.addChild(4);

        return Stream.of(
                Arguments.arguments(1, tree.getValue()),
                Arguments.arguments(2, tree.getChilds().get(0).getValue()),
                Arguments.arguments(3, tree.getChilds().get(1).getValue()),
                Arguments.arguments(4, tree.getChilds().get(0).getChilds().get(0).getValue())
        );
    }

    /**
     * метод для тестрирования удаления элементов.
     *
     * @param parent - родитель
     * @param elem   - ребенок
     */
    @ParameterizedTest
    @MethodSource("generateDataForRemove")
    public void removeTest(Tree<Integer> parent, Tree<Integer> elem) {
        Assertions.assertTrue(parent.getChilds().contains(elem));
        elem.remove();
        Assertions.assertFalse(parent.getChilds().contains(elem));
    }

    /**
     * данный для удаления.
     *
     * @return - аргументы вида (родитель, ребенок)
     */
    static Stream<Arguments> generateDataForRemove() {
        Tree<Integer> tree = new Tree<>(1);
        var a = tree.addChild(2);
        tree.addChild(3);
        var b = a.addChild(4);

        return Stream.of(
                Arguments.arguments(a, b),
                Arguments.arguments(tree, a)
        );
    }

    /**
     * метод для тестирования сравнения деревьев.
     *
     * @param tree1    - первое дерево
     * @param tree2    - второе дерево
     * @param expected - ожидаемое значение
     */
    @ParameterizedTest
    @MethodSource("generateDataForEquals")
    public void equalsTest(Tree<Integer> tree1, Tree<Integer> tree2, boolean expected) {
        Assertions.assertEquals(tree1.equals(tree2), expected);
    }

    /**
     * генерация данных.
     *
     * @return - аргументы вида(первое дерево, второе дерево, ожидаемое занчение)
     */
    static Stream<Arguments> generateDataForEquals() {
        Tree<Integer> tree1 = new Tree<>(1);
        var a = tree1.addChild(2);
        tree1.addChild(3);
        var b = a.addChild(4);

        Tree<Integer> tree2 = new Tree<>(4);
        tree2.addChild(1);
        tree2.addChild(2);

        return Stream.of(
                Arguments.arguments(tree2, tree2, true),
                Arguments.arguments(tree1, tree2, false),
                Arguments.arguments(tree1, tree1, true)
        );
    }
}