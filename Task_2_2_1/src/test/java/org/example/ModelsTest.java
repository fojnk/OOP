package org.example;

import org.example.models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ModelsTest {
    @Test
    public void SimpleTest() {
        var order = new Order(1001, "some pizza");
        var orderQueue = new OrderQueue();
        orderQueue.putOrder(order);
        Assertions.assertEquals(order.getId(), orderQueue.getOrder().getId());
        var storage = new Storage(5);
        storage.putOrder(order);
        Assertions.assertEquals(order.getId(), storage.getOrder().getId());
        var baker = new Baker(1, "Bob");
        baker.setStorageAndOrderQueue(storage, orderQueue);

        //Проверка на передачу заказов на склад
        orderQueue.putOrder(order);
        var t1 = new Thread(baker);
        t1.start();
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                t1.interrupt();
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Assertions.assertEquals(order.getId(), storage.getOrder().getId());
                timer.cancel();
            }
        }, 200);

        var order2 = new Order(1002,"some pizza");
        storage.putOrder(order2);
        var deliverer = new Deliverer(1000, 3, "Steve");
        deliverer.setStorageAndOrderQueue(storage, orderQueue);

        // Проверка действий курьера при прерывании доставки
        var t2 = new Thread(deliverer);
        t2.start();
        timer.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                t2.interrupt();
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Assertions.assertEquals(order.getId(), orderQueue.getOrder().getId());
                Assertions.assertTrue(orderQueue.contains(order));
                Assertions.assertTrue(orderQueue.contains(order2));
                timer.cancel();
            }
        }, 100);

        // Проверка на выполнение доставки
        var t3 = new Thread(deliverer);
        timer.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                t3.interrupt();
                try {
                    t3.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Assertions.assertFalse(storage.contains(order));
                timer.cancel();
            }
        }, 2000);
    }

    @Test
    public void StorageAndOrderQueueTest() {
        var order = new Order(1001, "some pizza");
        var orderQueue = new OrderQueue();
        orderQueue.putOrder(order);
        Assertions.assertEquals(order.getId(), orderQueue.getOrder().getId());
        Assertions.assertFalse(orderQueue.contains(order));
        var storage = new Storage(5);
        storage.putOrder(order);
        Assertions.assertTrue(storage.contains(order));
        Assertions.assertEquals(storage.getCapacity(), 5);
        orderQueue.putOrder(order);
        Assertions.assertEquals(orderQueue.getAllOrders().get(0).getId(), order.getId());
    }
}