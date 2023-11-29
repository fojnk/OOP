package org.example.Operations;

/**
 * text.
 */
public class Add extends Operation {

    /**
     * сложение.
     *
     * @param a - 1
     * @param b - 2
     */
    public Add(Double a, Double b) {
        arg1 = a;
        arg2 = b;
    }

    @Override
    public Double evaluate() {
        return arg1 + arg2;
    }
}

