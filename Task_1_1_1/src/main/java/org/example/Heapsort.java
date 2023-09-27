package org.example;

import java.util.Arrays;

public class Heapsort {

    private static void siftDown(int[] numberArray, int root, int len) {
        int maxElem = root;
        int leftChild = root * 2 + 1;
        int rightChild = root * 2 + 2;

        if (leftChild < len && numberArray[leftChild] > numberArray[root]) {
            maxElem = leftChild;
        }
        if (rightChild < len && numberArray[rightChild] > numberArray[maxElem]) {
            maxElem = rightChild;
        }

        if (maxElem != root) {
            int tmp = numberArray[root];
            numberArray[root] = numberArray[maxElem];
            numberArray[maxElem] = tmp;
            siftDown(numberArray, maxElem, len);
        }
    }

    public static int[] heapsort(int[] inputArray) {
        int[] numberArray = inputArray.clone();
        int n = numberArray.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(numberArray, i, n);
        }

        for (int i = n - 1; i >= 0; i--) {
            int tmp = numberArray[i];
            numberArray[i] = numberArray[0];
            numberArray[0] = tmp;
            siftDown(numberArray, 0, i);
        }
        return numberArray;
    }
}