package org.example.models;

import org.example.types.OrderStats;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Deliverer implements Runnable {
    private String name;
    private final int deliveryTime;
    private Storage storage;
    private final int trunkCapacity;
    private OrderQueue orderQueue;
    private boolean isBusy;

    private Queue<Order> trunk;

    public Deliverer(int deliveryTime, int trunkCapacity, String name) {
        this.name = name;
        this.deliveryTime = deliveryTime;
        this.trunkCapacity = trunkCapacity;
    }

    public void setStorageAndOrderQueue(Storage storage, OrderQueue orderQueue) {
        this.storage = storage;
        this.orderQueue = orderQueue;
    }

    public void TakeOrders() {
        var amountOfTakenOrders = 0;
        trunk = new LinkedList<>();
        Order order = storage.getOrder();
        trunk.add(order);
        while (amountOfTakenOrders < trunkCapacity && order != null) {
            order = storage.getOrderWithoutWaiting();
            if (order != null) {
                trunk.add(order);
                amountOfTakenOrders++;
            }
        }
    }

    public void Delivering() throws InterruptedException {
        isBusy = true;
        for (var order : trunk) {
            if (order != null) {
                System.out.println(order.updateStatus(OrderStats.DELIVERING));
                Thread.sleep(this.deliveryTime);
                System.out.println(order.updateStatus(OrderStats.DONE));
            } else {
                Thread.currentThread().interrupt();
            }
        }
        isBusy = false;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                TakeOrders();
                Delivering();
            } catch (InterruptedException e) {
                if (!trunk.isEmpty()) {
                    while (!trunk.isEmpty() ) {
                        orderQueue.putOrder(trunk.poll());
                    }
                }
                return;
            }
        }
    }
}