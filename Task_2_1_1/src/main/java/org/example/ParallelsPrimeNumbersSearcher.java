package org.example;

import java.util.List;

/**
 * Реализация с использованием ParallelStream.
 */
public class ParallelsPrimeNumbersSearcher extends PrimeNumbersChecker {

    /**
     * Проверка списка на наличие простых чисел.
     *
     * @param numbers - список чисел
     * @return - true or false
     */
    public boolean checkList(List<Integer> numbers) {
        return numbers.parallelStream().anyMatch(this::IsPrime);
    }

}