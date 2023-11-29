package org.example;

import org.example.Operations.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * класс для тестирования калькулятора.
 */
public class CalcTest {
    /**
     * тестирование операций.
     */
    @Test
    public void basicOperationsTests() {
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

    /**
     * тестирование работы калькулятора.
     */
    @Test
    public void calcTest() {
        var calc = new Calculator("sin + - 1 2 1");
        assertEquals(0.0, calc.evaluateExp());
    }

    /**
     * тесты для OperationHandler.
     */
    @Test
    public void operationHandlerTest() {
        assertTrue(OperationsHandler.argIsBinaryOp("+"));
        assertTrue(OperationsHandler.argIsBinaryOp("-"));
        assertTrue(OperationsHandler.argIsBinaryOp("*"));
        assertTrue(OperationsHandler.argIsBinaryOp("/"));
        assertTrue(OperationsHandler.argIsBinaryOp("pow"));
        assertTrue(OperationsHandler.argIsUnaryOp("sin"));
        assertTrue(OperationsHandler.argIsUnaryOp("cos"));
        assertTrue(OperationsHandler.argIsUnaryOp("log"));
        assertTrue(OperationsHandler.argIsUnaryOp("sqrt"));
        assertEquals(OperationsHandler.getUnaryOperation("sin", 0.0).evaluate(), 0.0);
        assertEquals(OperationsHandler.getUnaryOperation("cos", 0.0).evaluate(), 1.0);
        assertEquals(OperationsHandler.getUnaryOperation("sqrt", 0.0).evaluate(), 0.0);
        assertEquals(OperationsHandler.getBinaryOperation("+", 1.0, 2.0).evaluate(), 3.0);
        assertEquals(OperationsHandler.getBinaryOperation("-", 1.0, 2.0).evaluate(), -1.0);
        assertArrayEquals(OperationsHandler.getAllUnaryOperations(), new String[]{"sin", "cos", "sqrt", "log"});
        assertArrayEquals(OperationsHandler.getAllBinaryOperations(), new String[]{"+", "-", "*", "/", "pow"});
    }
}