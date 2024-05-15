package org.example.client_server;

import java.io.Flushable;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.channels.IllegalBlockingModeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Серверная часть.
 */
public class Server {
    private BlockingQueue<Integer> data;
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
        this.data = new LinkedBlockingDeque<>();
        this.data.addAll(data);
        Thread t = new Thread(new AcceptHandler());
        t.start();
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
                        Thread t = new Thread(new ClientHandler(socket, amount, data));
                        t.start();
                        threads.add(t);
                        System.out.println("one more slave");
                        amountOfClt++;
                    } catch (SocketTimeoutException ignore) {
                        if (amount.get() > 0 || data.isEmpty()) {
                            break;
                        }
                    }
                }

                for (int i = 0; i < amountOfClt; i++) {
                    try {
                        threads.get(i).interrupt();
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
        private final BlockingQueue<Integer> data;
        private final AtomicInteger amount;

        /**
         * контструктор обработчика клиента.
         *
         * @param socket - сокет
         * @param amount - количество найденных простых чисел
         * @param data   - данные
         */
        public ClientHandler(Socket socket, AtomicInteger amount, BlockingQueue<Integer> data) {
            this.socket = socket;
            this.amount = amount;
            this.data = data;
        }

        /**
         * Метод, который запуститься в новом потоке.
         */
        @Override
        public void run() {
            Integer dataSafe = 4;
            try {
                var in = new Scanner(socket.getInputStream());
                var out = new PrintWriter(socket.getOutputStream(), true);
                out.println("you are connected to server :)");
                while (!Thread.currentThread().isInterrupted() && !data.isEmpty() && (amount.get() == 0)) {
                    dataSafe = data.poll();
                    if (dataSafe == null) {
                        break;
                    }
                    out.println(dataSafe);
                    if (in.hasNext()) {
                        if (in.nextInt() == 1) {
                            amount.incrementAndGet();
                            break;
                        }
                    }
                }
                in.close();
                out.close();
            } catch (IllegalBlockingModeException | IOException | IndexOutOfBoundsException ignored) {
                if (dataSafe != null) {
                    data.add(dataSafe);
                }
            }
        }
    }
}