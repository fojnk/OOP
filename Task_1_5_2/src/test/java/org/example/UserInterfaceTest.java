package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class UserInterfaceTest {
    @Test
    public void addTest() throws IOException {
        var ui = new UserInterface("notebook.json");
        ui.ExecCommand(new String[] {"-add", "my title", "hshahfdlasj"});

        File file = new File("notebook.json");
        var notes = Json.readNotes(file);
        Assertions.assertEquals("my title", notes.get(notes.size() - 1).getTitle());
        Assertions.assertEquals("hshahfdlasj", notes.get(notes.size() - 1).getDescription());
    }

    @Test
    public void removeTest() throws IOException {
        var ui = new UserInterface("notebook.json");
        ui.ExecCommand(new String[] {"-rm", "my title"});

        File file = new File("notebook.json");
        var notes = Json.readNotes(file);

        var flag = true;
        for (var note: notes) {
            if (note.getTitle().equals("my title")) {
                flag = false;
                break;
            }
        }
        Assertions.assertTrue(flag);
    }
}