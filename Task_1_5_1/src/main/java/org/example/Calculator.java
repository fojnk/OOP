package org.example;

import java.util.Stack;

/**
 * класс калькулятора.
 */
public class Calculator {
    private String inputExp;
    private Stack<Double> numbers;

    /**
     * конструктор калькулятора для пользователя.
     */
    public Calculator() {
        this.numbers = new Stack<>();
    }

    /**
     * метод для добавления нового выражения.
     * @param exp - новое выражение.
     */
    public void addNewExpression(String exp) {
        this.numbers = new Stack<>();
        this.inputExp = exp;
    }

    /**
     * метод для подсчета значения выражений.
     *
     * @return - ответ
     */
    public Double evaluateExp() {
        String[] args = this.inputExp.split("\\s+");
        for (int i = args.length - 1; i >= 0; i--) {
            if (OperationsHandler.argIsUnaryOp(args[i])) {
                numbers.push(OperationsHandler.getUnaryOperation(args[i],
                        numbers.pop()).evaluate());
            } else if (OperationsHandler.argIsBinaryOp(args[i])) {
                numbers.push(OperationsHandler.getBinaryOperation(args[i],
                        numbers.pop(), numbers.pop()).evaluate());
            } else {
                numbers.push(Double.valueOf(args[i]));
            }
        }
        Double result = numbers.pop();
        if (!numbers.empty()) {
            throw new IllegalArgumentException();
        }
        return result;
    }
}