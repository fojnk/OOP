package org.example.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.models.Baker;
import org.example.models.Deliverer;
import org.example.models.OrderQueue;
import org.example.models.Storage;
import org.example.repository.JsonHandle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pizzeria {

    private static final Logger logger = LogManager.getLogger(Pizzeria.class);
    private List<Baker> bakers;
    private List<Deliverer> deliverers;
    private final OrderQueue orderQueue;
    private List<Thread> bakersThreads;
    private List<Thread> deliverersThreads;

    public Pizzeria() {
        this.orderQueue = new OrderQueue();
    }

    private void loadConfiguration(String configPath, String orderPath, List<String> storagesPaths) {
        bakers = JsonHandle.getBakersFromJson(configPath);
        deliverers = JsonHandle.getDeliverersFromJson(configPath);
        Storage storage = JsonHandle.getStorageFromJson(configPath);
        storagesPaths.add(orderPath);
        var orders = JsonHandle.getOrdersFromJson(storagesPaths);
        for (var order : orders) {
            this.orderQueue.putOrder(order);
        }
        for (var baker: bakers) {
            baker.setStorageAndOrderQueue(storage, this.orderQueue);
        }
        for (var deliverer: deliverers) {
            deliverer.setStorageAndOrderQueue(storage, this.orderQueue);
        }
    }

    public void Start(String configPath, String orderPath, List<String> storages) {
        logger.info("Loading configuration...");
        loadConfiguration(configPath, orderPath, storages);
        this.bakersThreads = new ArrayList<>();
        this.deliverersThreads = new ArrayList<>();

        logger.info("Setting bakers...");
        for (var baker: bakers) {
            Thread t = new Thread(baker);
            t.start();
            bakersThreads.add(t);
        }

        logger.info("Setting deliverers...");
        for (var deliverer: deliverers) {
            Thread t = new Thread(deliverer);
            t.start();
            deliverersThreads.add(t);
        }

        logger.info("Pizzeria started");
    }

    public void Stop(String path) throws IOException {
        logger.info("Stopping work...");
        bakersThreads.forEach(Thread::interrupt);
        deliverersThreads.forEach(Thread::interrupt);

        for (var thread : bakersThreads) {
            try {
                thread.join();
            } catch (InterruptedException ignore) {}
        }

        for (var thread : deliverersThreads) {
            try {
                thread.join();
            } catch (InterruptedException ignore) {}
        }

        if (orderQueue.getAmountOfOrders() != 0) {
            JsonHandle.putOrdersInStorage(orderQueue.getAllOrders(), path);
        }
        logger.info("Pizzeria stopped...");
    }
}