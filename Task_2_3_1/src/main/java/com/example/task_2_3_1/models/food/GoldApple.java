package com.example.task_2_3_1.models.food;

import com.example.task_2_3_1.models.food.Food;

public class GoldApple extends Food {
    private int change = 3;
    private int type = 1;
    public GoldApple(String imagePath, int positionX, int positionY) {
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