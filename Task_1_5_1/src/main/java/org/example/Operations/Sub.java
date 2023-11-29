package org.example.Operations;

public class Sub extends Operation {

    public Sub(Double a, Double b) {
        arg1 = a;
        arg2 = b;
    }

    @Override
    public Double evaluate() {
        return arg1 - arg2;
    }
}

