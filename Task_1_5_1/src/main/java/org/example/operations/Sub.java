package org.example.operations;

/**
 * text.
 */
public class Sub extends Operation {

    /**
     * вычитание.
     *
     * @param a - 1
     * @param b - 2
     */
    public Sub(Double a, Double b) {
        arg1 = a;
        arg2 = b;
    }

    @Override
    public Double evaluate() {
        return arg1 - arg2;
    }
}

