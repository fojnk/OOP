package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.example.ApostolicoCrochemoreAlgorithm.find;

public class AlgTest {

    @ParameterizedTest
    @MethodSource("generateDataForBasicTests")
    public void basicTests(String filename, String substring, int[] expected) {
        var answer = find(filename, substring);
        for (int i = 0; i < answer.size(); i ++) {
            Assertions.assertEquals(expected[i], answer.get(i));
        }
    }

    static Stream<Arguments> generateDataForBasicTests() {
        return Stream.of(
                Arguments.arguments("./src/test/java/org/example/input1.txt", "бра", new int[] {1, 8, 12, 15, 20})
        );
    }
}