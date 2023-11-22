package org.example;

import java.util.LinkedList;

public class GradeBook {
    private int gradeSum;
    private int subAmount;

    private int currSemesterCount;

    private String name;
    private String surname;

    private LinkedList<Semester> allSems;

    public GradeBook(String name, String surname) {
        allSems = new LinkedList<>();
        this.name = name;
        this.surname = surname;
        gradeSum = 0;
        subAmount = 0;
        currSemesterCount = 0;
    }

    public boolean addGrade(String name, int mark, int SemesterId) {
        Semester Sem;
        if (SemesterId > currSemesterCount) {
            if (SemesterId - 1 != currSemesterCount) {
                System.out.println("What are you doing?");
                return false;
            }
            Sem = new Semester(SemesterId);
            this.allSems.add(Sem);
            currSemesterCount++;
        }
        else {
            Sem = this.allSems.getLast();
        }
        Sem.addSubject(name, mark);
        gradeSum += mark;
        subAmount ++;
        return true;
    }

    public double getAverageGrade() {
       return (double) gradeSum / subAmount;
    }

    public boolean studentGrandInCurrentSem() {
        return this.allSems.getLast().grand() != SemesterGrand.no;
    }

    public boolean redDiploma() {
        int amountFif = 0;
        boolean canGetRedDiploma = true;
        boolean qualificationWork = false;
        int lastGrade = 0;
        for (var sem: this.allSems){
            var list = sem.getMarks();
            for (var mark: list) {
                if (mark.getSubjectMark() == 5) { amountFif++; }
                else if (mark.getSubjectMark() < 4) { canGetRedDiploma = false; break; }

                if (mark.getSubjectName().equals("qualification work") && mark.getSubjectMark() == 5) {
                    qualificationWork = true;
                }
                lastGrade = mark.getSubjectMark();
            }
            if (!canGetRedDiploma) {
                break;
            }
        }
        double percent = (double) amountFif / subAmount;
        if (canGetRedDiploma && qualificationWork && (percent > 0.75) && (lastGrade == 5)) {
            return true;
        }
        else {
            return false;
        }
    }
}