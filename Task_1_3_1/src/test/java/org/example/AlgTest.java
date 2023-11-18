package org.example;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.example.ApostolicoCrochemoreAlgorithm.find;

/**
 * класс для тестов.
 */
public class AlgTest {

    /**
     * просто проверка алгоритма.
     *
     * @param filename  - имя файла
     * @param substring - подстрока
     * @param expected  - ожидаемое значение
     */
    @ParameterizedTest
    @MethodSource("generateDataForBasicTests")
    public void basicTests(String filename, String substring, int[] expected) {
        var answer = find(filename, substring, StandardCharsets.UTF_8);
        for (int i = 0; i < answer.size(); i++) {
            Assertions.assertEquals(expected[i], answer.get(i));
        }
    }

    /**
     * генерация данных для тестов.
     *
     * @return - имя файла, подстрока, ожидаемое значение
     */
    static Stream<Arguments> generateDataForBasicTests() {
        return Stream.of(
                Arguments.arguments("input1.txt", "бра", new int[]{1, 8, 12, 15, 20})
        );
    }
}