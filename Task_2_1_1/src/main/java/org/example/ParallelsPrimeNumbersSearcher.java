package org.example;

import java.util.List;

public class ParallelsPrimeNumbersSearcher{

    public static boolean checkList(List<Integer> numbers) {
        return numbers.parallelStream().anyMatch(PrimeNumbersChecker::IsPrime);
    }

}