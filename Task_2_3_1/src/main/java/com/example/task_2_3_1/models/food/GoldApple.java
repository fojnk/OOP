package com.example.task_2_3_1.models.food;

import com.example.task_2_3_1.models.food.Food;
import com.example.task_2_3_1.types.FoodTypes;

public class GoldApple extends Food {
    private int change = 3;
    private FoodTypes type = FoodTypes.GOLDAPPLE;
    public GoldApple(String imagePath, int positionX, int positionY) {
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