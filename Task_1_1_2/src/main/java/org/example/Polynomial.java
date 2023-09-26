package org.example;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

enum Operation {
    plus,
    minus
}
// Polynomial example: if you put array (new int[] {1, 2, 3, 4, 5}) ->
// -> you get polynom 5x^4 + 4x^3 + 3x^2 + 2x^1 + 1
public class Polynomial {
    private final int[] cofArray;
    private final int size;
    public Polynomial(int[] cofArray) {
        this.size = cofArray.length;
        this.cofArray = cofArray;
    }

    public int[] getCofArray() { return this.cofArray; }

    public int getSize() { return this.size; }

    private Polynomial check_resultArray_and_return_correct_Polynomial(int len, int[] resultArray) {
        int k = 0;
        while (k < len && resultArray[len - 1 - k] == 0) {
            k++;
        }
        Polynomial new_p;
        if (k != 0) {
            int[] array = new int[len - k];
            System.arraycopy(resultArray, 0, array, 0, len - k);
            new_p = new Polynomial(array);
        }
        else {
            new_p = new Polynomial(resultArray);
        }
        return new_p;
    }

    private Polynomial exec_operation(Polynomial p, Operation op) {
        int[] p2_cofArray = p.getCofArray();
        int n = p.getSize();
        int main_len = this.size;

        int max_len = Math.max(main_len, n);
        int[] resultArray = new int[max_len];

        for (int i = 0; i < max_len; i ++) {
            if (i >= n) { resultArray[i] = this.cofArray[i]; }
            else if (main_len <= i) { resultArray[i] = p2_cofArray[i]; }
            else {
                if (op == Operation.plus) {
                    resultArray[i] = p2_cofArray[i] + this.cofArray[i];
                }
                else {
                    resultArray[i] = this.cofArray[i] - p2_cofArray[i];
                }
            }
        }

        return check_resultArray_and_return_correct_Polynomial(max_len, resultArray);
    }

    public Polynomial plus(Polynomial p) { return exec_operation(p, Operation.plus); }

    public Polynomial minus(Polynomial p) { return exec_operation(p, Operation.minus); }

    public Polynomial times(Polynomial p2) {
        int len_1 = this.size;
        int len_2 = p2.getSize();
        int[] p2_cofArray = p2.getCofArray();

        int[] resultArray = new int[len_1 + len_2];
        for (int i = 0; i < len_1; i ++) {
            for (int j = 0; j < len_2; j ++) {
                resultArray[i + j] =  resultArray[i + j] + this.cofArray[i] * p2_cofArray[j];
            }
        }

        return check_resultArray_and_return_correct_Polynomial(len_2 + len_1, resultArray);
    }

    public int evaluate(int x) {
        int result = 0;
        for (int i = 0; i < this.size; i ++) {
            result += this.cofArray[i] * (int)pow(x, i);
        }
        return result;
    }

    public Polynomial differentiate(int n) {
        if (this.size <= n) return new Polynomial(new int[] {});
        int[] resultArray = new int[this.size - n];

        for (int i = 0; i < this.size - n; i ++){
            resultArray[i] = this.cofArray[i + 1] * (i + 1);
        }

        return check_resultArray_and_return_correct_Polynomial(this.size - n,  resultArray);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        if (this.size == 0) return result.toString();

        boolean first_word = true;
        for (int i = this.size - 1; i > 0; i --) {
            if (this.cofArray[i] != 0) {
                if (!first_word)  {
                    if (this.cofArray[i] < 0) {
                        result.append(" - ");
                    }
                    else {
                        result.append(" + ");
                    }
                }
                else {
                    if (this.cofArray[i] < 0) {
                        result.append("- ");
                    }
                    first_word = false;
                }
                if (i != 1){ result.append(abs(this.cofArray[i])).append("x^").append(i); }
                else { result.append(abs(this.cofArray[i])).append("x"); }
            }
        }
        if (this.cofArray[0] != 0) result.append(" + ").append(this.cofArray[0]);
        return result.toString();
    }

    public boolean equals(Polynomial p) {
        boolean result = true;
        int len_1 = p.getSize();
        int[] p_cofArray = p.getCofArray();

        if (len_1 != this.size) result = false;
        for (int i = 0; i < len_1; i ++) {
            if (p_cofArray[i] != this.cofArray[i]) {
                result = false;
                break;
            }
        }

        return result;
    }
}