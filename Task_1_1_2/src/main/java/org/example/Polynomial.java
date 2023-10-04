package org.example;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

/** Polynomial example: if you put array (new int[] {1, 2, 3, 4, 5}) ->
    * -> you get polynom 5x^4 + 4x^3 + 3x^2 + 2x^1 + 1
 */
public class Polynomial {

    enum Operation {
        plus,
        minus
    }
    private final int[] cofArray;
    private final int size;

    /**
     * конструктор, который создает полином и удаляет лишние нули
     */
    public Polynomial(int[] cofArray) {
        var newArr = fix_input_arr(cofArray.length, cofArray.clone());
        var n = newArr.length;
        if (n == 0) {
            this.cofArray = new int[] {0};
            this.size = 1;
        }
        else {
            this.size = n;
            this.cofArray = newArr;
        }
    }

    /**
     *метод, предназначенный для доступа к содержимому приватных переменных
     *гетер передает копию массива
     */
    public int[] getCofArray() {
        return this.cofArray.clone();
    }

    /**
     * метод, предназначенный для доступа к содержимому приватных переменных
     * гетер достает размер массива с коэффициентами
     */
    public int getSize() {
        return this.size;
    }

    /**
     * функция, удаляющая лишние нули из массива с коэффициентами
     */
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

    /**
     * функция которая выполняет сложение или вычитание полиномов
     * в зависимовсти от передаваемой операции
     */
    private Polynomial exec_operation(Polynomial p, Operation op) {
        int[] p2CofArray = p.getCofArray();
        int n = p.getSize();
        int mainLen = this.size;

        int max_len = Math.max(mainLen, n);
        int[] resultArray = new int[max_len];

        for (int i = 0; i < max_len; i++) {
            if (i >= n) {
                resultArray[i] = this.cofArray[i];
            } else if (i >= mainLen) {
                if (op == Operation.minus){
                    resultArray[i] -= p2CofArray[i];
                }
                else {
                    resultArray[i] = p2CofArray[i];
                }
            } else {
                if (op == Operation.plus) {
                    resultArray[i] = p2CofArray[i] + this.cofArray[i];
                } else {
                    resultArray[i] = this.cofArray[i] - p2CofArray[i];
                }
            }
        }

        return new Polynomial(resultArray);
    }

    /**
     * сложение полиномов
     */
    public Polynomial plus(Polynomial p) {
        return exec_operation(p, Operation.plus);
    }

    /**
     * вычитание полиномов
     */
    public Polynomial minus(Polynomial p) {
        return exec_operation(p, Operation.minus);
    }

    /**
     * умножение полиномов
     */
    public Polynomial times(Polynomial p2) {
        int len1 = this.size;
        int len2 = p2.getSize();
        int[] p2_cofArray = p2.getCofArray();

        int[] resultArray = new int[len1 + len2];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                resultArray[i + j] = resultArray[i + j] + this.cofArray[i] * p2_cofArray[j];
            }
        }

        return new Polynomial(resultArray);
    }

    /**
     * вычисление значения полинома при заданном х
     */
    public int evaluate(int x) {
        int result = 0;
        for (int i = 0; i < this.size; i++) {
            result += this.cofArray[i] * (int) pow(x, i);
        }
        return result;
    }

    /**
     * получение производной
     */
    public Polynomial differentiate(int n) {
        if (this.size <= n) return new Polynomial(new int[] {});
        int[] resultArray = new int[this.size - n];

        for (int i = 0; i < this.size - n; i++) {
            int newCof = this.cofArray[i + n];
            for (int j = 0; j < n; j ++){
                newCof *= i + n - j;
            }
            resultArray[i] = newCof;
        }

        return new Polynomial(resultArray);
    }

    /**
     * переписанный метод toString
     * создает строку из полинома
     */
    @Override
    public String toString() {
        var result = new StringBuilder();

        if (this.size == 1 && this.cofArray[0] == 0) return "0";

        boolean firstWord = true;
        for (int i = this.size - 1; i >= 0; i--) {
            if (this.cofArray[i] != 0) {
                if (!firstWord) {
                    if (this.cofArray[i] < 0) {
                        result.append(" - ");
                    } else {
                        result.append(" + ");
                    }
                } else {
                    if (this.cofArray[i] < 0) {
                        result.append("- ");
                    }
                    firstWord = false;
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

    /**
     * метод сравнения полиномов
     */
    public boolean equals(Polynomial p) {
        boolean result = true;
        int len1 = p.getSize();
        int[] pCofArray = p.getCofArray();

        if (len1 != this.size) result = false;
        for (int i = 0; i < len1; i++) {
            if (pCofArray[i] != this.cofArray[i]) {
                result = false;
                break;
            }
        }

        return result;
    }
}