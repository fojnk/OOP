package com.example.task_2_3_1.models.food;

public class Apple extends Food {
    private int change = 1;
    private int type = 0;

    public Apple(String imagePath, int positionX, int positionY) {
        super(imagePath, positionX, positionY);
    }

    @Override
    public int getChange() {
        return change;
    }

    @Override
    public int getType() {
        return type;
    }
}