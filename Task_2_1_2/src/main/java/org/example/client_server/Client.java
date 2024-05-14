package org.example.client_server;

import java.io.IOException;
import java.io.InterruptedIOException;
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
    public static void main(String[] args) {
        var waitConnection = true;

            try (Socket socket = new Socket("localhost", 8000)) {
                var in = new Scanner(socket.getInputStream());
                var out = new PrintWriter(socket.getOutputStream(), true);
                if (in.hasNext()) {
                    waitConnection = false;
                    System.out.println(in.nextLine());
                }
                while (in.hasNext()) {
                    var number = in.nextInt();
                    if (IsPrime.isPrime(number)) {
                        out.println(1);
                    } else {
                        out.println(0);
                    }
                }
                in.close();
                out.close();
            } catch (IOException e) {
                if (!waitConnection) {
                    System.out.println("brake connection");
                }
            }

    }
}