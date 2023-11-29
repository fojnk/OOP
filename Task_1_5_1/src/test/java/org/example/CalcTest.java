package org.example;

import org.example.operations.*;
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
        assertEquals(a + b, add.evaluate());
        var sub = new Sub(a, b);
        assertEquals(a - b, sub.evaluate());
        var mul = new Mul(a, b);
        assertEquals(a * b, mul.evaluate());
        var div = new Div(a, b);
        assertEquals(a / b, div.evaluate());
        var pow = new Pow(a, b);
        assertEquals(Math.pow(a, b), pow.evaluate());
        var cos = new Cos(a);
        assertEquals(Math.cos(a), cos.evaluate());
        var sin = new Sin(a);
        assertEquals(Math.sin(a), sin.evaluate());
        var log = new Log(a);
        assertEquals(Math.log(a), log.evaluate());
        var sqrt = new Sqrt(a);
        assertEquals(Math.sqrt(a), sqrt.evaluate());
    }

    /**
     * тестирование работы калькулятора.
     */
    @Test
    public void calcTest() {
        var calc = new Calculator();
        calc = new Calculator("sin + - 1 2 1");
        assertEquals(0.0, calc.evaluateExp());
        calc = new Calculator("sin");
        assertNull(calc.evaluateExp());
        calc = new Calculator("+ 1");
        assertNull(calc.evaluateExp());
        calc = new Calculator("dsjaklfjdskjfl");
        assertNull(calc.evaluateExp());
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