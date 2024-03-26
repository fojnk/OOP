package org.example.models;

import java.util.LinkedList;
import java.util.Queue;

public class Storage {
    private final int capacity;
    private final Queue<Order> orders;

    public Storage(int capacity) {
        this.capacity = capacity;
        this.orders = new LinkedList<>();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public boolean contains(Order order) {
        return this.orders.contains(order);
    }

    public synchronized void putOrder(Order order) {
        while (orders.size() >= this.capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                return;
            }
        }
        orders.add(order);
        notifyAll();
    }

    public synchronized Order getOrder() {
        while(orders.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                return null;
            }
        }
        var order = orders.poll();
        notifyAll();
        return order;
    }

    public Order getOrderWithoutWaiting() {
        return orders.poll();
    }
}