package org.example;

import org.example.Calculator;
import org.example.Operations.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalcTest {
    @Test
    public void basicOperationsTests(){
        Double a = 1.0;
        Double b = 2.0;
        var add = new Add(a, b);
        var sub = new Sub(a, b);
        var mul = new Mul(a, b);
        var div = new Div(a, b);
        var pow = new Pow(a, b);
        var cos = new Cos(a);
        var sin = new Sin(a);
        var log = new Log(a);
        var sqrt = new Sqrt(a);
        assertEquals(a + b, add.evaluate());
        assertEquals(a - b, sub.evaluate());
        assertEquals(a * b, mul.evaluate());
        assertEquals(a / b, div.evaluate());
        assertEquals(Math.pow(a, b), pow.evaluate());
        assertEquals(Math.cos(a), cos.evaluate());
        assertEquals(Math.sin(a), sin.evaluate());
        assertEquals(Math.log(a), log.evaluate());
        assertEquals(Math.sqrt(a), sqrt.evaluate());
    }

    @Test
    public void calcTest() {
        var calc = new Calculator("sin + - 1 2 1");
        assertEquals(0.0, calc.evaluateExp());
    }
}