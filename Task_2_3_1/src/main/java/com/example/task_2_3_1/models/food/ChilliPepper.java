package com.example.task_2_3_1.models.food;

import com.example.task_2_3_1.models.food.Food;

public class ChilliPepper extends Food {
    private int change = 0;
    private int type = 2;

    public ChilliPepper(String imagePath, int positionX, int positionY) {
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