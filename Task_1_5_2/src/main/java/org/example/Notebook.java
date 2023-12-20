package org.example;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс для описания записной книжки.
 */
public class Notebook {
    private final List<Note> notes;
    private DateTimeFormatter formatter;

    /**
     * Конструктор для создания книжки со стандартным форматирвоанием.
     *
     * @param notes - заметки
     */
    public Notebook(List<Note> notes) {
        this.notes = notes;
        this.formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                .withZone(ZoneId.systemDefault());
    }

    /**
     * конструктор, создающий книжку по списку заметок.
     *
     * @param notes - заметки
     */
    public Notebook(List<Note> notes, DateTimeFormatter formatter) {
        this.notes = notes;
        this.formatter = formatter;
    }

    /**
     * добавление заметки.
     *
     * @param title       - заголовок
     * @param description - описание
     */
    public void addNote(String title, String description) {
        Note newNote = new Note(title, description, formatter);
        checkTimeZone(newNote);
        notes.add(newNote);
    }

    /**
     * добавление уже существующей заметки.
     *
     * @param note - заметка
     */
    public void addNote(Note note) {
        checkTimeZone(note);
        notes.add(note);
    }

    /**
     * метод для проверки временной зоны.
     *
     * @param note - новая заметка
     */
    private void checkTimeZone(Note note) {
        if (notes.isEmpty()) {
            return;
        }
        var time1 = ZonedDateTime.parse(note.getNoteDate(), formatter);
        var time2 = ZonedDateTime.parse(notes.get(0).getNoteDate(), formatter);
        if (!time2.getZone().equals(time1.getZone())) {
            for (var n : notes) {
                var newTime = ZonedDateTime.parse(n.getNoteDate(), formatter);
                var neT = newTime.withZoneSameInstant(ZoneId.systemDefault())
                        .toLocalDateTime().format(formatter);
                n.setNoteDate(neT);
            }
        }
    }

    /**
     * проверка на наличие заметки.
     *
     * @param note - заметка
     * @return - true or false
     */
    public boolean containsNote(Note note) {
        return notes.contains(note);
    }

    /**
     * удаление заметок.
     *
     * @param title - заголовок
     */
    public void removeNotes(String title) {
        Note newNote = new Note("", "", formatter);
        checkTimeZone(newNote);
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getTitle().equals(title)) {
                notes.remove(notes.get(i--));
            }
        }
    }

    /**
     * метод для получения заметок во временном интервале.
     *
     * @param arguments - аргументы командной строки
     * @return - список заметок
     */
    public List<Note> showNotes(List<String> arguments) {
        Note newNote = new Note("", "", formatter);
        checkTimeZone(newNote);
        List<Note> output = new LinkedList<>();
        if (arguments.size() >= 3) {
            var localFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            var start = LocalDateTime.parse(arguments.get(0), localFormatter);
            var end = LocalDateTime.parse(arguments.get(1), localFormatter);
            ;
            for (var note : notes) {
                var time = LocalDateTime.parse(note.getNoteDate(), formatter);
                if (time.isAfter(start) && time.isBefore(end)) {
                    for (int i = 2; i < arguments.size(); i++) {
                        if (note.getTitle().contains(arguments.get(i))) {
                            output.add(note);
                            break;
                        }
                    }
                }
            }
        } else if (arguments.isEmpty()) {
            output.addAll(notes);
        }
        return output;
    }

    /**
     * получение всех заметок.
     *
     * @return - новый список заметок
     */
    public List<Note> getAllNotes() {
        List<Note> newListOfNotes = new ArrayList<>();
        for (var note : notes) {
            newListOfNotes.add(new Note(note.getNoteDate(),
                    note.getTitle(), note.getDescription()));
        }
        return newListOfNotes;
    }
}