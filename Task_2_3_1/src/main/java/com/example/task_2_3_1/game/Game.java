package com.example.task_2_3_1.game;

import com.example.task_2_3_1.controllers.SettingsController;
import com.example.task_2_3_1.models.Field;
import com.example.task_2_3_1.models.Rock;
import com.example.task_2_3_1.models.food.Apple;
import com.example.task_2_3_1.models.food.ChilliPepper;
import com.example.task_2_3_1.models.food.Food;
import com.example.task_2_3_1.models.Snake;
import com.example.task_2_3_1.models.food.GoldApple;
import com.example.task_2_3_1.types.CellType;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game implements Runnable {
    private final Settings settings;
    private final GraphicsContext gc;
    private final Drawer drawer;
    private final Field field;
    private AtomicBoolean gameOver;
    private Thread gameloop;
    private boolean win;


    public Game(Stage primaryStage, Settings settings) {
        primaryStage.setTitle("Snake");
        gameOver = new AtomicBoolean();
        gameOver.set(false);
        win = false;
        this.settings = settings;

        var player = new Snake(6, 6,Direction.DOWN);
        field = new Field(settings.getCOLUMNS(), settings.getROWS(), player, settings.getAmountOfFood(), settings.getAmountOfRocks());
        field.addSnake(player);
        field.cleanField();

        var root = new Group();
        Canvas canvas = new Canvas(settings.getWIDTH(), settings.getHEIGHT());
        root.getChildren().add(canvas);

        var scene = new Scene(root);
        primaryStage.setScene(scene);
        gc = canvas.getGraphicsContext2D();
        drawer = new Drawer(gc, field, settings);
        drawer.initField();
        primaryStage.show();
        scene.setOnKeyPressed(new KeyHandler());
    }

    private void init() throws InterruptedException {
        field.generateFood();
        field.generateRocks();
        drawer.initField();
    }

    public boolean gameIsOver() {
        return gameOver.get();
    }

    @Override
    public void run() {
        try {
            init();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        gameloop = new Thread(() -> {
            long lastTick = System.currentTimeMillis();
            while(!gameOver.get()) {
                var currTime = System.currentTimeMillis();
                var tmp = 330 - settings.getSpeed() * 90L;
                if (field.getPlayer().getSpeedBoost() == 1) {
                    tmp = 50;
                }
                if (currTime - lastTick > tmp) {
                    update();
                    drawer.updateFrame();
                    lastTick = currTime;
                } else {
                    try {
                        Thread.sleep(tmp - (currTime - lastTick));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
            drawer.drawGameOver(win);
        });

        var foodSpawner = new Thread(() -> {
            while(!gameOver.get()) {
                try {
                    field.generateFood();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignore) {
                }
            }
        });

        gameloop.start();
        foodSpawner.start();
    }

    public void stop() throws InterruptedException {
        gameloop.interrupt();
        gameloop.join();
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
            } else if (kCode == KeyCode.ESCAPE || kCode == KeyCode.Q) {
                try {
                    stop();
                } catch (InterruptedException ignore) {
                }
            }
        }
    }

    private void update() {
        field.getSnakes().forEach(Snake::move);
        field.checkCollisions();
        field.deleteSnake();
        updateField();
        checkGameOver();
    }


    public void updateField() {
        field.generateRocks();

        for (var rock : field.getRocks()) {
            field.setVal(rock.getYPos(),rock.getXPos(), CellType.ROCK);
        }

        for (var food : field.getFoodList()) {
            field.setVal(food.getPositionY(), food.getPositionX(), CellType.FOOD);
        }

        for (var snake : field.getSnakes()) {
                var head = snake.getHead();
                var tmp = field.getVal(head.getPositionY(), head.getPositionX());
                switch (tmp) {
                    case FOOD:
                        var food = field.getFoodByPos(head.getPositionY(), head.getPositionX());
                        snake.eatFood(food);
                        field.deleteFood(food);
                        field.setVal(head.getPositionY(), head.getPositionX(), CellType.SNAKE);
                        break;
                    case ROCK, SNAKE:
                        snake.setIsAlive(false);
                        break;
                    default:
                        field.setVal(head.getPositionY(), head.getPositionX(), CellType.SNAKE);
                        break;
                }

                var removed = snake.getRemoved();
                if (removed != null) {
                    field.setVal(removed.getPositionY(), removed.getPositionX(), CellType.FIELD);
                }
        }
    }

    private void checkGameOver() {
        if (field.getSnakes().isEmpty() || field.getPlayer().getSnakeSize() >= settings.getWinLength()) {
            if (field.getPlayer().getSnakeSize() >= settings.getWinLength()) {
                win = true;
            }
            gameOver.set(true);
        }
    }

}