package com.example.task_2_3_1.game;

import com.example.task_2_3_1.models.Field;
import com.example.task_2_3_1.models.Snake;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Drawer {
    private final Settings settings;
    private final Field field;

    private final GraphicsContext gc;

    public Drawer(GraphicsContext gc, Field field, Settings settings) {
        this.gc = gc;
        this.field = field;
        this.settings = settings;
    }

    public void initField() {
        drawBackground();
        drawFood();
        drawRocks();
    }

    public void updateFrame() {
        if (!field.getNewFoodList().isEmpty()) {
            drawFood();
        }
        for (var snake : field.getSnakes()) {
            var head = snake.getHead();
            gc.setFill(Color.web("70706e"));
            gc.fillRoundRect(head.getPositionX() * settings.getBlockXSideSize(),
                    head.getPositionY() * settings.getBlockYSideSize(),
                    settings.getBlockXSideSize() - 1,
                    settings.getBlockYSideSize() - 1, 35, 35);
            var removed = snake.getRemoved();
            //drawSnake(snake);
            if (removed != null) {
                    if ((removed.getPositionX() + removed.getPositionY()) % 2 == 0) {
                        gc.setFill(Color.web("AAD751"));
                    } else {
                        gc.setFill(Color.web("A2D149"));
                    }
                    gc.fillRect(removed.getPositionX() * settings.getBlockXSideSize(),
                            removed.getPositionY() * settings.getBlockYSideSize(),
                            settings.getBlockXSideSize(), settings.getBlockYSideSize());
                }
            }
        drawScore();
    }

    private void drawScore() {
        gc.setFill(Color.web("AAD751"));
        gc.fillRect( 0,
                0,
                5 * 30, 2 * 30);
        gc.setFill(Color.GRAY);
        gc.setFont(new Font("Digital-7", 25));
        var score = String.format("Score: %d", field.getPlayer().getSnakeSize());
        gc.fillText(score, 1, settings.getHEIGHT()/20.0);
    }

    private void drawSnake(Snake snake) {
        var body = snake.getSnake();
        gc.setFill(Color.web("70706e"));
        gc.fillRoundRect(body.getFirst().getPositionX() * settings.getBlockXSideSize(),
                body.getFirst().getPositionY() * settings.getBlockYSideSize(),
                settings.getBlockXSideSize() - 1, settings.getBlockYSideSize() - 1, 35, 35);

        for (int i = 1; i < body.size(); i++) {
            gc.fillRect(body.get(i).getPositionX() * settings.getBlockXSideSize(),
                    body.get(i).getPositionY() * settings.getBlockYSideSize(),
                    settings.getBlockXSideSize() - 1, settings.getBlockYSideSize() - 1);
        }
    }

    public void drawBackground() {
        for (int j = 0; j < field.getYSize(); j ++) {
            for (int i = 0; i < field.getXSize(); i ++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("AAD751"));
                } else {
                    gc.setFill(Color.web("A2D149"));
                }
                gc.fillRect(i * settings.getBlockXSideSize(), j * settings.getBlockYSideSize(),
                        settings.getBlockXSideSize(), settings.getBlockYSideSize());
            }
        }
    }

    public void drawFood() {
        for (var food : field.getNewFoodList()) {
            gc.drawImage(new Image(food.getImagePath()),food.getPositionX() * settings.getBlockXSideSize(),
                    food.getPositionY() * settings.getBlockYSideSize(),
                    settings.getBlockXSideSize() - 1, settings.getBlockYSideSize() - 1);
        }
        field.clearNewFoodList();
    }


    public void drawRocks() {
        for (var rock : field.getRocks()) {
            gc.drawImage(new Image(rock.getImagePath()), rock.getXPos() * settings.getBlockXSideSize(),
                    rock.getYPos() * settings.getBlockYSideSize(),
                    settings.getBlockXSideSize() - 1, settings.getBlockYSideSize() - 1);
        }
    }

    public void drawGameOver(boolean win) {
        if (win) {
            gc.setFill(Color.GREEN);
            gc.setFont(new Font("Digital-7", 90));
            gc.fillText("YOU WIN", settings.getWIDTH() / 4.0, settings.getHEIGHT() / 2.0);
        } else {
            gc.setFill(Color.RED);
            gc.setFont(new Font("Digital-7", 90));
            gc.fillText("YOU LOSE", settings.getWIDTH() / 4.0, settings.getHEIGHT() / 2.0);
        }
    }
}