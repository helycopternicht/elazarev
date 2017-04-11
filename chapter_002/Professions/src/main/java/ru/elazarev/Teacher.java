package ru.elazarev;

/**
 * Teacher class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 07.04.17
 */
public class Teacher extends Profession {

    /**
     * Set of students.
     */
    private Student[] students = new Student[20];

    /**
     * Number of student in class.
     */
    private int size = 0;

    /**
     * Name of the workplace of teacher.
     */
    private String schoolName;

    /**
     * Returns word of teacher.
     *
     * @return String - words
     */
    public String startLesson() {
        return "Lesson is started";
    }

    /**
     * Returns log record.
     *
     * @param student - sturdent to evaluate
     * @return record string
     */
    public String putEstimate(String student) {
        return student + " was evaluated";
    }

    /**
     * Returns words of teacher about ending of lesson.
     *
     * @return String Lesson is end
     */
    public String endLesson() {
        return "Lesson is end";
    }

    /**
     * Method to add student in teachers class.
     * @param student - Student name
     */
    public void addStudent(Student student) {
        if (this.size == 19) {
            throw new IllegalArgumentException();
        }
        this.students[size++] = student;
    }

    /**
     * Results name of school where teacher working.
     * @return String cshool name
     */
    public String whereAreYouWork() {
        String result;
        if (this.schoolName == null) {
            result = "Default school";
        } else {
            result = this.schoolName;
        }
        return result;
    }

    /**
     * Setter for school name field.
     * @param name String
     */
    public void setSchoolName(String name) {
        this.schoolName = name;
    }

    /**
     * Returns last edded student or IllegalStateException if hes not students.
     * @return String - student name
     */
    public Student getLastAddedStudent() {
        if (this.size == 0) {
            throw new IllegalStateException("Teacher hes not students");
        }
        return this.students[size - 1];
    }
}
