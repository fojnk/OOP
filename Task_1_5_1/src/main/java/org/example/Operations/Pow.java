package org.example.Operations;

public class Pow extends Operation {

    public Pow(Double a, Double b) {
        arg1 = a;
        arg2 = b;
    }

    @Override
    public Double evaluate() {
        return Math.pow(arg1, arg2);
    }
}