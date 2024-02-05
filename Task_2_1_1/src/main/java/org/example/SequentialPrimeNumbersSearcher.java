package org.example;

import java.util.List;

/**
 * Реализация простого последовательного перебора.
 */
public class SequentialPrimeNumbersSearcher extends PrimeNumbersChecker {
    /**
     * метод для проверки списка.
     *
     * @param numbers - числа
     * @return - true or false
     */
    public boolean checkList(List<Integer> numbers) {
        return numbers.stream().anyMatch(this::IsPrime);
    }
}