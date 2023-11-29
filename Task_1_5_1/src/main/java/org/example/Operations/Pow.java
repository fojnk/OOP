package org.example.Operations;

/**
 * text.
 */
public class Pow extends Operation {

    /**
     * возведение в степень.
     *
     * @param a - 1
     * @param b - 2
     */
    public Pow(Double a, Double b) {
        arg1 = a;
        arg2 = b;
    }

    @Override
    public Double evaluate() {
        return Math.pow(arg1, arg2);
    }
}