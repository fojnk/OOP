package org.example.operations;

/**
 * text.
 */
public class Cos extends Operation {

    /**
     * косинус.
     *
     * @param a - 1
     */
    public Cos(Double a) {
        arg1 = a;
    }

    @Override
    public Double evaluate() {
        return Math.cos(arg1);
    }
}