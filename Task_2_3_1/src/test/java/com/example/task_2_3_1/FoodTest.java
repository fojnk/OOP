package com.example.task_2_3_1;

import com.example.task_2_3_1.models.food.Apple;
import com.example.task_2_3_1.models.food.ChilliPepper;
import com.example.task_2_3_1.models.food.GoldApple;
import com.example.task_2_3_1.types.FoodTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FoodTest {
    @Test
    public void simpleCreationTest() {
        var apple = new Apple("image1", 1,1);
        var goldApple = new GoldApple("image2", 2, 2);
        var chilliPepper = new ChilliPepper("image3", 3, 3);

        Assertions.assertEquals(apple.getChange(), 1);
        Assertions.assertEquals(goldApple.getChange(), 3);
        Assertions.assertEquals(chilliPepper.getChange(), 0);

        Assertions.assertEquals(apple.getPositionX(), 1);
        Assertions.assertEquals(apple.getPositionY(), 1);

        Assertions.assertEquals(apple.getType(), FoodTypes.APPLE);
        Assertions.assertEquals(goldApple.getType(), FoodTypes.GOLDAPPLE);
        Assertions.assertEquals(chilliPepper.getType(), FoodTypes.CHILLIPEPPER);
        Assertions.assertEquals(apple.getImagePath(), "image1");
        Assertions.assertEquals(goldApple.getImagePath(), "image2");
        Assertions.assertEquals(chilliPepper.getImagePath(), "image3");
    }
}