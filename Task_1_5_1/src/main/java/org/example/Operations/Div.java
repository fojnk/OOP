package org.example.Operations;

/**
 * text.
 */
public class Div extends Operation {
    /**
     * деление.
     *
     * @param a - 1
     * @param b - 2
     */
    public Div(Double a, Double b) {
        arg1 = a;
        arg2 = b;
    }

    @Override
    public Double evaluate() {
        return arg1 / arg2;
    }
}
