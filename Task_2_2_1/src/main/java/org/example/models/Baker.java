package org.example.models;

import org.example.types.OrderStats;

public class Baker implements Runnable {
    private String name;
    private int cookingTime;
    private Storage storage;
    private OrderQueue orderQueue;
    private boolean isBusy;
    private Order currOrder;

    private boolean hardStopWork;
    private boolean lightStopWork;

    public Baker(int cookingTime, String name) {
        this.name = name;
        this.cookingTime = cookingTime;
        this.hardStopWork = false;
        this.lightStopWork = false;
        this.isBusy = false;
    }

    public void setStorageAndOrderQueue(Storage storage, OrderQueue orderQueue) {
        this.storage = storage;
        this.orderQueue = orderQueue;
    }

    public void takeOrder () {
        currOrder = orderQueue.getOrder();
        isBusy = true;
        System.out.println(currOrder.updateStatus(OrderStats.ACCEPTED));
    }

    public void cook() throws InterruptedException {
        System.out.println(currOrder.updateStatus(OrderStats.IS_COOKING));
        Thread.sleep(this.cookingTime);
    }

    public void putInStorage() {
        System.out.println(currOrder.updateStatus(OrderStats.WAITING_TO_BE_SENT_TO_STORAGE));
        storage.putOrder(currOrder);
        System.out.println(currOrder.updateStatus(OrderStats.IN_STORAGE));
        isBusy = false;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                takeOrder();
                cook();
                putInStorage();
            } catch (InterruptedException e) {
                if (isBusy) {
                    orderQueue.putOrder(currOrder);
                }
                return;
            }
        }
    }
}