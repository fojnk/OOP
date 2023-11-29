package org.example;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

/**
 * класс калькулятора.
 */
public class Calculator {
    private String inputExp;
    private final Stack<Double> numbers;

    /**
     * конструктор калькулятора для пользователя.
     */
    public Calculator() {
        this.numbers = new Stack<>();
    }

    /**
     * конструктор калькулятора для тестов.
     *
     * @param inputExp - выражение в префиксной форме
     */
    public Calculator(String inputExp) {
        this.numbers = new Stack<>();
        this.inputExp = inputExp;
    }

    /**
     * пользовательский интерфейс калькулятора.
     */
    public void userInterface() {
        var scanner = new Scanner(System.in);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("% Hello, i am noob version of PrefixCalc");
        System.out.println("% Now i only have these operations:     ");
        System.out.print("% Unary operations: ");
        System.out.println(Arrays.toString(OperationsHandler.getAllUnaryOperations()));
        System.out.print("% Binary operations: ");
        System.out.println(Arrays.toString(OperationsHandler.getAllBinaryOperations()));
        System.out.println("% If you want to exit -> press q");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        while (true) {
            System.out.println("Please enter expression in prefix form:");
            this.inputExp = scanner.nextLine();
            if (inputExp.equals("q")) {
                break;
            }
            System.out.println("Answer: " + evaluateExp());
        }
    }

    /**
     * метод для подсчета значения выражений.
     *
     * @return - ответ
     */
    public Double evaluateExp() {
        try {
            String[] args = this.inputExp.split(" ");
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
                System.out.println("Bad Expression form");
                return null;
            }
            return result;
        } catch (EmptyStackException e) {
            System.out.println("Bad Expression form, try again");
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Invalid operation");
            return null;
        }
    }
}