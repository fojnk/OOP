package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Класс для тестирования книжки с заметками.
 */
public class NotebookTest {

    /**
     * проверка добалвения заметок.
     */
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
        for (var note : notebook.getAllNotes()) {
            if (note.getTitle().equals("newNote")) {
                flag = true;
                break;
            }
        }
        Assertions.assertTrue(flag);
    }

    /**
     * проверка удаления заметок.
     */
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

    @Test
    public void showTest() {
        List<Note> list = new LinkedList<>();
        var notebook = new Notebook(list);
        var startTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        var note1 = new Note("hello", "jdfasljlfk");
        var note2 = new Note("some text", "fdjsaklj");
        var endTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        notebook.addNote(note1);
        notebook.addNote(note2);
        var output = notebook.showNotes(new ArrayList<>());
        Assertions.assertTrue(output.contains(note1));
        Assertions.assertTrue(output.contains(note2));
    }
}