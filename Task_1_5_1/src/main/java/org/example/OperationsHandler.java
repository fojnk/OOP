package org.example;

import org.example.Operations.*;
import org.example.Operations.Operation;

import java.util.Arrays;
import java.util.List;

public class OperationsHandler {
    private static final String[] unaryOps = new String[]{"sin", "cos", "sqrt", "log"};
    private static final String[] binaryOps = new String[]{"+", "-", "*", "/", "pow"};

    public static boolean argIsUnaryOp(String arg) {
        for (String unaryOp : unaryOps) {
            if (arg.equals(unaryOp)) {
                return true;
            }
        }
        return false;
    }
    public static boolean argIsBinaryOp(String arg) {
        for (String binaryOp : binaryOps) {
            if (arg.equals(binaryOp)) {
                return true;
            }
        }
        return false;
    }

    public static String[] getAllUnaryOperations(){
        return unaryOps.clone();
    }

    public static String[] getAllBinaryOperations(){
        return binaryOps.clone();
    }

    public static Operation getUnaryOperation(String op, Double a) {
        switch (op) {
            case "cos":
                return new Cos(a);
            case "sin":
                return new Sin(a);
            case "log":
                return new Log(a);
            case "sqrt":
                return new Sqrt(a);
        }
        return null;
    }

    public static Operation getBinaryOperation(String op, Double a, Double b) {
        switch (op) {
            case "+":
                return new Add(a, b);
            case "-":
                return new Sub(a, b);
            case "*":
                return new Mul(a, b);
            case "/":
                return new Div(a, b);
            case "pow":
                return new Pow(a, b);
        }
        return null;
    }
}