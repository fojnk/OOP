package org.example.operations;

/**
 * text.
 */
public class Log extends Operation {

    /**
     * логарифм.
     *
     * @param a - 1
     */
    public Log(Double a) {
        arg1 = a;
    }

    @Override
    public Double evaluate() {
        return Math.log(arg1);
    }
}