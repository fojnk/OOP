package com.example.task_2_3_1.game;

public class Settings {
    private static int WIDTH = 800;
    private static int HEIGHT = 500;
    private static int ROWS = 25;
    private static int COLUMNS = 40;
    private static int blockXSideSize = WIDTH / COLUMNS;
    private static int blockYSideSize = HEIGHT / ROWS;
    private static int amountOfFood = 3;
    private static int amountOfRocks = 6;
    private static int winLength = 50;
    private static int speed = 2;

    public Settings() {}
    public Settings(int rows, int columns) {
        updateRC(rows, columns);
    }

    private void updateRC(int rows, int columns) {
        var size1 = WIDTH / columns;
        var size2 = HEIGHT / rows;
        ROWS = rows;
        COLUMNS = columns;
        if (size2 > size1) {
            blockXSideSize = size1;
            blockYSideSize = size1;
        } else {
            blockXSideSize = size2;
            blockYSideSize = size2;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public int getAmountOfRocks() {
        return amountOfRocks;
    }

    public void setAmountOfRocks(int amount) {
        amountOfRocks = amount;
    }

    public void setSpeed(int newSpeed) {
        if (newSpeed > 3) {
            speed = 3;
        } else if (speed < 0) {
            speed = 0;
        } else {
            speed = newSpeed;
        }
    }

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
        updateRC(ROWS, amount);
    }

    public void setRows(int amount) {
        updateRC(amount, COLUMNS);
    }
}