package org.example;

import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
        NotebookApp userin = new NotebookApp("notebook.json",
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm z").withZone(ZoneId.systemDefault()));
        userin.execCommand(args);
    }
}