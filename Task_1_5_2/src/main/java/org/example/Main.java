package org.example;

import java.io.IOException;

/**
 * Главный класс.
 */
public class Main {
    /**
     * точка входа.
     *
     * @param args - аргументы командной строки
     * @throws IOException - исключение ввода-вывода
     */
    public static void main(String[] args) throws IOException {
        UserInterface userin = new UserInterface("notebook.json");
        userin.ExecCommand(new String[]{"-show"});
    }
}