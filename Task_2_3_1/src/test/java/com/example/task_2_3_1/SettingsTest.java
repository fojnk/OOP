package com.example.task_2_3_1;

import com.example.task_2_3_1.game.Settings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SettingsTest {
    @Test
    public void simpleTest() {
        var settings = new Settings();
        Assertions.assertEquals(settings.getWIDTH(), 800);
        Assertions.assertEquals(settings.getHEIGHT(), 500);
        Assertions.assertEquals(settings.getCOLUMNS(), 40);
        Assertions.assertEquals(settings.getROWS(), 25);
        Assertions.assertEquals(settings.getAmountOfFood(), 3);
        Assertions.assertEquals(settings.getWinLength(), 50);
        settings.setWinLength(100);
        Assertions.assertEquals(settings.getWinLength(), 100);
        settings.setWidth(1000);
        Assertions.assertEquals(settings.getWIDTH(), 1000);
        settings.setHeight(1000);
        Assertions.assertEquals(settings.getHEIGHT(), 1000);
        settings.setRows(50);
        settings.setColumns(50);
        Assertions.assertEquals(settings.getCOLUMNS(), 50);
        Assertions.assertEquals(settings.getROWS(), 50);
        settings.setAmountOfFood(10);
        Assertions.assertEquals(settings.getAmountOfFood(), 10);
    }
}