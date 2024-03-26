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
        java.util.Timer timer1 = new java.util.Timer();
        timer1.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                t1.interrupt();
                timer1.cancel();
            }
        }, 200);
        try {
            t1.join();
        } catch (InterruptedException ignore) {}
        Assertions.assertEquals(order.getId(), storage.getOrder().getId());

        var order2 = new Order(1002,"some pizza");
        storage.putOrder(order2);
        var deliverer = new Deliverer(1000, 3, "Steve");
        deliverer.setStorageAndOrderQueue(storage, orderQueue);

        // Проверка действий курьера при прерывании доставки
        java.util.Timer timer2 = new java.util.Timer();
        var t2 = new Thread(deliverer);
        t2.start();

        timer2.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                t2.interrupt();
                timer2.cancel();
            }
        }, 500);
        try {
            t2.join();
        } catch (InterruptedException ignore) { }
        Assertions.assertEquals(order2.getId(), orderQueue.getOrder().getId());
        Assertions.assertFalse(orderQueue.contains(order2));

        // Проверка на выполнение доставки
        var t3 = new Thread(deliverer);
        java.util.Timer timer3 = new java.util.Timer();
        timer3.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                t3.interrupt();
                timer3.cancel();
            }
        }, 2000);
        try {
            t3.join();
        } catch (InterruptedException ignore) {
        }
        Assertions.assertFalse(storage.contains(order));
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