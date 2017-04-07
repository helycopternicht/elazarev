package ru.job4j.professions;

/**
 * Teacher class.
 * @author Eugeme Lazarev mailto(helycopternicht@rambler.ru)
 * @since 07.04.17
 */
public class Teacher extends Profession {

    /**
     * Specialization of teacher.
     */
    private String specialization;

    /**
     * Set of students.
     */
    private String[] students;

    /**
     * Name of the workplace of teacher.
     */
    private String schoolName;

    /**
     * Returns word of teacher.
     * @return String - words
     */
    public String startLesson() {
        return "Lesson is started";
    }

    /**
     * Returns log record.
     * @param student - sturdent to evaluate
     * @return record string
     */
    public String putEstimate(String student) {
        return student + " was evaluated";
    }

    /**
     * Returns words of teacher about ending of lesson.
     * @return String Lesson is end
     */
    public String endLesson() {
        return "Lesson is end";
    }
}
