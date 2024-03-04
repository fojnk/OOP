package org.example.models;

import org.example.types.OrderStats;

public class Order {
    private final int id;
    private OrderStats status;
    private final String description;

    public Order (int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {return this.description;}

    public OrderStats getStatus() {
        return this.status;
    }

    public String updateStatus(OrderStats newStatus) {
        this.status = newStatus;
        String statusMessage = null;
        switch (this.status) {
            case IN_QUEUE -> statusMessage = "in queue for cooking";
            case ACCEPTED -> statusMessage = "accepted";
            case IS_COOKING -> statusMessage = "is cooking";
            case WAITING_TO_BE_SENT_TO_STORAGE -> statusMessage = "waiting to be sent to storage";
            case IN_STORAGE -> statusMessage = "in storage";
            case DELIVERING -> statusMessage = "delivering";
            case DONE -> statusMessage = "done";
            default -> statusMessage = "unknown status";
        }
        return String.format("[%d] [%s]", this.id, statusMessage);
    }
}