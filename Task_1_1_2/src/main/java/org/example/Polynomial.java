package org.example;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

// Polynomial example: if you put array (new int[] {1, 2, 3, 4, 5}) ->
// -> you get polynom 5x^4 + 4x^3 + 3x^2 + 2x^1 + 1
public class Polynomial {

    enum Operation {
        plus,
        minus
    }
    private final int[] cofArray;
    private final int size;

    //конструктор, который
    //создает полином и удаляет лишние нули
    public Polynomial(int[] cofArray) {
        var new_arr = fix_input_arr(cofArray.length, cofArray.clone());
        var n = new_arr.length;
        if (n == 0) {
            this.cofArray = new int[] {0};
            this.size = 1;
        }
        else {
            this.size = n;
            this.cofArray = new_arr;
        }
    }

    //гетер передает копию массива
    public int[] getCofArray() {
        return this.cofArray.clone();
    }

    //гетер достает размер массива с коэффициентами
    public int getSize() {
        return this.size;
    }

    //функция, удаляющая лишние нули из массива с коэффициентами
    private int[] fix_input_arr(int len, int[] cofArray){
        int k = 0;
        while (k < len && cofArray[len - 1 - k] == 0) {
            k++;
        }
        if (k != 0) {
            int[] array = new int[len - k];
            System.arraycopy(cofArray, 0, array, 0, len - k);
            return array;
        } else {
            return cofArray;
        }
    }

    //функция которая выполняет сложение или вычитание полиномов
    // в зависимовсти от передаваемой операции
    private Polynomial exec_operation(Polynomial p, Operation op) {
        int[] p2_cofArray = p.getCofArray();
        int n = p.getSize();
        int main_len = this.size;

        int max_len = Math.max(main_len, n);
        int[] resultArray = new int[max_len];

        for (int i = 0; i < max_len; i++) {
            if (i >= n) {
                resultArray[i] = this.cofArray[i];
            } else if (i >= main_len) {
                if (op == Operation.minus){
                    resultArray[i] -= p2_cofArray[i];
                }
                else {
                    resultArray[i] = p2_cofArray[i];
                }
            } else {
                if (op == Operation.plus) {
                    resultArray[i] = p2_cofArray[i] + this.cofArray[i];
                } else {
                    resultArray[i] = this.cofArray[i] - p2_cofArray[i];
                }
            }
        }

        return new Polynomial(resultArray);
    }

    //сложение полиномов
    public Polynomial plus(Polynomial p) {
        return exec_operation(p, Operation.plus);
    }

    //вычитание полиномов
    public Polynomial minus(Polynomial p) {
        return exec_operation(p, Operation.minus);
    }

    //умножение полиномов
    public Polynomial times(Polynomial p2) {
        int len_1 = this.size;
        int len_2 = p2.getSize();
        int[] p2_cofArray = p2.getCofArray();

        int[] resultArray = new int[len_1 + len_2];
        for (int i = 0; i < len_1; i++) {
            for (int j = 0; j < len_2; j++) {
                resultArray[i + j] = resultArray[i + j] + this.cofArray[i] * p2_cofArray[j];
            }
        }

        return new Polynomial(resultArray);
    }

    //вычисление значения полинома при заданном х
    public int evaluate(int x) {
        int result = 0;
        for (int i = 0; i < this.size; i++) {
            result += this.cofArray[i] * (int) pow(x, i);
        }
        return result;
    }

    //вычисляет производную
    public Polynomial differentiate(int n) {
        if (this.size <= n) return new Polynomial(new int[] {});
        int[] resultArray = new int[this.size - n];

        for (int i = 0; i < this.size - n; i++) {
            int new_cof = this.cofArray[i + n];
            for (int j = 0; j < n; j ++){
                new_cof *= i + n - j;
            }
            resultArray[i] = new_cof;
        }

        return new Polynomial(resultArray);
    }

    //переписанный метод toString
    //создает строку из полинома
    @Override
    public String toString() {
        var result = new StringBuilder();

        if (this.size == 1 && this.cofArray[0] == 0) return "0";

        boolean first_word = true;
        for (int i = this.size - 1; i >= 0; i--) {
            if (this.cofArray[i] != 0) {
                if (!first_word) {
                    if (this.cofArray[i] < 0) {
                        result.append(" - ");
                    } else {
                        result.append(" + ");
                    }
                } else {
                    if (this.cofArray[i] < 0) {
                        result.append("- ");
                    }
                    first_word = false;
                }
                if (i > 1) {
                    result.append(abs(this.cofArray[i])).append("x^").append(i);
                } else if (i == 1){
                    result.append(abs(this.cofArray[i])).append("x");
                } else {
                    result.append(abs(this.cofArray[i]));
                }
            }
        }
        return result.toString();
    }

    //метод сравнения полиномов
    public boolean equals(Polynomial p) {
        boolean result = true;
        int len_1 = p.getSize();
        int[] p_cofArray = p.getCofArray();

        if (len_1 != this.size) result = false;
        for (int i = 0; i < len_1; i++) {
            if (p_cofArray[i] != this.cofArray[i]) {
                result = false;
                break;
            }
        }

        return result;
    }
}