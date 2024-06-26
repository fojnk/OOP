package org.example;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.example.client_server.Client;
import org.example.client_server.IsPrime;
import org.example.client_server.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * класс для тестов.
 */
public class ClientServerTest {

    private boolean res;

    /**
     * простой тест isprime.
     */
    @Test
    public void simplePrimeTest() {
        var list1 = Arrays.asList(2, 3, 3, 3, 3, 5, 7, 11);
        for (var tmp : list1) {
            Assertions.assertTrue(IsPrime.isPrime(tmp));
        }

        var list2 = Arrays.asList(4, 6, 8, 10, 12, 14, 16);
        for (var tmp : list2) {
            Assertions.assertFalse(IsPrime.isPrime(tmp));
        }
    }

    /**
     * тест клиента и сервера.
     *
     * @throws InterruptedException - прерывание потока
     */
    @Test
    public void clientServerSimpleTest() throws InterruptedException {
        res = false;
        Thread serverThread = new Thread(new ServerThread(Arrays.asList(49, 49, 49, 49, 49)));
        serverThread.start();
        Thread.sleep(100);
        Thread client1 = new Thread(new ClientThread());
        Thread client2 = new Thread(new ClientThread());
        client1.start();
        client2.start();
        serverThread.join();
        client1.join();
        client2.join();

        Assertions.assertFalse(res);
    }

    /**
     * подкласс для запуска сервера в потоке.
     */
    public class ServerThread implements Runnable {
        private final List<Integer> data;

        /**
         * конструктор подкласса.
         *
         * @param data            - данные
         */
        public ServerThread(List<Integer> data) {
            this.data = data;
        }

        /**
         * метод, который запустится в новом потоке.
         */
        @Override
        public void run() {
            var srv = new Server();
            try {
                res = srv.startServer(data);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * подкласс для запуска клиента в потоке.
     */
    public static class ClientThread implements Runnable {

        /**
         * метод, который запустится в потоке.
         */
        @Override
        public void run() {
                Client.main(new String[]{});
        }
    }
}