package com.example.task_2_3_1.models;

import java.util.LinkedList;

public class Field {
    private final int xSize;
    private final int ySize;
    private final LinkedList<Snake> snakes;
    private final Snake player;

    public Field(int xSize, int ySize, Snake player) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.player = player;
        this.snakes = new LinkedList<>();
    }

    public int getXSize() {
        return this.xSize;
    }

    public int getYSize() {
        return this.ySize;
    }

    public void addSnake(Snake snake) {
        this.snakes.add(snake);
    }

    public LinkedList<Snake> getSnakes() {
        return this.snakes;
    }

    public Snake getPlayer() { return this.player; }

    public boolean checkCollisions() {
        var result = false;
        for (var snake : snakes) {
            var head = snake.getHead();
            if (head.getPositionX() < 0 || head.getPositionX() > this.xSize ||
                head.getPositionY() < 0 || head.getPositionY() > this.ySize) {
                snake.setIsAlive(false);
                result = true;
            }
        }
        return result;
    }

    public void deleteSnake() {
        snakes.removeIf(snake -> !snake.getIsAlive());
    }
}