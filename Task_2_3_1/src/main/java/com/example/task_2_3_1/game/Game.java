package com.example.task_2_3_1;

import com.example.task_2_3_1.game.Settings;
import com.example.task_2_3_1.models.Field;
import com.example.task_2_3_1.models.Food;
import com.example.task_2_3_1.models.Snake;
import com.example.task_2_3_1.types.Direction;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Game extends Application {
    private Settings settings;
    private Snake player;
    private GraphicsContext gc;
    private Field field;
    private boolean gameOver;
    private ArrayList<Food> foodList;
    private Timeline timeline;
    private Group root;
    private Stage stage;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        gameOver = false;
        settings = new Settings();
        player = new Snake(6, 6, 1, Direction.DOWN);
        field = new Field(settings.getCOLUMNS(), settings.getROWS());
        field.addSnake( player);
        foodList = new ArrayList<>();

        primaryStage.setTitle("Snake");
        root = new Group();

        Canvas canvas = new Canvas(settings.getWIDTH(), settings.getHEIGHT());
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        gc = canvas.getGraphicsContext2D();

        //initMainMenu(root);
        primaryStage.show();
        scene.setOnKeyPressed(new KeyHandler());

        timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), e -> run()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void switchToGame*

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

    private void startGame() {

    }

    private void exit() {
        timeline.stop();
    }

    public static void main(String[] args) {
        launch();
    }

    private void initMainMenu() {
        Rectangle bg = new Rectangle(settings.getWIDTH(), settings.getHEIGHT());

        Font font = Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50);

        Button btnStart = new Button("Start");
        btnStart.setFont(font);
        btnStart.setOnAction(event -> startGame());

        Button btnExit = new Button("Exit");
        btnExit.setFont(font);
        btnExit.setOnAction(event -> exit());

        VBox vbox = new VBox(50, btnStart, btnExit);
        vbox.setTranslateX(400);
        vbox.setTranslateY(200);

        root.getChildren().addAll(bg, vbox);
    }

    private void run () {
        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("Digital-7", 70));
            gc.fillText("Game Over", settings.getWIDTH() / 3.0, settings.getHEIGHT() / 2.0);
            return;
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
        checkGameOver();
    }

    private void generateFood(LinkedList<Snake> snakes) {
        Random rand = new Random();
        while (foodList.size() < settings.getAmountOfFood()) {
            var goodPosition = true;
            int xPos = rand.nextInt(settings.getCOLUMNS() - 2) + 1;
            int yPos = rand.nextInt(settings.getROWS() - 2) + 1;
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
                foodList.add(new Food("hello", xPos, yPos));
            }
        }
    }

    private void drawFood() {
        gc.setFill(Color.web("AAD999"));
        for (var food : foodList) {
            gc.fillRoundRect(food.getPositionX() * settings.getBlockXSideSize(),
                    food.getPositionY() * settings.getBlockYSideSize(),
                    settings.getBlockXSideSize() - 1, settings.getBlockYSideSize() - 1,
                    20, 20);
        }
    }

    private void eatFood(LinkedList<Snake> snakes) {
        for (var snake : snakes) {
            var head = snake.getHead();
            for (var food : foodList) {
                if (food.getPositionX() == head.getPositionX() && food.getPositionY() == head.getPositionY()) {
                    snake.increaseSnake();
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
        gc.setFill(Color.web("4674E9"));
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