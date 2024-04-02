package com.example.task_2_3_1.models;

import com.example.task_2_3_1.types.Direction;

import java.util.LinkedList;

public class Snake {
    private final LinkedList<SnakePart> snake;
    private int size;
    private boolean isAlive;
    private Direction direction;
    private int speed = 1;
    private int xChange;
    private int yChange;
    private int increase;


    public Snake(int startPositionX, int startPositionY, Direction direction) {
        snake = new LinkedList<>();
        snake.addFirst(new SnakePart(startPositionX, startPositionY));
        isAlive = true;
        this.increase = 0;
        setDirection(direction);
    }

    public LinkedList<SnakePart> getSnake() {
        var snake = new LinkedList<SnakePart>();
        for (var part : this.snake) {
            snake.add(new SnakePart(part.getPositionX(), part.getPositionY()));
        }
        return snake;
    }

    public int getSnakeSize() {
        return this.size;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public SnakePart getHead() {
        return snake.getFirst();
    }

    public SnakePart getTail() {
        return snake.getLast();
    }

    public void increaseSnake(int change) {
        this.increase = change;
    }

    public void setDirection(Direction direction) {
        switch (direction) {
            case UP:
                if (this.direction != Direction.DOWN) {
                    this.direction = direction;
                    yChange = -speed;
                    xChange = 0;
                }
                break;
            case DOWN:
                if (this.direction != Direction.UP) {
                    this.direction = direction;
                    yChange = speed;
                    xChange = 0;
                }
                break;
            case LEFT:
                if (this.direction != Direction.RIGHT) {
                    this.direction = direction;
                    yChange = 0;
                    xChange = -speed;
                }
                break;
            case RIGHT:
                if (this.direction != Direction.LEFT) {
                    this.direction = direction;
                    yChange = 0;
                    xChange = speed;
                }
        }
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void move() {
        var newHead = new SnakePart(snake.getFirst().getPositionX() + xChange,
                snake.getFirst().getPositionY() + yChange);
        snake.addFirst(newHead);
        if (this.increase == 0) {
            snake.pollLast();
        } else {
            this.increase--;
            size++;
        }
        checkBodyCollision();
    }

    private void checkBodyCollision() {
        var head = this.snake.getFirst();
        for (var part : this.snake) {
            if (part != head) {
                if (head.getPositionX() == part.getPositionX() &&
                        head.getPositionY() == part.getPositionY()) {
                    isAlive = false;
                    break;
                }
            }
        }
    }
}