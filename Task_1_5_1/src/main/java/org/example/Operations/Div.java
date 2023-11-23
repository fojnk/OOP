package org.example.Operations;

public class Div extends Operation {
    public Div(Double a, Double b) {
        arg1 = a;
        arg2 = b;
    }

    @Override
    public Double evaluate() {
        return arg1 / arg2;
    }
}
