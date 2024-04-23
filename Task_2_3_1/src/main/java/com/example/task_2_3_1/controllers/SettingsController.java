package com.example.task_2_3_1.controllers;

import com.example.task_2_3_1.game.Game;
import com.example.task_2_3_1.game.Settings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    private Settings settings;
    @FXML
    private Slider amountOfFood;

    @FXML
    private Slider amountOfRocks;

    @FXML
    private Slider speed;

    @FXML
    private Slider winLength;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        settings = new Settings();
        amountOfFood.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                settings.setAmountOfFood((int) amountOfFood.getValue());
            }
        });

        winLength.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                settings.setWinLength((int) winLength.getValue());
            }
        });

        speed.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                settings.setSpeed((int) speed.getValue());
            }
        });

        amountOfRocks.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                settings.setAmountOfRocks((int) amountOfRocks.getValue());
            }
        });
    }

    public void startGame(ActionEvent event) throws InterruptedException {
        var stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        var game = new Game(stage, settings);
        Thread t = new Thread(game);
        t.start();
        t.join();
    }
}