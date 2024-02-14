package org.example.primes;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс для перебора массива с заданным количеством потоков.
 */
public class MultiThreadPrimeNumbersSearcher extends PrimeNumbersChecker {
    private final AtomicInteger amount = new AtomicInteger();
    private int amountOfThreads;

    /**
     * Подкласс для реализации многопоточности.
     */
    public class MyThread extends Thread {
        List<Integer> numbers;

        /**
         * Инициализация класса.
         *
         * @param numbers - массив чисел
         */
        public MyThread(List<Integer> numbers) {
            this.numbers = numbers;
        }

        /**
         * запуск программы.
         */
        @Override
        public void run() {
            if (this.numbers.stream().anyMatch(PrimeNumbersChecker::IsPrime)) {
                amount.incrementAndGet();
            }
        }
    }


    /**
     * Инициализация главного класса.
     */
    public MultiThreadPrimeNumbersSearcher() {
        this.amount.set(0);
        this.amountOfThreads = 1;
    }

    /**
     * получение текущего количества потоков.
     *
     * @return - количество потоков
     */
    public int getAmountOfThreads() {
        return this.amountOfThreads;
    }

    /**
     * задание количества потоков.
     *
     * @param amount - новое количество потоков (натуральное число)
     */
    public void setAmountOfThreads(int amount) {
        if (amount <= 0) {
            return;
        }
        this.amountOfThreads = amount;
    }

    /**
     * Метод для проверки чисел в списке.
     *
     * @param numbers - числа
     * @return - true or false
     * @throws InterruptedException - ошибка в одном из потоков
     */
    public boolean checkList(List<Integer> numbers) throws InterruptedException {
        this.amount.set(0);
        int part = numbers.size() / amountOfThreads;
        MyThread[] threads = new MyThread[amountOfThreads];
        int index = 0;
        for (int i = 0; i < amountOfThreads - 1; i++) {
            threads[index] = new MyThread(numbers.subList(i * part, (i + 1) * part));
            threads[index++].start();
        }
        threads[index] = new MyThread(numbers.subList((amountOfThreads - 1) * part, numbers.size()));
        threads[index].start();

        for (int i = 0; i < amountOfThreads; i++) {
            threads[i].join();
        }

        return amount.get() > 0;
    }
}