package com.example.task_2_3_1.controllers;

import com.example.task_2_3_1.game.Game;
import com.example.task_2_3_1.game.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Game game = null;
    private Settings settings;

    public void switchToSettingsScreen(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(
                SceneController.class.getResource("/com/example/task_2_3_1/screens/Settings.fxml"));
        AnchorPane root = fxmlLoader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loadSettingsScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                SceneController.class.getResource("/com/example/task_2_3_1/screens/Settings.fxml"));
        AnchorPane root = fxmlLoader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void startGame(ActionEvent event) throws InterruptedException, IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        settings = new Settings();
        game = new Game(stage, settings);
        Thread t = new Thread(game);
        t.start();
        t.join();
    }
}