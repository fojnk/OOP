package org.example;

import java.util.ArrayList;
import java.util.List;

public class Notebook {
    private final List<Note> notes;

    public Notebook(List<Note> notes) {
        this.notes = notes;
    }

    public void addNote(String title, String description) {
        Note newNote = new Note(title, description);
        notes.add(newNote);
    }

    public void removeNotes(String title) {
        for (int i = 0; i < notes.size(); i ++) {
            if (notes.get(i).getTitle().equals(title)){
                notes.remove(notes.get(i--));
            }
        }
    }

    public List<Note> getAllNotes() {
        List<Note> newListOfNotes = new ArrayList<>();
        for (var note: notes) {
            newListOfNotes.add(new Note(note.getNoteDate(), note.getTitle(), note.getDescription()));
        }
        return newListOfNotes;
    }
}