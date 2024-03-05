package org.example.app;

import org.example.models.Baker;
import org.example.models.Deliverer;
import org.example.models.OrderQueue;
import org.example.models.Storage;
import org.example.repository.JsonHandle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pizzeria {
    private List<Baker> bakers;
    private List<Deliverer> deliverers;
    private Storage storage;
    private OrderQueue orderQueue;

    private List<Thread> bakersThreads;
    private List<Thread> deliverersThreads;

    public Pizzeria() {
        this.orderQueue = new OrderQueue();
    }

    private void loadConfiguration(String configPath, String orderPath, List<String> storagesPaths) {
        bakers = JsonHandle.getBakersFromJson(configPath);
        deliverers = JsonHandle.getDeliverersFromJson(configPath);
        storage = JsonHandle.getStorageFromJson(configPath);
        storagesPaths.add(orderPath);
        var orders = JsonHandle.getOrdersFromJson(storagesPaths);
        for (var order : orders) {
            this.orderQueue.putOrder(order);
        }
        for (var baker: bakers) {
            baker.setStorageAndOrderQueue(this.storage, this.orderQueue);
        }
        for (var deliverer: deliverers) {
            deliverer.setStorageAndOrderQueue(this.storage, this.orderQueue);
        }
    }

    public void Start(String configPath, String orderPath, List<String> storages) {
        loadConfiguration(configPath, orderPath, storages);
        this.bakersThreads = new ArrayList<>();
        this.deliverersThreads = new ArrayList<>();

        for (var baker: bakers) {
            Thread t = new Thread(baker);
            t.start();
            bakersThreads.add(t);
        }
        for (var deliverer: deliverers) {
            Thread t = new Thread(deliverer);
            t.start();
            deliverersThreads.add(t);
        }
    }

    public void Stop(String path) throws IOException {
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
    }
}