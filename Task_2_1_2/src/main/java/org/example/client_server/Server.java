package org.example.client_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
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

    /**
     * запуск сервера.
     *
     * @param data - числа
     * @return - true or false
     * @throws IOException          - ошибка ввода-вывода
     * @throws InterruptedException - прерывание потока
     */
    public boolean startServer(List<Integer> data) throws IOException, InterruptedException {
        this.amount.set(0);
        this.id.set(0);
        this.data = data;
        Thread t = new Thread(new AcceptHandler());
        t.start();
        while (data.size() > id.get() && amount.get() == 0) {
        }
        t.interrupt();
        t.join();
        return amount.get() > 0;
    }

    /**
     * Подкласс для обработки новых подключений.
     */
    public class AcceptHandler implements Runnable {

        /**
         * Метод, который запуститься в новом потоке.
         */
        @Override
        public void run() {
            try {
                var serverSocket = new ServerSocket(8000);
                List<Thread> threads = new ArrayList<>();
                int amountOfClt = 0;
                serverSocket.setSoTimeout(1000);

                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Socket socket = serverSocket.accept();
                        Thread t = new Thread(new ClientHandler(socket, id, amount, data));
                        t.start();
                        threads.add(t);
                        System.out.println("one more slave");
                        amountOfClt++;
                    } catch (SocketTimeoutException ignore) {
                    }
                }

                for (int i = 0; i < amountOfClt; i++) {
                    try {
                        threads.get(i).join();
                    } catch (InterruptedException ignored) {
                    }
                }
                serverSocket.close();

            } catch (IOException ignore) {
            }
        }
    }

    /**
     * подкласс для обработки соединения и передачи данных клиентам.
     */
    public class ClientHandler implements Runnable {

        private final Socket socket;
        private final AtomicInteger id;
        private final List<Integer> data;
        private final AtomicInteger amount;

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
            int dataSafe = id.get();
            try {
                var in = new Scanner(socket.getInputStream());
                var out = new PrintWriter(socket.getOutputStream());
                boolean run = true;
                out.println("you are connected to server :)");
                out.flush();
                while (run) {
                    try {
                        dataSafe = id.incrementAndGet();
                        var k = data.get(dataSafe);
                        out.println(k);
                        out.flush();
                    } catch (NullPointerException | IndexOutOfBoundsException e) {
                        break;
                    }

                    if (in.hasNext()) {
                        if (in.nextInt() == 1) {
                            amount.incrementAndGet();
                            run = false;
                        }
                    }
                }

            } catch (IllegalBlockingModeException | IOException | IndexOutOfBoundsException ignored) {
                id.set(dataSafe);
            }
        }
    }
}