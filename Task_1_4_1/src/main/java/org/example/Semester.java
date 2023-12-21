package org.example;

import java.util.LinkedList;
import java.util.Optional;

/**
 * класс для описание семестров.
 */
public class Semester {
    private int number;
    private SemesterGrand grand;
    private final LinkedList<Subject> allGrades;

    /**
     * конструктор семестра.
     *
     * @param number - номер семестра
     */
    public Semester(int number) {
        allGrades = new LinkedList<>();
        this.grand = SemesterGrand.big;
        this.number = number;
    }

    /**
     * метод добавления нового предмета.
     *
     * @param name - имя предмета
     * @param mark - оценка
     */
    public void addSubject(String name, int mark) {
        var newSubject = new Subject(name, mark);
        if (mark == 4) {
            grand = SemesterGrand.average;
        } else if (mark < 4) {
            grand = SemesterGrand.no;
        }
        allGrades.add(newSubject);
    }

    /**
     * метод для нахождения предмета по его названию.
     *
     * @param name - название предмета
     * @return - предмет
     */
    public Optional<Subject> findSubjectByName(String name) {
        return allGrades.stream().filter(x -> x.getSubjectName().equals(name)).findFirst();
    }

    /**
     * метод для изменения оценки за предмет.
     *
     * @param name - имя предмета
     * @param mark - оценка за него
     */
    public void changeMarkInSubject(String name, int mark) {
        if (mark < 2 || mark > 5) throw new IllegalArgumentException();
        var sub = findSubjectByName(name);
        sub.ifPresent(this.allGrades::remove);
        this.allGrades.add(new Subject(name, mark));
    }

    /**
     * получение информации о стипендии.
     *
     * @return - стипендию
     */
    public SemesterGrand grand() {
        return this.grand;
    }

    /**
     * получение номера семестра.
     *
     * @return - номер семестра
     */
    public Integer semesterGetNumber() {
        return this.number;
    }

    /**
     * получение всех оценок в семестре.
     *
     * @return - список предметов
     */
    public LinkedList<Subject> getMarks() {
        LinkedList<Subject> newList = new LinkedList<>();
        allGrades.forEach(sub -> {
            newList.add(new Subject(sub.getSubjectName(), sub.getSubjectMark()));
        });
        return newList;
    }
}