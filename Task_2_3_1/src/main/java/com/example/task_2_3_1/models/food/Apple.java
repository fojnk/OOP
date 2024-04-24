package com.example.task_2_3_1.models.food;

import com.example.task_2_3_1.types.FoodTypes;

public class Apple extends Food {
    private int change = 1;
    private FoodTypes type = FoodTypes.APPLE;

    public Apple(String imagePath, int positionX, int positionY) {
        super(imagePath, positionX, positionY);
    }

    @Override
    public int getChange() {
        return change;
    }

    @Override
    public FoodTypes getType() {
        return type;
    }
}