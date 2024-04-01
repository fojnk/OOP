package com.example.task_2_3_1.game;

import com.example.task_2_3_1.models.Field;
import com.example.task_2_3_1.models.food.Apple;
import com.example.task_2_3_1.models.food.ChilliPepper;
import com.example.task_2_3_1.models.food.Food;
import com.example.task_2_3_1.models.Snake;
import com.example.task_2_3_1.models.food.GoldApple;
import com.example.task_2_3_1.types.Direction;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
    private final Snake player;
    private final GraphicsContext gc;
    private final Field field;
    private boolean gameOver;
    private final ArrayList<Food> foodList;
    private Timeline timeline;
    private Group root;
    private Scene scene;
    private boolean speedBoost;

    public Game(Stage primaryStage, Settings settings) throws IOException {
        gameOver = false;
        this.settings = settings;
        player = new Snake(6, 6, 1, Direction.DOWN);
        field = new Field(settings.getCOLUMNS(), settings.getROWS());
        field.addSnake(player);
        this.speedBoost = false;
        foodList = new ArrayList<>();
        primaryStage.setTitle("Snake");
        root = new Group();

        Canvas canvas = new Canvas(settings.getWIDTH(), settings.getHEIGHT());
        root.getChildren().add(canvas);
        scene = new Scene(root);
        primaryStage.setScene(scene);
        gc = canvas.getGraphicsContext2D();

        primaryStage.show();
        scene.setOnKeyPressed(new KeyHandler());
    }

    public Scene getScene() {
        return this.scene;
    }

    public void startGame() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.25), e -> run()));
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
                player.setDirection(Direction.RIGHT);
            } else if (kCode == KeyCode.LEFT || kCode == KeyCode.A) {
                player.setDirection(Direction.LEFT);
            } else if (kCode == KeyCode.UP || kCode == KeyCode.W) {
                player.setDirection(Direction.UP);
            } else if (kCode == KeyCode.DOWN || kCode == KeyCode.S) {
                player.setDirection(Direction.DOWN);
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
        drawBackground(gc);
        field.getSnakes().forEach(Snake::move);
        eatFood(field.getSnakes());
        field.checkCollisions();
        for (var snake : field.getSnakes()) {
            drawSnake(gc, snake);
        }
        drawFood();

        gc.setFill(Color.GRAY);
        gc.setFont(new Font("Digital-7", 25));
        var score = String.format("Score: %d", player.getSnakeSize());
        gc.fillText(score, 1, settings.getHEIGHT()/20.0);

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

    private void drawFood() {
        for (var food : foodList) {
            gc.drawImage(new Image(food.getImagePath()),food.getPositionX() * settings.getBlockXSideSize(),
                    food.getPositionY() * settings.getBlockYSideSize(),
                    settings.getBlockXSideSize() - 1, settings.getBlockYSideSize() - 1);
        }
    }

    private void eatFood(LinkedList<Snake> snakes) {
        for (var snake : snakes) {
            var head = snake.getHead();
            for (var food : foodList) {
                if (food.getPositionX() == head.getPositionX() && food.getPositionY() == head.getPositionY()) {
                    snake.increaseSnake(food.getChange());
                    speedBoost = food.getType() == 1;
                    foodList.remove(food);
                    break;
                }
            }
        }
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

    private void checkGameOver() {
        if (!player.getIsAlive()) {
            gameOver = true;
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

}