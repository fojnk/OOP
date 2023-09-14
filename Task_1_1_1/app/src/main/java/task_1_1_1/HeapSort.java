package task_1_1_1;

public class HeapSort {

    public static void siftDown(int number_array[], int root, int len){
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

    public static void heapsort(int number_array[]){
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
    }

    public static void main(String[] args){
        int number_array[] = {1, 2, 3, 45, 5, 1, 2, 3, 4, 5};
        HeapSort ob = new HeapSort();
        ob.heapsort(number_array);
        printArray(number_array); 
    }

    public static void printArray(int number_array[]){
        int n = number_array.length;
        for (int i = 0; i < n; i ++){
            System.out.print(number_array[i] + " ");
        }
    }
}
