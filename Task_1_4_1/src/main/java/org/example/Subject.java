package org.example;

/**
 * класс предмета.
 */
public class Subject {
    private String name;
    private int mark;

    /**
     * конструктор предмета.
     *
     * @param name - имя предмета
     * @param mark - оценка от 2 до 5
     */
    public Subject(String name, int mark) {
        if (5 >= mark && mark >= 2) {
            this.name = name;
            this.mark = mark;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * получение названия предмета.
     *
     * @return - название предмета
     */
    public String getSubjectName() {
        return this.name;
    }

    /**
     * получение оценки за предмет.
     *
     * @return - оценка
     */
    public int getSubjectMark() {
        return this.mark;
    }
}