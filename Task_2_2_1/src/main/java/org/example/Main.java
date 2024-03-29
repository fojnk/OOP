package org.example;


import org.example.app.Pizzeria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var pz = new Pizzeria();
        List<String> list = new ArrayList<>();
        list.add("src/main/resources/Storage.json");
        pz.Start(
                "src/main/resources/Config.json",
                "src/main/resources/Orders.json",
                list
        );
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                try {
                    pz.Stop("src/main/resources/Storage.json");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                timer.cancel();
            }
        }, 10000);
    }
}