package org.example.models;

public class SnakePart {
    private int positionX;
    private int positionY;

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
