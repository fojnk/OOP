package com.example.task_2_3_1.models;

public class Food {
    private final String ImagePath;
    private final int positionX;
    private final int positionY;

    public Food(String imagePath, int positionX, int positionY) {
        this.ImagePath = imagePath;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public String getImagePath() {
        return this.ImagePath;
    }
}