package org.example.Operations;

public class Cos extends Operation {
    public Cos(Double a) {
        arg1 = a;
    }

    @Override
    public Double evaluate() {
        return Math.cos(arg1);
    }
}