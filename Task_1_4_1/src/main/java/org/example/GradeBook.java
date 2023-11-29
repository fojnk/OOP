package org.example;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * gradebook class.
 */
public class GradeBook {
    private int gradeSum;
    private int subAmount;

    private final String name;
    private final String surname;
    private final LinkedList<Semester> allSems;

    /**
     * конструктор зачетной книжки.
     *
     * @param name    - имя владельца
     * @param surname - фамилия владельца
     */
    public GradeBook(String name, String surname) {
        allSems = new LinkedList<>();
        this.name = name;
        this.surname = surname;
        gradeSum = 0;
        subAmount = 0;
    }

    /**
     * метод для получения информации о владельце.
     *
     * @return - ИФ
     */
    public String getOwnerInfo() {
        return name + " " + surname;
    }

    /**
     * метод для добавления оценок в зачетную книжку.
     *
     * @param name       - имя предмета
     * @param mark       - оценка
     * @param SemesterId - номер семестра
     */
    public void addGrade(String name, int mark, int SemesterId) {
        Semester Sem;
        if (SemesterId > allSems.size()) {
            if (SemesterId - 1 != allSems.size()) {
                throw new IllegalArgumentException();
            }
            Sem = new Semester(SemesterId);
            this.allSems.add(Sem);
        } else {
            Sem = this.allSems.getLast();
        }
        Sem.addSubject(name, mark);
        gradeSum += mark;
        subAmount++;
    }

    /**
     * средний балл учащегося.
     *
     * @return - средний балл
     */
    public double getAverageGrade() {
        return (double) gradeSum / subAmount;
    }

    /**
     * возможность получения какой-либо стипендии.
     *
     * @return - true or false
     */
    public boolean studentGrandInCurrentSem() {
        return this.allSems.getLast().grand() != SemesterGrand.no;
    }

    /**
     * возможность получения красного диплома.
     *
     * @return - true or false
     */
    public boolean redDiploma() {
        AtomicInteger finalAmountOfBodGrades = new AtomicInteger(0);
        AtomicInteger finalQualificationWork = new AtomicInteger(0);
        AtomicInteger finalAmountFif = new AtomicInteger(0);
        allSems.forEach(sem -> {
            finalAmountFif.addAndGet((int) sem.getMarks().stream().filter(x ->
                    x.getSubjectMark() == 5).count());
            finalQualificationWork.addAndGet((int) sem.getMarks().stream().filter(x ->
                    x.getSubjectName().equals("qualification work") && x.getSubjectMark() == 5).count());
            finalAmountOfBodGrades.addAndGet((int) sem.getMarks().stream().filter(x ->
                    x.getSubjectMark() < 4).count());
        });

        double percent = (double) finalAmountFif.doubleValue() / subAmount;
        return finalAmountOfBodGrades.doubleValue() == 0
                && finalQualificationWork.doubleValue() > 0
                && (percent > 0.75)
                && (allSems.getLast().getMarks().getLast().getSubjectMark() == 5);
    }
}