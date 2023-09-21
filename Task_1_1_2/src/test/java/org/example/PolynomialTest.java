package org.example;

import org.example.Polynomial;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PolynomialTest {
    @ParameterizedTest
    @MethodSource("generateDataForMinus")
    public void minusTest(Polynomial expected, Polynomial p1, Polynomial p2) {
        Arguments.assertArrayEquals(expected.getCofArray(), p1.minus(p2).getCofArray());
    }

    @ParameterizedTest
    @MethodSource("generateDataForPlus")
    public void plusTest(Polynomial expected, Polynomial p1, Polynomial p2) {
        Arguments.assertArrayEquals(expected.getCofArray(), p1.plus(p2).getCofArray());
    }

    @ParameterizedTest
    @MethodSource("generateDataForTimes")
    public void timesTest(Polynomial expected, Polynomial p1, Polynomial p2) {
        Arguments.assertArrayEquals(expected.getCofArray(), p1.times(p2).getCofArray());
    }

    @ParameterizedTest
    @MethodSource("generateData")
    public void evaluateTest(int[] expected, int[] input) {

    }

    @ParameterizedTest
    @MethodSource("generateData")
    public void differentiateTest(int[] expected, int[] input) {

    }

    @ParameterizedTest
    @MethodSource("generateData")
    public void toStringTest(int[] expected, int[] input) {

    }

    @ParameterizedTest
    @MethodSource("generateData")
    public void equalsTest(int[] expected, int[] input) {

    }

    static Stream<Arguments> generateDataForPlus() {
        return Stream.of(
                Arguments.arguments(new Polynomial(new int[] {1, 2, 3, 4}), new Polynomial(new int[] {1, 2, 3, 3}), new Polynomial(new int[] {0, 0, 0, 1})),
                Arguments.arguments(new Polynomial(new int[] {1, 2, 3}), new Polynomial(new int[] {1, 2, 3, 3}), new Polynomial(new int[] {1, 2, 3, -3})),
                Arguments.arguments(new Polynomial(new int[] {}), new Polynomial(new int[] {-1, -2, -3, -4}), new Polynomial(new int[] {1, 2, 3, 4}))
        );
    }

    static Stream<Arguments> generateDataForMinus() {
        return Stream.of(
                Arguments.arguments(new Polynomial(new int[] {1, 2, 3, 2}), new Polynomial(new int[] {1, 2, 3, 3}), new Polynomial(new int[] {0, 0, 0, 1})),
                Arguments.arguments(new Polynomial(new int[] {0, 0, 0, 6}), new Polynomial(new int[] {1, 2, 3, 3}), new Polynomial(new int[] {1, 2, 3, -3})),
                Arguments.arguments(new Polynomial(new int[] {-2, -4, -6, -8}), new Polynomial(new int[] {-1, -2, -3, -4}), new Polynomial(new int[] {1, 2, 3, 4}))
                );
    }

    static Stream<Arguments> generateDataForTimes() {
        return Stream.of(
                Arguments.arguments(new Polynomial(new int[] {}), new Polynomial(new int[] {}), new Polynomial(new int[] {0, 0, 0, 1})),
                Arguments.arguments(new Polynomial(new int[] {0, 0, 0, 6}), new Polynomial(new int[] {0, 2}), new Polynomial(new int[] {0, 0, 3})),
                Arguments.arguments(new Polynomial(new int[] {1, 1, 1, 1}), new Polynomial(new int[] {1}), new Polynomial(new int[] {1, 1, 1, 1}))
                );
    }
}