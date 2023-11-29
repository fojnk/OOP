package org.example;

import org.example.operations.*;

/**
 * класс для манипуляций с операциями.
 */
public class OperationsHandler {
    private static final String[] unaryOps = new String[]{"sin", "cos", "sqrt", "log"};
    private static final String[] binaryOps = new String[]{"+", "-", "*", "/", "pow"};

    /**
     * метод для проверки операции на унарность.
     *
     * @param arg - один из токенов
     * @return - true or false
     */
    public static boolean argIsUnaryOp(String arg) {
        for (String unaryOp : unaryOps) {
            if (arg.equals(unaryOp)) {
                return true;
            }
        }
        return false;
    }

    /**
     * метод для проверки операции на бинарность.
     *
     * @param arg - один из токенов
     * @return - true or false
     */
    public static boolean argIsBinaryOp(String arg) {
        for (String binaryOp : binaryOps) {
            if (arg.equals(binaryOp)) {
                return true;
            }
        }
        return false;
    }

    /**
     * метод для получения всех унарных операций.
     *
     * @return - массив унарных операций
     */
    public static String[] getAllUnaryOperations() {
        return unaryOps.clone();
    }

    /**
     * метод для получения бинарных операций.
     *
     * @return - массив бинарных операций
     */
    public static String[] getAllBinaryOperations() {
        return binaryOps.clone();
    }

    /**
     * метод для создания унарных операций.
     *
     * @param op - операция
     * @param a  - аргумент
     * @return - функция
     */
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

    /**
     * метод для создания бинарных операций.
     *
     * @param op - операция
     * @param a  - первый аргумент
     * @param b  - второй аргумент
     * @return - функция
     */
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