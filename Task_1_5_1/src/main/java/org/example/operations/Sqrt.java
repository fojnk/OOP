package org.example.operations;

/**
 * text.
 */
public class Sqrt extends Operation {
    /**
     * корень.
     *
     * @param a - 1
     */
    public Sqrt(Double a) {
        arg1 = a;
    }

    @Override
    public Double evaluate() {
        return Math.sqrt(arg1);
    }
}