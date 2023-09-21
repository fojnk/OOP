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
        Assertions.assertArrayEquals(expected.getCofArray(), p1.minus(p2).getCofArray());
    }

    @ParameterizedTest
    @MethodSource("generateDataForPlus")
    public void plusTest(Polynomial expected, Polynomial p1, Polynomial p2) {
        Assertions.assertArrayEquals(expected.getCofArray(), p1.plus(p2).getCofArray());
    }

    @ParameterizedTest
    @MethodSource("generateDataForTimes")
    public void timesTest(Polynomial expected, Polynomial p1, Polynomial p2) {
        Assertions.assertArrayEquals(expected.getCofArray(), p1.times(p2).getCofArray());
    }

    @ParameterizedTest
    @MethodSource("generateDataForEvaluate")
    public void evaluateTest(int expected, Polynomial p, int value) {
        Assertions.assertEquals(expected, p.evaluate(value));
    }

    @ParameterizedTest
    @MethodSource("generateDataForDiff")
    public void differentiateTest(Polynomial expected, Polynomial p1) {
        Assertions.assertArrayEquals(expected.getCofArray(), p1.differentiate(1).getCofArray());
    }

    @ParameterizedTest
    @MethodSource("generateDataForStrings")
    public void toStringTest(String expected, Polynomial p) {
        Assertions.assertEquals(expected, p.toString());
    }

    @ParameterizedTest
    @MethodSource("generateDataForEquals")
    public void equalsTest(boolean expected, Polynomial p1, Polynomial p2) {
        Assertions.assertEquals(expected, p1.equals(p2));
    }

    static Stream<Arguments> generateDataForPlus() {
        return Stream.of(
                Arguments.arguments(new Polynomial(new int[] {1, 2, 3, 4}), new Polynomial(new int[] {1, 2, 3, 3}), new Polynomial(new int[] {0, 0, 0, 1})),
                Arguments.arguments(new Polynomial(new int[] {1, 2, 3}), new Polynomial(new int[] {1, 2, 3, 3}), new Polynomial(new int[] {0, 0, 0, -3})),
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

    static Stream<Arguments> generateDataForDiff() {
        return Stream.of(
                Arguments.arguments(new Polynomial(new int[] {2, 3}), new Polynomial(new int[] {1, 2, 3})),
                Arguments.arguments(new Polynomial(new int[] {3}), new Polynomial(new int[] {2, 3})),
                Arguments.arguments(new Polynomial(new int[] {}), new Polynomial(new int[] {3}))
        );
    }

    static Stream<Arguments> generateDataForEvaluate() {
        return Stream.of(
                Arguments.arguments(6, new Polynomial(new int[] {1, 2, 3}), 1),
                Arguments.arguments(0, new Polynomial(new int[] {}), 1),
                Arguments.arguments(142, new Polynomial(new int[] {1, 2, 3, 4}), 3)
        );
    }

    static Stream<Arguments> generateDataForEquals() {
        return Stream.of(
                Arguments.arguments(false, new Polynomial(new int[] {2, 3}), new Polynomial(new int[] {1, 2, 3})),
                Arguments.arguments(true, new Polynomial(new int[] {2, 3}), new Polynomial(new int[] {2, 3})),
                Arguments.arguments(false, new Polynomial(new int[] {}), new Polynomial(new int[] {3}))
        );
    }

    static Stream<Arguments> generateDataForStrings() {
        return Stream.of(
                Arguments.arguments("3x + 2", new Polynomial(new int[] {2, 3})),
                Arguments.arguments("5x^3 + 4x^2 + 3x + 2", new Polynomial(new int[] {2, 3, 4, 5})),
                Arguments.arguments("", new Polynomial(new int[] {}))
        );
    }
}