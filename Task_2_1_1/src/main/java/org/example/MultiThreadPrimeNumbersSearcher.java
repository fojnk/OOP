package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadPrimeNumbersSearcher {
    private final AtomicInteger amount = new AtomicInteger();
    public class MyThread extends Thread {
        List<Integer> numbers;

        public MyThread(List<Integer> numbers) {
            this.numbers = numbers;
        }

        @Override
        public void run() {
            if (this.numbers.stream().anyMatch(PrimeNumbersChecker::IsPrime)) {
                amount.incrementAndGet();
            }
        }
    }

    public MultiThreadPrimeNumbersSearcher() {this.amount.set(0);}

    public boolean checkList(List<Integer> numbers, Integer amountOfThreads) throws InterruptedException {
        int part = numbers.size() / amountOfThreads;
        MyThread[] threads = new MyThread[amountOfThreads];
        int index = 0;
        for (int i = 0; i < amountOfThreads - 1; i ++) {
            threads[index] = new MyThread(numbers.subList(i * part, (i + 1) * part));
            threads[index++].start();
        }
        threads[index] = new MyThread(numbers.subList((amountOfThreads -1) * part, numbers.size()));
        threads[index].start();

        for (int i = 0; i < amountOfThreads; i ++) {
            threads[i].join();
        }

        return amountOfThreads == amount.get();
    }
}