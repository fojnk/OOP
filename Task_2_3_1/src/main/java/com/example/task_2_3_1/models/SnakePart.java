package com.example.task_2_3_1.models;

public class SnakePart {
    private final int positionX;
    private final int positionY;

    public SnakePart(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }
}