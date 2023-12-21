package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * класс для тестирования.
 */
public class TestGradeBook {
    /**
     * тестирование зачетной книжки.
     */
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
        assertTrue((someGradeBook.getAverageGrade() > 4.4)
                && (someGradeBook.getAverageGrade() < 4.6));
        assertEquals("Boba Bebrov", someGradeBook.getOwnerInfo());
    }

    /**
     * тестирование семестров.
     */
    @Test
    public void semesterTest() {
        var sem = new Semester(1);
        assertEquals(sem.semesterGetNumber(), 1);
        sem.addSubject("Math", 5);
        assertEquals(sem.getMarks().getLast().getSubjectMark(), 5);
        sem.changeMarkInSubject("Math", 4);
        assertEquals(sem.getMarks().getLast().getSubjectMark(), 4);
        boolean ok = false;
        try {
            sem.addSubject("OS", 1);
        } catch (IllegalArgumentException e) {
            ok = true;
        }
        assertTrue(ok);
    }
}