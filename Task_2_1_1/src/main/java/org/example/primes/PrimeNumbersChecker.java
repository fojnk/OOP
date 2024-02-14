package org.example.primes;

import java.util.List;

/**
 * Абстрактный класс для наследования от него.
 */
abstract public class PrimeNumbersChecker {
    /**
     * Реализация метода, который проверяет, является ли число простым.
     *
     * @param number - число
     * @return - true or false
     */
    public static boolean IsPrime(int number) {
        var i = 2;
        while (i * i <= number) {
            if (number % i == 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     * Абстрактный метод проверки списка.
     *
     * @param numbers - числа
     * @return - true or false
     * @throws InterruptedException - ошибка многопоточной программы
     */
    abstract public boolean checkList(List<Integer> numbers) throws InterruptedException;
}