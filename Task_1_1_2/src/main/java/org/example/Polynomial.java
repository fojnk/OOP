package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Math.pow;
import static sun.security.krb5.Confounder.intValue;
import static sun.swing.MenuItemLayoutHelper.max;

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
        return cofArray;
    }

    public void set_value(int index, int value){
        this.cofArray[index] = value;
    }

    public Polynomial plus(Polynomial p) {
        int[] p2_cofArray = p.getCofArray();
        int n = p2_cofArray.length;
        int main_len = this.cofArray.length;

        int max_len = max(main_len, n);
        Polynomial new_p = new Polynomial(max_len);

        for (int i = 0; i < max_len; i ++){
            if (i >= n) {
                new_p.set_value(i, this.cofArray[i]);
            }
            else if (main_len <= i) {
                new_p.set_value(i, p2_cofArray[i]);
            }
            else {
                new_p.set_value(i, p2_cofArray[i] + this.cofArray[i]);
            }
        }

        return new_p;
    }

    public Polynomial minus(Polynomial p){
        int[] p2_cofArray = p.getCofArray();
        int n = p2_cofArray.length;
        int main_len = this.cofArray.length;

        int max_len = max(main_len, n);
        Polynomial new_p = new Polynomial(max_len);

        for (int i = 0; i < max_len; i ++){
            if (i >= n) {
                new_p.set_value(i, this.cofArray[i]);
            }
            else if (main_len <= i) {
                new_p.set_value(i, p2_cofArray[i]);
            }
            else {
                new_p.set_value(i, p2_cofArray[i] - this.cofArray[i]);
            }
        }

        return new_p;
    }

    public void times(){

    }

    public int evaluate(int x) {
        int result = 0;
        for (int i = 0; i < this.size; i ++){
            result += this.cofArray[i] * intValue(pow(x, i));
        }
    }
    public Polynomial differentiate(int n){
        Polynomial new_p = new Polynomial(this.size - n);
        for (int i = 0; i < this.size - n; i ++){
            new_p.set_value(i, this.cofArray[i + n]);
        }
        return new_p;
    }

    public String toString(){

    }

    public int equals() {

    }
}