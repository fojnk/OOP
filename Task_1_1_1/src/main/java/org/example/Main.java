package org.example;


public class Main {

    private static void siftDown(int[] number_array, int root, int len){
        int maxElem = root;
        int leftChild = root * 2 + 1;
        int rightChild = root * 2 + 2;

        if (leftChild < len && number_array[leftChild] > number_array[root]){
            maxElem = leftChild;
        }
        if (rightChild < len && number_array[rightChild] > number_array[maxElem]){
            maxElem = rightChild;
        }

        if (maxElem != root) {
            int tmp = number_array[root];
            number_array[root] = number_array[maxElem];
            number_array[maxElem] = tmp;
            siftDown(number_array, maxElem, len);
        }
    }

    public static int[] heapsort(int[] number_array){
        int n = number_array.length;

        for (int i = n/2 - 1; i >= 0; i --) {
            siftDown(number_array, i, n);
        }

        for (int i = n - 1; i >=0 ; i --) {
            int tmp = number_array[i];
            number_array[i] = number_array[0];
            number_array[0] = tmp;
            siftDown(number_array, 0, i);
        }
        return number_array;
    }

    public static void main(String[] args){}

}
