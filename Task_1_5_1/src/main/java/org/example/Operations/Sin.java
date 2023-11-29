package org.example.Operations;

public class Sin extends Operation {

    public Sin(Double a) {
        arg1 = a;
    }

    @Override
    public Double evaluate() {
        return Math.sin(arg1);
    }
}