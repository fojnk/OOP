package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс для описания заметок.
 */
public class Note {
    private String noteDate;
    private String title;
    private String description;

    /**
     * Пустой конструктор заметки.
     */
    public Note() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
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