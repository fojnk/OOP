package com.example.task_2_3_1.models;

import com.example.task_2_3_1.models.food.Apple;
import com.example.task_2_3_1.models.food.ChilliPepper;
import com.example.task_2_3_1.models.food.Food;
import com.example.task_2_3_1.models.food.GoldApple;
import com.example.task_2_3_1.types.CellType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Field {
    private final int xSize;
    private final int ySize;
    private final int amountOfFood;
    private final int amountOfRocks;
    private final LinkedList<Snake> snakes;
    private final Snake player;
    private final ArrayList<Food> foodList;
    private final ArrayList<Rock> rocks;
    private final CellType[][] matrix;
    private boolean foodChanged;
    private ArrayList<CellCoordinates> emptyBlocksList;

    public Field(int xSize, int ySize, Snake player, int amountOfFood, int amountOfRocks) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.player = player;
        this.amountOfFood = amountOfFood;
        this.amountOfRocks = amountOfRocks;
        this.snakes = new LinkedList<>();
        matrix = new CellType[ySize][xSize];
        foodList = new ArrayList<>();
        rocks = new ArrayList<>();
        foodChanged = false;
        emptyBlocksList = new ArrayList<>();
    }

    public boolean getFoodChanged() {
        return this.foodChanged;
    }

    public void setFoodChanged(boolean changed) {
        this.foodChanged = changed;
    }
    public CellType getVal(int yPos, int xPos) {
        return matrix[yPos][xPos];
    }

    public void setVal(int yPos, int xPos, CellType type) {
        matrix[yPos][xPos] = type;
    }

    public int getXSize() {
        return this.xSize;
    }

    public Food getFoodByPos(int yPos, int xPos) {
        for (var food : foodList) {
            if (food.getPositionY() == yPos && food.getPositionX() == xPos) {
                return food;
            }
        }
        return null;
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
            if (head.getPositionX() < 0 || head.getPositionX() >= this.xSize ||
                head.getPositionY() < 0 || head.getPositionY() >= this.ySize) {
                snake.setIsAlive(false);
                result = true;
            }
        }
        return result;
    }

    public ArrayList<Food> getFoodList() {
        return this.foodList;
    }

    public void deleteFood(Food food) {
        foodList.remove(food);
    }

    public ArrayList<Rock> getRocks() {
        return this.rocks;
    }

    public void deleteSnake() {
        snakes.removeIf(snake -> !snake.getIsAlive());
    }

    public void generateFood() {
        if (foodList.size() < amountOfFood) {
            foodChanged = true;
        }
        Random rand = new Random();
        if (foodList.size() < amountOfFood) {
            var goodPosition = true;
            int yPos = rand.nextInt(ySize - 2) + 1;
            int xPos = rand.nextInt(xSize - 2) + 1;
            int typeOfFood = rand.nextInt(3);
            goodPosition = checkPosition(yPos, xPos);

            if (goodPosition) {
                if (typeOfFood == 0) {
                    foodList.add(new Apple("images/apple.png", xPos, yPos));
                } else if (typeOfFood == 1) {
                    foodList.add(new GoldApple("images/goldApple.png", xPos, yPos));
                } else {
                    foodList.add(new ChilliPepper("images/chilliPaper.png", xPos, yPos));
                }
            }
        }
    }

    private boolean checkPosition(int yPos, int xPos) {
        var goodPosition = true;
        if (xPos < 0 || xPos >= xSize || yPos < 0 || yPos >= ySize) return false;

        if (matrix[yPos][xPos] != CellType.FIELD) {
            goodPosition = false;
        }
        return goodPosition;
    }

    public void generateRocks() {
        Random rand = new Random();

        while (rocks.size() < amountOfRocks) {
            var goodPosition = true;
            int yPos = rand.nextInt(ySize - 2) + 1;
            int xPos = rand.nextInt(xSize - 2) + 1;
            goodPosition = checkPosition(yPos, xPos);

            if (goodPosition) {
                rocks.add(new Rock(xPos, yPos, "images/rock.png"));
                if (rocks.size() + 2 < amountOfRocks) {
                    if (rocks.size() % 2 == 0) {
                        var res = checkPosition(yPos, xPos + 1);
                        if (res) {
                            rocks.add(new Rock(xPos + 1, yPos, "images/rock.png"));
                        }

                        res = checkPosition(yPos, xPos - 1);
                        if (res) {
                            rocks.add(new Rock(xPos - 1, yPos, "images/rock.png"));
                        }
                    } else {
                        var res = checkPosition(yPos + 1, xPos);
                        if (res) {
                            rocks.add(new Rock(xPos, yPos + 1, "images/rock.png"));
                        }

                        res = checkPosition(yPos - 1, xPos);
                        if (res) {
                            rocks.add(new Rock(xPos, yPos - 1, "images/rock.png"));

                        }
                    }
                }
            }
        }
    }

    public void cleanField() {
        for (int i = 0; i < ySize; i ++) {
            for (int j = 0; j < xSize; j ++) {
                matrix[i][j] = CellType.FIELD;
            }
        }
    }
}