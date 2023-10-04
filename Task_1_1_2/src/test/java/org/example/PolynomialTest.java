package org.example;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * тестирование полинома.
 */
public class PolynomialTest {

    /**
     * текст.
     *
     * @param expected - ожидание
     * @param p1       - первый полином
     * @param p2       - второй полином
     */
    @ParameterizedTest
    @MethodSource("generateDataForMinus")
    public void minusTest(Polynomial expected, Polynomial p1, Polynomial p2) {
        Assertions.assertArrayEquals(expected.getCofArray(), p1.minus(p2).getCofArray());
    }

    static Stream<Arguments> generateDataForMinus() {
        return Stream.of(
                Arguments.arguments(new Polynomial(new int[]{1, 2, 3, 2}),
                        new Polynomial(new int[]{1, 2, 3, 3}),
                        new Polynomial(new int[]{0, 0, 0, 1})),
                Arguments.arguments(new Polynomial(new int[]{0, 0, 0, 6}),
                        new Polynomial(new int[]{1, 2, 3, 3}),
                        new Polynomial(new int[]{1, 2, 3, -3})),
                Arguments.arguments(new Polynomial(new int[]{-2, -4, -6, -8}),
                        new Polynomial(new int[]{-1, -2, -3, -4}),
                        new Polynomial(new int[]{1, 2, 3, 4})),
                Arguments.arguments(new Polynomial(new int[]{-1, -2, -3, -4}),
                        new Polynomial(new int[]{0}),
                        new Polynomial(new int[]{1, 2, 3, 4}))
        );
    }

    /**
     * текст.
     *
     * @param expected - ожидание
     * @param p1       - первый полином
     * @param p2       - второй полином
     */
    @ParameterizedTest
    @MethodSource("generateDataForPlus")
    public void plusTest(Polynomial expected, Polynomial p1, Polynomial p2) {
        Assertions.assertArrayEquals(expected.getCofArray(), p1.plus(p2).getCofArray());
    }

    static Stream<Arguments> generateDataForPlus() {
        return Stream.of(
                Arguments.arguments(new Polynomial(new int[]{1, 2, 3, 4}),
                        new Polynomial(new int[]{1, 2, 3, 3}),
                        new Polynomial(new int[]{0, 0, 0, 1})),
                Arguments.arguments(new Polynomial(new int[]{1, 2, 3}),
                        new Polynomial(new int[]{1, 2, 3, 3}),
                        new Polynomial(new int[]{0, 0, 0, -3})),
                Arguments.arguments(new Polynomial(new int[]{}),
                        new Polynomial(new int[]{-1, -2, -3, -4}),
                        new Polynomial(new int[]{1, 2, 3, 4}))
        );
    }

    /**
     * текст.
     *
     * @param expected - ожидание
     * @param p1       - первый полином
     * @param p2       - второй полином
     */
    @ParameterizedTest
    @MethodSource("generateDataForTimes")
    public void timesTest(Polynomial expected, Polynomial p1, Polynomial p2) {
        Assertions.assertArrayEquals(expected.getCofArray(), p1.times(p2).getCofArray());
    }

    static Stream<Arguments> generateDataForTimes() {
        return Stream.of(
                Arguments.arguments(new Polynomial(new int[]{}),
                        new Polynomial(new int[]{}),
                        new Polynomial(new int[]{0, 0, 0, 1})),
                Arguments.arguments(new Polynomial(new int[]{0, 0, 0, 6}),
                        new Polynomial(new int[]{0, 2}),
                        new Polynomial(new int[]{0, 0, 3})),
                Arguments.arguments(new Polynomial(new int[]{1, 1, 1, 1}),
                        new Polynomial(new int[]{1}),
                        new Polynomial(new int[]{1, 1, 1, 1}))
        );
    }

    /**
     * текст.
     *
     * @param expected - ожидание
     * @param p        - полином
     * @param value    - число
     */
    @ParameterizedTest
    @MethodSource("generateDataForEvaluate")
    public void evaluateTest(int expected, Polynomial p, int value) {
        Assertions.assertEquals(expected, p.evaluate(value));
    }

