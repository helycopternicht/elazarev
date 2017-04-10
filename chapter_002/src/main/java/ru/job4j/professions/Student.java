package ru.job4j.professions;

/**
 * Student class fro Teacher.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 10.04.17
 */
public class Student {
    /**
     * Name of student.
     */
    private String name;

    /**
     * Default constructor.
     */
    public Student() {
    }

    /**
     * Constructor with name.
     * @param name - name of student
     */
    public Student(String name) {
        this.name = name;
    }

    /**
     * Getter for name field.
     * @return name of student
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name field.
     * @param name - name of student
     */
    public void setName(String name) {
        this.name = name;
    }
}
