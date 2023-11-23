package org.example.Operations;

import org.example.Operations.Operation;

public class Sqrt extends Operation {
    public Sqrt(Double a) {
        arg1 = a;
    }


    @Override
    public Double evaluate() {
        return Math.sqrt(arg1);
    }
}