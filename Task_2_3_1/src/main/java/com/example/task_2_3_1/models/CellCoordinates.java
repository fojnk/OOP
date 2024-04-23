package com.example.task_2_3_1.models;

public class CellCoordinates {
    private int xPos;
    private int yPos;

    public CellCoordinates(int yPos, int xPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
}