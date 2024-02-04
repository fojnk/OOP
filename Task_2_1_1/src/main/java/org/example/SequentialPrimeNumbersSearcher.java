package org.example;

import java.util.List;

public class SequentialPrimeNumbersSearcher {
    public static boolean checkList(List<Integer> numbers) {
        return numbers.stream().anyMatch(PrimeNumbersChecker::IsPrime);
    }
}