    static Stream<Arguments> generateDataForEvaluate() {
        return Stream.of(
                Arguments.arguments(6, new Polynomial(new int[]{1, 2, 3}), 1),
                Arguments.arguments(0, new Polynomial(new int[]{}), 1),
                Arguments.arguments(142, new Polynomial(new int[]{1, 2, 3, 4}), 3)
        );
    }

    /**
     * текст.
     *
     * @param expected - ожидание
     * @param p1       - полином
     */
    @ParameterizedTest
    @MethodSource("generateDataForDiff1")
    public void differentiateTest1(Polynomial expected, Polynomial p1) {
        Assertions.assertArrayEquals(expected.getCofArray(), p1.differentiate(1).getCofArray());
    }

    static Stream<Arguments> generateDataForDiff1() {
        return Stream.of(
                Arguments.arguments(new Polynomial(new int[]{2, 6}),
                        new Polynomial(new int[]{1, 2, 3})),
                Arguments.arguments(new Polynomial(new int[]{3}),
                        new Polynomial(new int[]{2, 3})),
                Arguments.arguments(new Polynomial(new int[]{}),
                        new Polynomial(new int[]{3})),
                Arguments.arguments(new Polynomial(new int[]{}),
                        new Polynomial(new int[]{}))
        );
    }

    /**
     * текст.
     *
     * @param expected - ожидание
     * @param p1       - полином
     */
    @ParameterizedTest
    @MethodSource("generateDataForDiff2")
    public void differentiateTest2(Polynomial expected, Polynomial p1) {
        Assertions.assertArrayEquals(expected.getCofArray(), p1.differentiate(2).getCofArray());
    }

    static Stream<Arguments> generateDataForDiff2() {
        return Stream.of(
                Arguments.arguments(new Polynomial(new int[]{6}),
                        new Polynomial(new int[]{1, 2, 3})),
                Arguments.arguments(new Polynomial(new int[]{}),
                        new Polynomial(new int[]{2, 3})),
                Arguments.arguments(new Polynomial(new int[]{}),
                        new Polynomial(new int[]{3}))
        );
    }

    /**
     * текс.
     *
     * @param expected - ожидание
     * @param p        - полином
     */
    @ParameterizedTest
    @MethodSource("generateDataForStrings")
    public void toStringTest(String expected, Polynomial p) {
        Assertions.assertEquals(expected, p.toString());
    }

    static Stream<Arguments> generateDataForStrings() {
        return Stream.of(
                Arguments.arguments("3x + 2", new Polynomial(new int[]{2, 3})),
                Arguments.arguments("5x^3 + 4x^2 + 3x + 2", new Polynomial(new int[]{2, 3, 4, 5})),
                Arguments.arguments("0", new Polynomial(new int[]{}))
        );
    }

    /**
     * текст.
     *
     * @param expected - ожидание
     * @param p1       - первый полином
     * @param p2       - второй полином
     */
    @ParameterizedTest
    @MethodSource("generateDataForEquals")
    public void equalsTest(boolean expected, Polynomial p1, Polynomial p2) {
        Assertions.assertEquals(expected, p1.equals(p2));
    }

    static Stream<Arguments> generateDataForEquals() {
        return Stream.of(
                Arguments.arguments(false, new Polynomial(new int[]{2, 3}),
                        new Polynomial(new int[]{1, 2, 3})),
                Arguments.arguments(true, new Polynomial(new int[]{2, 3}),
                        new Polynomial(new int[]{2, 3})),
                Arguments.arguments(true, new Polynomial(new int[]{3}),
                        new Polynomial(new int[]{3})),
                Arguments.arguments(true, new Polynomial(new int[]{0}),
                        new Polynomial(new int[]{0, 0, 0, 0})),
                Arguments.arguments(true, new Polynomial(new int[]{0}),
                        new Polynomial(new int[]{}))
        );
    }
}