package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Класс для проверки заметок.
 */
public class NoteTest {
    /**
     * проверка всех основных операций.
     */
    @Test
    public void basicTests() {
        var note1 = new Note("hello", "somenote");
        Assertions.assertEquals("hello", note1.getTitle());
        Assertions.assertEquals("somenote", note1.getDescription());

        var note2 = new Note("sometime", "hello2", "somenote2");
        Assertions.assertEquals("hello2", note2.getTitle());
        Assertions.assertEquals("somenote2", note2.getDescription());
        Assertions.assertEquals("sometime", note2.getNoteDate());
        note2.setNoteDate("newdate");
        Assertions.assertEquals("newdate", note2.getNoteDate());
        note2.setTitle("newtitle");
        Assertions.assertEquals("newtitle", note2.getTitle());
        note2.setDescription("newdiscription");
        Assertions.assertEquals("newdiscription", note2.getDescription());

        var note3 = new Note();
        Assertions.assertEquals("", note3.getTitle());
        Assertions.assertEquals("", note3.getDescription());
    }
}