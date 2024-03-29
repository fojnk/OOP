package org.example.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrderQueue {
    private final LinkedList<Order> orders;

    public OrderQueue() {
        this.orders = new LinkedList<>();
    }

    public boolean contains(Order order) {
        return orders.contains(order);
    }

    public int getAmountOfOrders() {
        return orders.size();
    }

    public synchronized void putOrder(Order order) {
        orders.add(order);
    }

    public synchronized void putFirstInOrder(Order order) {
        orders.addFirst(order);
    }

    public List<Order> getAllOrders(){
        List<Order> list = new ArrayList<>();
        for (var order : orders) {
            list.add(new Order(order.getId(), order.getDescription()));
        }
        return list;
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
}