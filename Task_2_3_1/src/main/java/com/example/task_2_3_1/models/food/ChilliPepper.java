package com.example.task_2_3_1.models.food;

import com.example.task_2_3_1.models.food.Food;
import com.example.task_2_3_1.types.FoodTypes;

public class ChilliPepper extends Food {
    private int change = 0;
    private FoodTypes type = FoodTypes.CHILLIPEPPER;

    public ChilliPepper(String imagePath, int positionX, int positionY) {
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