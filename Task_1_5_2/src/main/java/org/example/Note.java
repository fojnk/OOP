package org.example;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Класс для описания заметок.
 */
public class Note {
    private DateTimeFormatter formatter;
    private String noteDate;
    private String title;
    private String description;

    /**
     * Пустой конструктор заметки.
     */
    public Note() {
        this.formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm z")
                .withZone(ZoneId.systemDefault());
        this.noteDate = LocalDateTime.now().format(formatter);
        this.title = "";
        this.description = "";
    }

    /**
     * Конструктор с автодатой.
     *
     * @param title       - название
     * @param description - описание
     */
    public Note(String title, String description) {
        this.formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm z")
                .withZone(ZoneId.systemDefault());
        this.noteDate = LocalDateTime.now().format(formatter);
        this.title = title;
        this.description = description;
    }

    /**
     * метод для создания заметок с кастомных форматированием.
     *
     * @param title       = заголовок
     * @param description - описание
     * @param formatter   - форматирвоание
     */
    public Note(String title, String description, DateTimeFormatter formatter) {
        this.formatter = formatter;
        this.noteDate = LocalDateTime.now().format(formatter);
        this.title = title;
        this.description = description;
    }

    /**
     * конструктор с заданной датой.
     *
     * @param date        - дата
     * @param title       - заголовок
     * @param description - описание
     */
    public Note(String date, String title, String description) {
        this.noteDate = date;
        this.title = title;
        this.description = description;
    }

    /**
     * получение даты.
     *
     * @return - дата в виде строки
     */
    public String getNoteDate() {
        return noteDate;
    }

    /**
     * установка даты.
     *
     * @param noteDate - новая дата
     */
    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    /**
     * получение заголовка.
     *
     * @return - заголовок
     */
    public String getTitle() {
        return title;
    }

    /**
     * установка загловка.
     *
     * @param title - новый заголовок
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * получение описания.
     *
     * @return - строка
     */
    public String getDescription() {
        return description;
    }

    /**
     * установка описания.
     *
     * @param description - новое описание
     */
    public void setDescription(String description) {
        this.description = description;
    }
}