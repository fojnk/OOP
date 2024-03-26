package org.example.client_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Клиентская часть, здесь производится проверка числа на простоту.
 */
public class Client {
    /**
     * входная точка клиентской части.
     *
     * @param args - аргументы командной строки
     * @throws IOException - ошибка операции ввода-вывода
     */
    public static void main(String[] args) throws IOException {
        var socket = new Socket("localhost", 8000);
        var in = new Scanner(socket.getInputStream());
        var out = new PrintWriter(socket.getOutputStream());
        if (in.hasNext()) {
            System.out.println(in.nextLine());
        }
        while (in.hasNext()) {
            var number = in.nextInt();
            if (IsPrime.isPrime(number)) {
                out.println(1);
                out.flush();
            } else {
                out.println(0);
                out.flush();
            }
        }
        in.close();
        out.close();
        socket.close();
    }
}