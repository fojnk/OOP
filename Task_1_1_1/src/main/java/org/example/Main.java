package org.example;

import org.example.Heapsort;

import java.util.Arrays;

public class Main {
    public static void main(String[] Args){
        int[] input_array = new int[] {5, 6, 7, 7, 8, 8, 2};
        int[] result_array = Heapsort.heapsort(input_array);
        System.out.println(Arrays.toString(input_array));
        System.out.println(Arrays.toString(result_array));
    }
}