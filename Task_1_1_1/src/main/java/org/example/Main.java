package org.example;

import org.example.Heapsort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] inputArray = new int[] {2, 3, 1, 5, 3, 4, 1};
        int[] resultArray = Heapsort.heapsort(inputArray);
        System.out.println(Arrays.toString(inputArray));
        System.out.println(Arrays.toString(resultArray));
    }
}