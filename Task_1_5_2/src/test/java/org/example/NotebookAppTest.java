package org.example;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * класс для проверки пользовательского интерфейса.
 */
public class NotebookAppTest {
    /**
     * проверка добавления заметок.
     *
     * @throws IOException - исключение ввода-вывода
     */
    @Test
    public void addTest() throws IOException {
        var ui = new NotebookApp("notebook.json");
        ui.execCommand(new String[]{"-help"});
        ui.execCommand(new String[]{"-add", "my title", "hshahfdlasj"});
        File file = new File("notebook.json");
        var notes = Json.readNotes(file);
        Assertions.assertEquals("my title", notes.get(notes.size() - 1).getTitle());
        Assertions.assertEquals("hshahfdlasj", notes.get(notes.size() - 1).getDescription());
    }

    /**
     * проверка удаления заметок.
     *
     * @throws IOException - исключение ввода-вывода
     */
    @Test
    public void removeTest() throws IOException {
        var ui = new NotebookApp("notebook.json");
        ui.execCommand(new String[]{"-rm", "my title"});
        ui.execCommand(new String[]{"-show"});
        File file = new File("notebook.json");
        var notes = Json.readNotes(file);

        var flag = true;
        for (var note : notes) {
            if (note.getTitle().equals("my title")) {
                flag = false;
                break;
            }
        }
        Assertions.assertTrue(flag);
    }
}