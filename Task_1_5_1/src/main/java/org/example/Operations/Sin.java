package org.example.Operations;

/**
 * text.
 */
public class Sin extends Operation {

    /**
     * синус.
     *
     * @param a - 1
     */
    public Sin(Double a) {
        arg1 = a;
    }

    @Override
    public Double evaluate() {
        return Math.sin(arg1);
    }
}