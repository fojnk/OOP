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

        var deliverer = new Deliverer(10000, 3, "Steve");
        deliverer.setStorageAndOrderQueue(storage, orderQueue);

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
                timer.cancel();
            }
        }, 100);
    }
}