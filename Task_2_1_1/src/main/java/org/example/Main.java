package org.example;

import java.util.Arrays;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        var k = new MultiThreadPrimeNumbersSearcher();
        System.out.println(k.checkList(Arrays.asList(7, 6, 8, 4), 1));
    }
}