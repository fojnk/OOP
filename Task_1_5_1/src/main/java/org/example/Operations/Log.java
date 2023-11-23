package org.example.Operations;

import org.example.Operations.Operation;

public class Log extends Operation {

    public Log(Double a) {
        arg1 = a;
    }
    @Override
    public Double evaluate() {
        return Math.log(arg1);
    }
}