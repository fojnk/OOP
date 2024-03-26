package org.example.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.types.OrderStats;

import java.util.LinkedList;
import java.util.Queue;

public class Deliverer implements Runnable {
    private static final Logger logger = LogManager.getLogger(Deliverer.class);;
    private final String name;
    private final int deliveryTime;
    private Storage storage;
    private final int trunkCapacity;
    private OrderQueue orderQueue;
    private Queue<Order> trunk;

    public Deliverer(int deliveryTime, int trunkCapacity, String name) {
        this.name = name;
        this.deliveryTime = deliveryTime;
        this.trunkCapacity = trunkCapacity;
    }

    public String getName() {
        return this.name;
    }

    public int getDeliveryTime() {
        return this.deliveryTime;
    }

    public int getTrunkCapacity() {
        return this.trunkCapacity;
    }

    public void setStorageAndOrderQueue(Storage storage, OrderQueue orderQueue) {
        this.storage = storage;
        this.orderQueue = orderQueue;
    }

    private void TakeOrders() {
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

    private void Delivering() throws InterruptedException {
        for (var order : trunk) {
            if (order != null) {
                logger.info(order.updateStatus(OrderStats.DELIVERING));
                Thread.sleep(this.deliveryTime);
                logger.info(order.updateStatus(OrderStats.DONE));
            } else {
                Thread.currentThread().interrupt();
            }
        }
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
                        orderQueue.putFirstInOrder(trunk.poll());
                    }
                }
                return;
            }
        }
    }
}