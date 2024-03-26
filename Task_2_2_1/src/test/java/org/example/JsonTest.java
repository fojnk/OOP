package org.example;

import org.example.models.Order;
import org.example.repository.JsonHandle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonTest {
    String STORAGE_PATH = "src/test/resources/Storage.json";
    String CONFIG_PATH = "src/test/resources/Config.json";

    @Test
    public void OrderJsonTest() throws IOException {
        List<Order> list = new ArrayList<>();
        for (int i = 1000; i < 1004; i ++) {
            list.add(new Order(i, "some pizza"));
        }

        JsonHandle.putOrdersInStorage(list, STORAGE_PATH);
        List<String> path_list = new ArrayList<>();
        path_list.add(STORAGE_PATH);
        var output = JsonHandle.getOrdersFromJson(path_list);

        for (int i = 1000; i < 1004; i ++) {
            var order = output.get(i - 1000);
            Assertions.assertEquals(order.getId(), list.get(i - 1000).getId());
            Assertions.assertEquals(order.getDescription(), list.get(i - 1000).getDescription());
        }

        list = new ArrayList<>();
        JsonHandle.putOrdersInStorage(list, STORAGE_PATH);
    }

    @Test
    public void LoadConfigTest() {
        var bakers = JsonHandle.getBakersFromJson(CONFIG_PATH);
        var deliverers = JsonHandle.getDeliverersFromJson(CONFIG_PATH);
        var storage = JsonHandle.getStorageFromJson(CONFIG_PATH);

        Assertions.assertEquals(bakers.get(0).getName(), "Vasya");
        Assertions.assertEquals(bakers.get(0).getCookingTime(), 9000);

        Assertions.assertEquals(deliverers.get(0).getName(), "Steve");
        Assertions.assertEquals(deliverers.get(0).getDeliveryTime(), 5000);
        Assertions.assertEquals(deliverers.get(0).getTrunkCapacity(), 3);

        Assertions.assertEquals(storage.getCapacity(), 10);
    }
}