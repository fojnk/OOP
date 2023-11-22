package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestGradeBook {
    @Test
    public void basicOperationsTest() {
        var someGradeBook = new GradeBook("Boba", "Bebrov");
        someGradeBook.addGrade("Math", 5, 1);
        someGradeBook.addGrade("Football", 5, 1);
        assertEquals(someGradeBook.getAverageGrade(), 5);
        assertTrue(someGradeBook.studentGrandInCurrentSem());
        assertFalse(someGradeBook.redDiploma());
        someGradeBook.addGrade("qualification work", 5, 1);
        assertTrue(someGradeBook.redDiploma());
        someGradeBook.addGrade("OS", 3, 2);
        assertFalse(someGradeBook.studentGrandInCurrentSem());
        assertFalse(someGradeBook.redDiploma());
        assertTrue((someGradeBook.getAverageGrade() > 4.4) && (someGradeBook.getAverageGrade() < 4.6));
    }
}