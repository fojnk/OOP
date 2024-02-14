package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;
import org.example.primes.MultiThreadPrimeNumbersSearcher;
import org.example.primes.ParallelsPrimeNumbersSearcher;
import org.example.primes.PrimeNumbersChecker;
import org.example.primes.SequentialPrimeNumbersSearcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Класс для тестов.
 */
public class PrimeNumbersCheckerTest {
    /**
     * Проверка метода IsPrime во всех чекерах.
     */
    @Test
    public void primeTest() {
        var list1 = Arrays.asList(1, 3, 5, 7, 2, 2, 2, 11, 13, 2, 1);
        for (Integer integer : list1) {
            Assertions.assertTrue(PrimeNumbersChecker.IsPrime(integer));
        }

        var list2 = Arrays.asList(18, 25, 9, 4, 6, 6, 8, 10, 12, 22, 32, 14);
        for (Integer integer : list2) {
            Assertions.assertFalse(PrimeNumbersChecker.IsPrime(integer));
        }
    }

    /**
     * Проверка метода checkList во всех чекерах.
     *
     * @param checker - разные реализации чекеров
     * @throws InterruptedException - ошибка в одном из потоков
     */
    @ParameterizedTest
    @MethodSource("generateCheckersForTests")
    public void checkListTest(PrimeNumbersChecker checker) throws InterruptedException {
        var list1 = Arrays.asList(1, 3, 5, 7, 2, 2, 2, 11, 13, 2, 1);
        Assertions.assertTrue(checker.checkList(list1));

        var list2 = Arrays.asList(18, 25, 9, 4, 6, 6, 8, 10, 12, 22, 32, 14);
        Assertions.assertFalse(checker.checkList(list2));
    }

    /**
     * Проверка многопоточной реализации.
     *
     * @param list     - список чисел
     * @param expected - ожидаемое значение
     * @throws InterruptedException - ошибка в одном из потоков
     */
    @ParameterizedTest
    @MethodSource("generateListsForTests")
    public void threadsTest(List<Integer> list, boolean expected) throws InterruptedException {
        var checker1 = new MultiThreadPrimeNumbersSearcher();
        for (int i = 1; i < list.size(); i++) {
            checker1.setAmountOfThreads(i);
            Assertions.assertEquals(checker1.checkList(list), expected);
        }
    }

    /**
     * тест ParallelStream заданным пулом потоков.
     *
     * @param list     - список чисел
     * @param expected - ожидаемое значение
     * @throws ExecutionException   - ошибка выполнения
     * @throws InterruptedException - прерывание
     */
    @ParameterizedTest
    @MethodSource("generateListsForTests")
    public void ParallelStream(List<Integer> list, boolean expected) throws ExecutionException, InterruptedException {
        var checker = new ParallelsPrimeNumbersSearcher();
        for (int i = 1; i < list.size(); i++) {
            Assertions.assertEquals(checker.checkList(list, i), expected);
        }
    }

    /**
     * генерация чекеров.
     *
     * @return - чекеры
     */
    static Stream<Arguments> generateCheckersForTests() {
        var checker1 = new MultiThreadPrimeNumbersSearcher();
        var checker2 = new SequentialPrimeNumbersSearcher();
        var checker3 = new ParallelsPrimeNumbersSearcher();
        return Stream.of(
                Arguments.arguments(checker1),
                Arguments.arguments(checker2),
                Arguments.arguments(checker3)
        );
    }

    /**
     * генерация списков и ожидаемых значений.
     *
     * @return - списки и ожидаемые значения
     */
    static Stream<Arguments> generateListsForTests() {
        var list1 = Arrays.asList(1, 3, 5, 7, 2, 2, 2, 11, 13, 2, 1);
        var list2 = Arrays.asList(18, 25, 9, 4, 6, 6, 8, 10, 12, 22, 32, 14);
        var list3 = Arrays.asList(18, 25, 9, 4, 6, 6, 8, 10, 12, 22, 32, 14, 1, 2, 2, 1, 1);
        return Stream.of(
                Arguments.arguments(list1, true),
                Arguments.arguments(list2, false),
                Arguments.arguments(list3, true)
        );
    }
}