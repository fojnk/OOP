package com.example.task_2_3_1.game;

public class Settings {
    private static int WIDTH = 1600;
    private static int HEIGHT = 800;
    private static int ROWS = 20;
    private static int COLUMNS = 40;
    private static int blockXSideSize = WIDTH / COLUMNS;
    private static int blockYSideSize = HEIGHT / ROWS;
    private static int amountOfFood = 3;
    private static int winLength = (ROWS * COLUMNS) - 5;

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getAmountOfFood() {
        return amountOfFood;
    }

    public int getWinLength() {
        return winLength;
    }

    public void setAmountOfFood(int newAmountOfFood) {
        amountOfFood = newAmountOfFood;
    }

    public void setWinLength(int newWinLength) {
        winLength = newWinLength;
    }

    public int getROWS() {
        return ROWS;
    }

    public int getCOLUMNS() {
        return COLUMNS;
    }

    public int getBlockXSideSize() {
        return blockXSideSize;
    }

    public int getBlockYSideSize() {
        return blockYSideSize;
    }

    public void setWidth(int newWidth) {
        if (newWidth >= 400 && newWidth / COLUMNS > 0) {
            WIDTH = newWidth;
            blockXSideSize = WIDTH / COLUMNS;
        }
    }

    public void setHeight(int newHeight) {
        if (newHeight >= 400 && newHeight / ROWS > 0) {
            HEIGHT = newHeight;
            blockYSideSize = HEIGHT / ROWS;
        }
    }

    public void setColumns(int amount) {
        if (amount >= 4 && WIDTH / amount > 0) {
            COLUMNS = amount;
            blockXSideSize = WIDTH / amount;
        }
    }

    public void setRows(int amount) {
        if (amount > 4 && HEIGHT / amount > 0) {
            ROWS = amount;
            blockYSideSize = HEIGHT / amount;
        }
    }
}