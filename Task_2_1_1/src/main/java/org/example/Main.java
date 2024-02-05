package org.example;

import java.util.Arrays;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        var lists = Arrays.asList(4, 4, 4, 4, 4, 4, 4, 4, 4, 1);
        var k = new MultiThreadPrimeNumbersSearcher();
        System.out.print("Sequential Numbers Checker: ");
        long start1 = System.currentTimeMillis();
        var res1 = SequentialPrimeNumbersSearcher.checkList(lists);
        long end1 = System.currentTimeMillis();
        System.out.println(res1 + " time: " + (end1 - start1) + "ms");
        System.out.println("----------------------------------------");
        for (int i = 1; i < 10; i ++) {
            System.out.print("MultiThread Numbers Checker: ");
            long start2 = System.currentTimeMillis();
            var res2 = k.checkList(lists, i);
            long end2 = System.currentTimeMillis();
            System.out.println(res2 + " Threads: " + i + " time: " + (end2 - start2) + "ms");
        }
        System.out.println("----------------------------------------");

        System.out.print("ParallelStream Numbers Checker: ");
        long start3 = System.currentTimeMillis();
        var res3 = ParallelsPrimeNumbersSearcher.checkList(lists);
        long end3 = System.currentTimeMillis();
        System.out.println(res3 + " time: " + (end3 - start3) + "ms");
    }
}