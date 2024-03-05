package org.example.repository;

import com.google.gson.*;
import org.example.models.Baker;
import org.example.models.Deliverer;
import org.example.models.Order;
import org.example.models.Storage;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class JsonHandle {
    public static List<Baker> getBakersFromJson(String fileName) {
        Path path = new File(fileName).toPath();
        try (Reader reader = Files.newBufferedReader(path,
                StandardCharsets.UTF_8)) {

            JsonParser parser = new JsonParser();
            JsonElement tree = parser.parse(reader);
            var array = tree.getAsJsonObject().get("bakers").getAsJsonArray();

            List<Baker> bakers = new ArrayList<>();
            System.out.println("*********** bakers **********");
            for (var element: array) {
                if (element.isJsonObject()) {
                    JsonObject baker = element.getAsJsonObject();
                    var name = baker.get("name").getAsString();
                    var cookingTime = baker.get("cooking_time").getAsInt();
                    bakers.add(new Baker(cookingTime,name));
                    System.out.println(name);
                }
            }

            return bakers;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Deliverer> getDeliverersFromJson(String fileName) {
        Path path = new File(fileName).toPath();

        try (Reader reader = Files.newBufferedReader(path,
                StandardCharsets.UTF_8)) {

            JsonParser parser = new JsonParser();
            JsonElement tree = parser.parse(reader);
            var array = tree.getAsJsonObject().get("deliverers").getAsJsonArray();

            List<Deliverer> deliverers = new ArrayList<>();
            System.out.println("*********** deliverers **********");
            for (var element: array) {
                if (element.isJsonObject()) {
                    JsonObject deliverer = element.getAsJsonObject();
                    var name = deliverer.get("name").getAsString();
                    var deliveryTime = deliverer.get("delivery_time").getAsInt();
                    var trunkSize = deliverer.get("trunk_size").getAsInt();
                    deliverers.add(new Deliverer(deliveryTime, trunkSize, name));
                    System.out.println(name);
                }
            }
            return deliverers;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Storage getStorageFromJson(String fileName) {
        Path path = new File(fileName).toPath();

        try (Reader reader = Files.newBufferedReader(path,
                StandardCharsets.UTF_8)) {
            JsonParser parser = new JsonParser();
            JsonElement tree = parser.parse(reader);
            var capacity = tree.getAsJsonObject().get("storage_size").getAsInt();
            return new Storage(capacity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Order> getOrdersFromJson(List<String> filenames) {
        List<Order> orders = new ArrayList<>();
        System.out.println("*********** orders **********");
        for (var filename: filenames) {
            Path path = new File(filename).toPath();

            try (Reader reader = Files.newBufferedReader(path,
                    StandardCharsets.UTF_8)) {

                JsonParser parser = new JsonParser();
                JsonElement tree = parser.parse(reader);
                var array = tree.getAsJsonObject().get("orders").getAsJsonArray();

                for (var element: array) {
                    if (element.isJsonObject()) {
                        JsonObject order = element.getAsJsonObject();
                        var id = order.get("id").getAsInt();
                        var description = order.get("description").getAsString();
                        orders.add(new Order(id, description));
                        System.out.println(id);
                    }
                }
            } catch (IOException | IllegalStateException ignore) {
            }
        }
        return orders;
    }

    public static void putOrdersInStorage(List<Order> orders, String path) throws IOException {
        Map<String, List<Order>> ord = new TreeMap<>();
        ord.put("orders", orders);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        Writer writer = new FileWriter(path);
        gson.toJson(ord, writer);
        writer.flush();
        writer.close();
    }
}