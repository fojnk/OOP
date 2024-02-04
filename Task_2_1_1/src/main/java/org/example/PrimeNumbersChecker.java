package org.example;

public class PrimeNumbersChecker {
    public static boolean IsPrime(int number) {
        var i = 2;
        while(i * i <= number) {
            if (number % 2 == 0) {
                return false;
            }
            i ++;
        }
        return true;
    }
}