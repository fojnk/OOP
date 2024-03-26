package org.example.client_server;

/**
 * класс для проверки числа на простоту.
 */
public class IsPrime {
    /**
     * метод для проверки числа на простоту.
     *
     * @param number - число
     * @return - true or false
     */
    public static boolean isPrime(int number) {
        var i = 2;
        while (i * i <= number) {
            if (number % i == 0) {
                return false;
            }
            i++;
        }
        return true;
    }
}