package org.example;

/**
 * главный класс.
 */
public class Main {

    /**
     * точка входа.
     *
     * @param args - аргументы командной строки
     */
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial(new int[]{});
        Polynomial p2 = new Polynomial(new int[]{1});
        System.out.println("Polynom p1: " + p1.equals(p2));
        System.out.println("Polynom p2: " + p2.toString());
        System.out.println("------------------------------");
        System.out.println(p1.plus(p2.differentiate(1)).toString());
        System.out.println(p1.times(p2).evaluate(2));
    }
}