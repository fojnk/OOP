package com.example.task_2_3_1;

import com.example.task_2_3_1.models.Field;
import com.example.task_2_3_1.models.Snake;
import com.example.task_2_3_1.types.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FieldTest {
    @Test
    public void simpleFieldTest() {
        var field = new Field(10, 10);
        Assertions.assertEquals(field.getXSize(), 10);
        Assertions.assertEquals(field.getYSize(), 10);
        var snake = new Snake(1, 1, Direction.RIGHT);
        field.addSnake(snake);
        Assertions.assertEquals(field.getSnakes().getLast(), snake);
        Assertions.assertFalse(field.checkCollisions());
        snake.setIsAlive(false);
        field.deleteSnake();
        Assertions.assertTrue(field.getSnakes().isEmpty());
    }
}