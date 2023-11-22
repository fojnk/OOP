package org.example;

import java.util.LinkedList;

public class Semester {
    private int number;

    private SemesterGrand grand;
    private LinkedList<Subject> allGrades;

    public Semester(int number) {
        allGrades = new LinkedList<>();
        this.grand = SemesterGrand.big;
        this.number = number;
    }

    public void addSubject(String name, int mark) {
        var newSubject = new Subject(name, mark);
        if (mark == 4) {
            grand = SemesterGrand.average;
        }
        else if (mark < 4) {
            grand = SemesterGrand.no;
        }
        allGrades.add(newSubject);
    }

    public Subject findSubjectByName(String name) {
        for (var sub: this.allGrades) {
            if (sub.getSubjectName().equals(name)) {
                return sub;
            }
        }
        return null;
    }

    public void changeMarkInSubject(String name, int mark) {
        var sub = findSubjectByName(name);
        if (sub != null) {
            this.allGrades.remove(sub);
        }
        this.allGrades.add(new Subject(name, mark));
    }

    public SemesterGrand grand() {
        return this.grand;
    }

    public LinkedList<Subject> getMarks() {
        LinkedList<Subject> newList = new LinkedList<>();
        for (var sub : this.allGrades) {
            newList.add(new Subject(sub.getSubjectName(), sub.getSubjectMark()));
        }
        return newList;
    }
}