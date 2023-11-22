package org.example;

public class Subject {
    private String name;
    private int mark;

    public Subject(String name, int mark) {
        if (5 >= mark && mark >= 2) {
            this.name = name;
            this.mark = mark;
        }
    }

    public String getSubjectName() {
        return this.name;
    }

    public int getSubjectMark() {
        return this.mark;
    }
}