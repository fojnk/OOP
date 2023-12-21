package org.example;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * class for calc interface.
 */
class CalcUserInterface {
    /**
     * user interface.
     */
    public static void userInterface() {
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
        var calc = new Calculator();
        Double answer;
        while (true) {
            System.out.println("Please enter expression in prefix form:");
            var inputExp = scanner.nextLine();
            if (inputExp.equals("q")) {
                break;
            }
            calc.addNewExpression(inputExp);
            try {
                answer = calc.evaluateExp();
                System.out.println("Answer: " + answer);
            } catch (EmptyStackException e) {
                System.out.println("Bad expression format, try again");
            } catch (NumberFormatException e) {
                System.out.println("Unknown option");
            } catch (IllegalArgumentException e) {
                System.out.println("Too many arguments");
            }
        }
    }
}