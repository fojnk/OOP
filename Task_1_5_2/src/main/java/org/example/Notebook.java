package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для описания записной книжки.
 */
public class Notebook {
    private final List<Note> notes;

    /**
     * конструктор, создающий книжку по списку заметок.
     *
     * @param notes - заметки
     */
    public Notebook(List<Note> notes) {
        this.notes = notes;
    }

    /**
     * добавление заметки.
     *
     * @param title       - заголовок
     * @param description - описание
     */
    public void addNote(String title, String description) {
        Note newNote = new Note(title, description);
        notes.add(newNote);
    }

    /**
     * добавление уже существующей заметки.
     *
     * @param note - заметка
     */
    public void addNote(Note note) {
        notes.add(note);
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
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getTitle().equals(title)) {
                notes.remove(notes.get(i--));
            }
        }
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