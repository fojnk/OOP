package org.example;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.LocalDateTime;

public class Note {
    private String noteDate;
    private String title;
    private String description;

    public Note(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        this.noteDate = LocalDateTime.now().format(formatter);
        this.title = "";
        this.description = "";
    }

    public Note(String title, String description) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        this.noteDate = LocalDateTime.now().format(formatter);
        this.title = title;
        this.description = description;
    }

    public Note(String date, String title, String description) {
        this.noteDate = date;
        this.title = title;
        this.description = description;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}