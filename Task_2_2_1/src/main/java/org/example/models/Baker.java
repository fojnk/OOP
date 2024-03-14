package org.example.models;

import org.example.app.Pizzeria;
import org.example.types.OrderStats;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Baker implements Runnable {
    private static final Logger logger = LogManager.getLogger(Baker.class);;
    private final String name;
    private final int cookingTime;
    private Storage storage;
    private OrderQueue orderQueue;
    private boolean isBusy;
    private Order currOrder;

    public Baker(int cookingTime, String name) {
        this.name = name;
        this.cookingTime = cookingTime;
        this.isBusy = false;
    }

    public void setStorageAndOrderQueue(Storage storage, OrderQueue orderQueue) {
        this.storage = storage;
        this.orderQueue = orderQueue;
    }

    public String getName() {
        return this.name;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    private void takeOrder () {
        currOrder = orderQueue.getOrder();
        if (currOrder != null) {
            isBusy = true;
            logger.info(currOrder.updateStatus(OrderStats.ACCEPTED));
        } else {
            isBusy = false;
            Thread.currentThread().interrupt();
        }
    }

    private void cook() throws InterruptedException {
        if (isBusy) {
            logger.info(currOrder.updateStatus(OrderStats.IS_COOKING));
            Thread.sleep(this.cookingTime);
        }
    }

    private void putInStorage() {
        if (isBusy) {
            logger.info(currOrder.updateStatus(OrderStats.WAITING_TO_BE_SENT_TO_STORAGE));
            storage.putOrder(currOrder);
            logger.info(currOrder.updateStatus(OrderStats.IN_STORAGE));
            isBusy = false;
        }
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
                    orderQueue.putFirstInOrder(currOrder);
                }
                return;
            }
        }
    }
}