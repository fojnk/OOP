package com.example.task_2_3_1.models;

import com.example.task_2_3_1.models.food.Food;
import com.example.task_2_3_1.types.Direction;
import com.example.task_2_3_1.types.FoodTypes;

import java.util.ArrayList;
import java.util.LinkedList;

public class Snake {
    private final LinkedList<SnakePart> snake;
    private int size;
    private boolean isAlive;
    private Direction direction;
    private int xChange;
    private int yChange;
    private int increase;
    private int speedBoost;

    private SnakePart removed;


    public Snake(int startPositionX, int startPositionY, Direction direction) {
        snake = new LinkedList<>();
        snake.addFirst(new SnakePart(startPositionX, startPositionY));
        isAlive = true;
        this.increase = 0;
        setDirection(direction);
        speedBoost = 0;
        removed = null;
    }

    public SnakePart getRemoved() {
        return this.removed;
    }

    public int getSpeedBoost() {
        return  speedBoost;
    }

    public LinkedList<SnakePart> getSnake() {
        return this.snake;
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
        this.increase += change;
    }

    public void setDirection(Direction direction) {
        switch (direction) {
            case UP:
                if (this.direction != Direction.DOWN) {
                    this.direction = direction;
                    yChange = -1;
                    xChange = 0;
                }
                break;
            case DOWN:
                if (this.direction != Direction.UP) {
                    this.direction = direction;
                    yChange = 1;
                    xChange = 0;
                }
                break;
            case LEFT:
                if (this.direction != Direction.RIGHT) {
                    this.direction = direction;
                    yChange = 0;
                    xChange = -1;
                }
                break;
            case RIGHT:
                if (this.direction != Direction.LEFT) {
                    this.direction = direction;
                    yChange = 0;
                    xChange = 1;
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
            removed = snake.pollLast();
        } else {
            removed = null;
            this.increase--;
            size++;
        }
    }

    public void eatFood(Food food) {
            this.increaseSnake(food.getChange());
            if (food.getType() == FoodTypes.CHILLIPEPPER) {
                speedBoost = 1;
            } else {
                speedBoost = 0;
            }
    }
}
