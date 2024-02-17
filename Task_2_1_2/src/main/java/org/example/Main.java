package org.example;

import org.example.client_server.Server;
import java.io.IOException;
import java.util.Arrays;

/**
 * Главный класс.
 */
public class Main {
    /**
     * входная точка.
     *
     * @param args - аргументы командной строки
     * @throws IOException          - ошибка ввода-вывода
     * @throws InterruptedException - прерывание потока
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        example();
    }

    /**
     * пример.
     *
     * @throws IOException          - ошибка ввода-вывода
     * @throws InterruptedException - прерывание потока
     */
    public static void example() throws IOException, InterruptedException {
        var srv = new Server();
        var list = Arrays.asList(4, 4, 4, 4);
        System.out.println(srv.startServer(1, list));
    }
}