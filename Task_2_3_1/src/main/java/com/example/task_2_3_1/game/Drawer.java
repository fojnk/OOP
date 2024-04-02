package com.example.task_2_3_1.game;

import com.example.task_2_3_1.models.Field;
import com.example.task_2_3_1.models.Snake;
import com.example.task_2_3_1.models.food.Food;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Drawer {
    private Settings settings;
    private final ArrayList<Food> foodList;
    private Field field = null;
    private Snake player;
    private final GraphicsContext gc;

    public Drawer(GraphicsContext gc, ArrayList<Food> foodList, Settings settings, Field field, Snake player) {
        this.gc = gc;
        this.foodList = foodList;
        this.settings = settings;
        this.field = field;
        this.player = player;
    }

    public void drawFrame() {
        drawBackground(gc);
        drawFood(gc);
        for (var snake : field.getSnakes()) {
            drawSnake(gc, snake);
        }

        gc.setFill(Color.GRAY);
        gc.setFont(new Font("Digital-7", 25));
        var score = String.format("Score: %d", player.getSnakeSize());
        gc.fillText(score, 1, settings.getHEIGHT()/20.0);
    }

    private void drawBackground(GraphicsContext gc) {
        for (int i = 0; i < settings.getCOLUMNS(); i ++) {
            for (int j = 0; j < settings.getROWS(); j ++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("AAD751"));
                } else {
                    gc.setFill(Color.web("A2D149"));
                }
                gc.fillRect(i * settings.getBlockYSideSize(), j * settings.getBlockXSideSize(),
                        settings.getBlockXSideSize(), settings.getBlockYSideSize());
            }
        }
    }

    private void drawSnake(GraphicsContext gc, Snake snake) {
        var body = snake.getSnake();
        gc.setFill(Color.web("70706e"));
        gc.fillRoundRect(body.getFirst().getPositionX() * settings.getBlockXSideSize(),
                body.getFirst().getPositionY() * settings.getBlockYSideSize(),
                settings.getBlockXSideSize() - 1, settings.getBlockYSideSize() - 1, 35, 35);

        for (int i = 1; i < body.size(); i++) {
            gc.fillRoundRect(body.get(i).getPositionX() * settings.getBlockXSideSize(),
                    body.get(i).getPositionY() * settings.getBlockYSideSize(),
                    settings.getBlockXSideSize() - 1, settings.getBlockYSideSize() - 1,
                    20, 20);
        }
    }

    private void drawFood(GraphicsContext gc) {
        for (var food : foodList) {
            gc.drawImage(new Image(food.getImagePath()),food.getPositionX() * settings.getBlockXSideSize(),
                    food.getPositionY() * settings.getBlockYSideSize(),
                    settings.getBlockXSideSize() - 1, settings.getBlockYSideSize() - 1);
        }
    }
}