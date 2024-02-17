package org.example.client_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.IllegalBlockingModeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Серверная часть.
 */
public class Server {
    private final AtomicInteger id = new AtomicInteger();
    private List<Integer> data;
    private final AtomicInteger amount = new AtomicInteger();
    private int amountOfClients;

    /**
     * запуск сервера.
     *
     * @param requiredAmountOfClients - требуемое количество клиентов
     * @param data                    - числа
     * @return - true or false
     * @throws IOException          - ошибка ввода-вывода
     * @throws InterruptedException - прерывание потока
     */
    public boolean startServer(int requiredAmountOfClients, List<Integer> data) throws IOException, InterruptedException {
        this.amount.set(0);
        this.id.set(0);
        this.data = data;
        amountOfClients = 0;
        var serverSocket = new ServerSocket(8000);
        List<Thread> threads = new ArrayList<>();

        while (amountOfClients < requiredAmountOfClients) {
            Socket socket = serverSocket.accept();
            Thread t = new Thread(new ClientHandler(socket, id, amount, data));
            t.start();
            threads.add(t);
            System.out.println("one more slave");
            amountOfClients++;
        }

        for (int i = 0; i < amountOfClients; i++) {
            threads.get(i).join();
        }

        return amount.get() > 0;
    }

    /**
     * подкласс для обработки соединения и передачи данных клиентам.
     */
    public class ClientHandler implements Runnable {

        private Socket socket;
        private AtomicInteger id;
        private List<Integer> data;
        private AtomicInteger amount;

        /**
         * контструктор обработчика клиента.
         *
         * @param socket - сокет
         * @param id     - позиция в списки
         * @param amount - количество найденных простых чисел
         * @param data   - данные
         */
        public ClientHandler(Socket socket, AtomicInteger id, AtomicInteger amount, List<Integer> data) {
            this.socket = socket;
            this.id = id;
            this.amount = amount;
            this.data = data;
        }

        /**
         * Метод, который запуститься в новом потоке.
         */
        @Override
        public void run() {
            try {
                var in = new Scanner(socket.getInputStream());
                var out = new PrintWriter(socket.getOutputStream());
                boolean run = true;
                out.println("you are connected to server :)");
                out.flush();
                while (run) {
                    try {
                        var k = data.get(id.incrementAndGet());
                        out.println(k);
                        out.flush();
                    } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                        break;
                    }

                    if (in.hasNext()) {
                        if (in.nextInt() == 1) {
                            amount.incrementAndGet();
                            run = false;
                        }
                    }
                }

            } catch (IllegalBlockingModeException | IOException e) {
            }
        }
    }
}