package org.example;

import java.util.Arrays;

/**
 * Главный класс.
 */
public class Main {
    /**
     * Главный метод.
     *
     * @param args - аргументы коммадной строки
     * @throws InterruptedException - ошибка выполнения потока
     */
    public static void main(String[] args) throws InterruptedException {
        var lists = Arrays.asList(4, 4, 4, 4, 4, 4, 4, 4, 4, 1);
        var sc1 = new SequentialPrimeNumbersSearcher();
        var sc2 = new ParallelsPrimeNumbersSearcher();
        var sc3 = new MultiThreadPrimeNumbersSearcher();
        System.out.print("Sequential Numbers Checker: ");
        long start1 = System.currentTimeMillis();
        var res1 = sc1.checkList(lists);
        long end1 = System.currentTimeMillis();
        System.out.println(res1 + " time: " + (end1 - start1) + "ms");
        System.out.println("----------------------------------------");
        for (int i = 1; i < 10; i++) {
            System.out.print("MultiThread Numbers Checker: ");
            long start2 = System.currentTimeMillis();
            sc3.setAmountOfThreads(i);
            var res2 = sc3.checkList(lists);
            long end2 = System.currentTimeMillis();
            System.out.println(res2 + " Threads: " + i + " time: " + (end2 - start2) + "ms");
        }
        System.out.println("----------------------------------------");

        System.out.print("ParallelStream Numbers Checker: ");
        long start3 = System.currentTimeMillis();
        var res3 = sc2.checkList(lists);
        long end3 = System.currentTimeMillis();
        System.out.println(res3 + " time: " + (end3 - start3) + "ms");
    }
}