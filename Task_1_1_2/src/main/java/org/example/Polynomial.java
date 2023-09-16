package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Math.pow;

public class Polynomial {

    private int[] cofArray;
    private int size;
    public Polynomial(int[] cofArray) {
        this.size = cofArray.length;
        this.cofArray = cofArray;
    }

    public Polynomial(int size){
        this.size = size;
        this.cofArray = new int[size];
    }

    public int[] getCofArray(){
        return this.cofArray;
    }

    public int getSize(){
        return this.size;
    }

    public void set_value(int index, int value){
        this.cofArray[index] = value;
    }

    public int get_value(int index){
        return this.cofArray[index];
    }

    private int mymax(int num1, int num2) {
        int result = num1;
        if (num1 < num2) result = num2;
        return result;
    }

    private Polynomial exec_operation(Polynomial p, byte op){
        int[] p2_cofArray = p.getCofArray();
        int n = p.getSize();
        int main_len = this.size;

        int max_len = mymax(main_len, n);
        Polynomial new_p = new Polynomial(max_len);

        for (int i = 0; i < max_len; i ++){
            if (i >= n) { new_p.set_value(i, this.cofArray[i]); }
            else if (main_len <= i) { new_p.set_value(i, p2_cofArray[i]);}
            else {
                if (op == '+') {
                    new_p.set_value(i, p2_cofArray[i] + this.cofArray[i]);
                }
                else {
                    new_p.set_value(i, p2_cofArray[i] - this.cofArray[i]);
                }
            }
        }

        return new_p;
    }

    public Polynomial plus(Polynomial p) {
        return exec_operation(p, (byte) '+');
    }

    public Polynomial minus(Polynomial p){
        return exec_operation(p, (byte) '-');
    }

    public Polynomial times(Polynomial p2){
        int len_1 = this.size;
        int len_2 = p2.getSize();
        int[] p2_cofArray = p2.getCofArray();

        Polynomial new_p = new Polynomial(len_1 + len_2);
        for (int i = 0; i < len_1; i ++) {
            for (int j = 0; j < len_2; j ++) {
                new_p.set_value(i + j, new_p.get_value(i + j) + this.cofArray[i] * p2_cofArray[j]);
            }
        }
        return new_p;
    }

    public int evaluate(int x) {
        int result = 0;
        for (int i = 0; i < this.size; i ++){
            result += this.cofArray[i] * pow(x, i);
        }
        return result;
    }
    public Polynomial differentiate(int n){
        if (this.size <= n) return new Polynomial(0);
        Polynomial new_p = new Polynomial(this.size - n);
        for (int i = 0; i < this.size - n; i ++){
            new_p.set_value(i, this.cofArray[i + n]);
        }
        return new_p;
    }

    public String toString(){
        String result = "";

        if (this.size == 0) return result;

        boolean first_word = true;
        for (int i = this.size - 1; i > 0; i --) {
            if (this.cofArray[i] != 0){
                if (!first_word)  {result += " + ";}
                else {first_word = false;}
                result += this.cofArray[i] + "x^" + i;
            }
        }
        if (this.cofArray[0] != 0) result += " + " + this.cofArray[0];
        return result;
    }

    public boolean equals(Polynomial p) {
        boolean result = true;
        int len_1 = p.getSize();
        int len_2 = this.size;
        int[] p_cofArray = p.getCofArray();

        if (len_1 != len_2) result = false;
        for (int i = 0; i < len_1; i ++) {
            if (p_cofArray[i] != this.cofArray[i]) {
                result = false;
                break;
            }
        }

        return result;
    }

    public static void main (String[] args) {
        Polynomial p1 = new Polynomial(new int[] {4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[] {3, 2, 8});
        System.out.println(p1.plus(p2.differentiate(1)).toString());
        System.out.println(p1.times(p2).evaluate(2));
    }
}