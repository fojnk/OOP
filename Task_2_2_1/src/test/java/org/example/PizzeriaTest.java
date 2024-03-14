package org.example;

import org.example.app.Pizzeria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PizzeriaTest {
    @Test
    public void checkWorkTest() {
        Main.main(new String[]{});
    }

    @Test
    public void loadingConfigurationTest() {
        var pz = new Pizzeria();
        List<String> list = new ArrayList<>();
        list.add("src/test/resources/Storage.json");
        pz.loadConfiguration("src/test/resources/Config.json",
                "src/test/resources/Orders.json",
                list);
        var bakers = pz.getBakers();
        var deliverers = pz.getDeliverers();
        Assertions.assertEquals(bakers.get(0).getName(), "Vasya");
        Assertions.assertEquals(deliverers.get(0).getName(), "Steve");
    }

    @Test
    public void pizzeriaWorkTest() {
        var pz = new Pizzeria();
        List<String> list = new ArrayList<>();
        list.add("src/test/resources/Storage.json");
        pz.loadConfiguration("src/test/resources/Config.json",
                "src/test/resources/Orders.json",
                list);

        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                try {
                    pz.Stop("src/test/resources/Storage.json");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                timer.cancel();
            }
        }, 10000);

        var orders = pz.getOrdersFromOrderQueue();
        Assertions.assertEquals(orders.get(0).getId(), 1001);
        Assertions.assertEquals(orders.get(1).getId(), 1002);
    }
}
