package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class NotebookTest {

    @Test
    public void addTest() {
        List<Note> list = new LinkedList<>();
        var notebook = new Notebook(list);
        var note1 = new Note("hello", "jdfasljlfk");
        var note2 = new Note("some text", "fdjsaklj");
        Assertions.assertFalse(notebook.containsNote(note1));
        notebook.addNote(note1);
        Assertions.assertTrue(notebook.containsNote(note1));
        notebook.addNote(note2);
        Assertions.assertTrue(notebook.containsNote(note2));
        notebook.addNote("newNote", "some text");
        boolean flag = false;
        for (var note: notebook.getAllNotes()) {
            if (note.getTitle().equals("newNote")) {
                flag = true;
                break;
            }
        }
        Assertions.assertTrue(flag);
    }

    @Test
    public void removeTest() {
        List<Note> list = new LinkedList<>();
        var notebook = new Notebook(list);
        var note1 = new Note("hello", "jdfasljlfk");
        var note2 = new Note("some text", "fdjsaklj");
        Assertions.assertFalse(notebook.containsNote(note1));
        notebook.addNote(note1);
        Assertions.assertTrue(notebook.containsNote(note1));
        notebook.addNote(note2);
        Assertions.assertTrue(notebook.containsNote(note2));
        notebook.removeNotes("hello");
        Assertions.assertFalse(notebook.containsNote(note1));
        notebook.removeNotes("some text");
        Assertions.assertFalse(notebook.containsNote(note2));
    }
}