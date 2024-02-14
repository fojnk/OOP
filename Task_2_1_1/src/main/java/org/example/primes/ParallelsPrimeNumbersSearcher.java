package org.example.primes;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * Реализация с использованием ParallelStream.
 */
public class ParallelsPrimeNumbersSearcher extends PrimeNumbersChecker {

    /**
     * Проверка списка на наличие простых чисел.
     *
     * @param numbers      - числа
     * @param threadNumber - ограничение по потокам
     * @return - true or fasle
     * @throws ExecutionException   - ошибка выполнения
     * @throws InterruptedException - ошибка в одном из потоков
     */
    public boolean checkList(List<Integer> numbers, Integer threadNumber) throws ExecutionException,
            InterruptedException {
        ForkJoinPool customThreadPool = new ForkJoinPool(threadNumber);
        var result = customThreadPool.submit(() ->
                numbers.parallelStream().anyMatch(PrimeNumbersChecker::IsPrime)).get();
        customThreadPool.shutdown();
        return result;
    }

    /**
     * Проверка списка на наличие простых чисел.
     *
     * @param numbers - список чисел
     * @return - true or false
     */
    public boolean checkList(List<Integer> numbers) {
        return numbers.parallelStream().anyMatch(PrimeNumbersChecker::IsPrime);
    }

}