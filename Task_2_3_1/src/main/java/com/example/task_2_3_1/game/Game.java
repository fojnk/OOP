package com.example.task_2_3_1.game;

import com.example.task_2_3_1.models.Field;
import com.example.task_2_3_1.models.food.Apple;
import com.example.task_2_3_1.models.food.ChilliPepper;
import com.example.task_2_3_1.models.food.Food;
import com.example.task_2_3_1.models.Snake;
import com.example.task_2_3_1.models.food.GoldApple;
import com.example.task_2_3_1.types.Direction;
import com.example.task_2_3_1.types.FoodTypes;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Game {
    private final Settings settings;
    private final GraphicsContext gc;
    private final Drawer drawer;
    private final Field field;
    private boolean gameOver;
    private final ArrayList<Food> foodList;
    private Timeline timeline;
    private boolean speedBoost;

    public Game(Stage primaryStage, Settings settings) throws IOException {
        primaryStage.setTitle("Snake");
        gameOver = false;
        speedBoost = false;
        this.settings = settings;

        var player = new Snake(6, 6,Direction.DOWN);
        field = new Field(settings.getCOLUMNS(), settings.getROWS(), player);
        field.addSnake(player);

        foodList = new ArrayList<>();
        var root = new Group();
        Canvas canvas = new Canvas(settings.getWIDTH(), settings.getHEIGHT());
        root.getChildren().add(canvas);

        var scene = new Scene(root);
        primaryStage.setScene(scene);
        gc = canvas.getGraphicsContext2D();
        drawer = new Drawer(gc, foodList, settings, field, player);

        primaryStage.show();
        scene.setOnKeyPressed(new KeyHandler());
    }

    public void startGame() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), e -> run()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void stopGame() {
        timeline.stop();
    }

    public class KeyHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            var kCode = event.getCode();
            if (kCode == KeyCode.RIGHT || kCode == KeyCode.D) {
                field.getPlayer().setDirection(Direction.RIGHT);
            } else if (kCode == KeyCode.LEFT || kCode == KeyCode.A) {
                field.getPlayer().setDirection(Direction.LEFT);
            } else if (kCode == KeyCode.UP || kCode == KeyCode.W) {
                field.getPlayer().setDirection(Direction.UP);
            } else if (kCode == KeyCode.DOWN || kCode == KeyCode.S) {
                field.getPlayer().setDirection(Direction.DOWN);
            }
        }
    }

    private void run () {
        if (gameOver) {
            gc.setFill(Color.GRAY);
            gc.setFont(new Font("Digital-7", 90));
            gc.fillText("Game Over", settings.getWIDTH() / 4.0, settings.getHEIGHT() / 2.0);
            return;
        }
        if (speedBoost) {
            timeline.setDelay(Duration.seconds(0.002));
        } else if (timeline.getDelay() != Duration.seconds(0.2)) {
            timeline.setDelay(Duration.seconds(0.2));
        }

        generateFood(field.getSnakes());
        field.getSnakes().forEach(Snake::move);
        eatFood(field.getSnakes());
        field.checkCollisions();
        drawer.drawFrame();

        checkGameOver();
    }

    private void generateFood(LinkedList<Snake> snakes) {
        Random rand = new Random();
        while (foodList.size() < settings.getAmountOfFood()) {
            var goodPosition = true;
            int xPos = rand.nextInt(settings.getCOLUMNS() - 2) + 1;
            int yPos = rand.nextInt(settings.getROWS() - 2) + 1;
            int typeOfFood = rand.nextInt(3);
            for (var snake : snakes) {
                var body = snake.getSnake();
                for (var part : body) {
                    if (part.getPositionX() == xPos && part.getPositionY() == yPos) {
                        goodPosition = false;
                        break;
                    }
                }
                if (!goodPosition) {
                    break;
                }
            }

            if (goodPosition) {
                if (typeOfFood == 0) {
                    foodList.add(new Apple("images/apple.png", xPos, yPos));
                } else if (typeOfFood == 1) {
                    foodList.add(new GoldApple("images/goldApple.png", xPos, yPos));
                } else {
                    foodList.add(new ChilliPepper("images/chilliPaper.png", xPos, yPos));
                }
            }
        }
    }

    private void eatFood(LinkedList<Snake> snakes) {
        for (var snake : snakes) {
            var head = snake.getHead();
            for (var food : foodList) {
                if (food.getPositionX() == head.getPositionX() && food.getPositionY() == head.getPositionY()) {
                    snake.increaseSnake(food.getChange());
                    speedBoost = food.getType() == FoodTypes.CHILLIPEPPER;
                    foodList.remove(food);
                    break;
                }
            }
        }
    }

    private void checkGameOver() {
        if (!field.getPlayer().getIsAlive()) {
            gameOver = true;
        }
    }

}