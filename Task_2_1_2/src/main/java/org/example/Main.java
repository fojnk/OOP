package org.example;

import java.io.IOException;
import java.util.ArrayList;
import org.example.client_server.Server;

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
        var list = new ArrayList<Integer>();
        for (int i = 0; i < 200000; i++) {
            list.add(49);
        }
        System.out.println(srv.startServer(list));
    }
}