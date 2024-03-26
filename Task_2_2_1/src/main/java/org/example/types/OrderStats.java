package org.example.types;

public enum OrderStats {
    ACCEPTED,
    IN_QUEUE,
    IS_COOKING,
    WAITING_TO_BE_SENT_TO_STORAGE,
    IN_STORAGE,
    DELIVERING,
    DONE
}