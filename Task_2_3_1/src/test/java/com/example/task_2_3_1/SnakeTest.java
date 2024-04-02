package com.example.task_2_3_1;

import com.example.task_2_3_1.models.Snake;
import com.example.task_2_3_1.types.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SnakeTest {
    @Test
    public void simpleTest() {
        var snake = new Snake(1, 1, Direction.RIGHT);
        Assertions.assertEquals(snake.getDirection(), Direction.RIGHT);
        Assertions.assertEquals(snake.getSnakeSize(), 0);
        Assertions.assertEquals(snake.getTail(), snake.getHead());
        snake.increaseSnake(1);
        snake.move();
        Assertions.assertNotEquals(snake.getTail(), snake.getHead());
        Assertions.assertEquals(snake.getHead().getPositionX(), 2);
        Assertions.assertEquals(snake.getHead().getPositionY(), 1);
        Assertions.assertEquals(snake.getTail().getPositionX(), 1);
        Assertions.assertEquals(snake.getTail().getPositionY(), 1);
        Assertions.assertTrue(snake.getIsAlive());
    }
}