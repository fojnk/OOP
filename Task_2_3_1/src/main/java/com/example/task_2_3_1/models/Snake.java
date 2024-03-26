package com.example.task_2_3_1.models;

import com.example.task_2_3_1.types.Direction;

import java.util.LinkedList;

public class Snake {
    private final LinkedList<SnakePart> snake;
    private boolean isAlive;
    private Direction direction;
    private int speed;
    private int xChange;
    private int yChange;
    private boolean increase;


    public Snake(int startPositionX, int startPositionY, int speed, Direction direction) {
        snake = new LinkedList<>();
        snake.addFirst(new SnakePart(startPositionX, startPositionY));
        isAlive = true;
        this.speed = speed;
        this.increase = false;
        setDirection(direction);
    }

    public LinkedList<SnakePart> getSnake() {
        var snake = new LinkedList<SnakePart>();
        for (var part : this.snake) {
            snake.add(new SnakePart(part.getPositionX(), part.getPositionY()));
        }
        return snake;
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

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int newSpeed) {
        if (newSpeed > 0) {
            this.speed = newSpeed;
        }
    }

    public void increaseSnake() {
        this.increase = true;
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
        if (!this.increase) {
            snake.pollLast();
        } else {
            this.increase = false;
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