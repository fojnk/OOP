package com.example.task_2_3_1.models;

public class Rock {
    private final int positionX;
    private final int positionY;
    private final String imagePath;

    public Rock(int positionX, int positionY, String imagePath) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.imagePath = imagePath;
    }

    public int getXPos() {
        return positionX;
    }

    public int getYPos() {
        return positionY;
    }

    public String getImagePath() {
        return imagePath;
    }
}
