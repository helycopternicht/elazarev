package ru.elazarev;

import org.junit.Test;
import ru.elazarev.Student;
import ru.elazarev.Teacher;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Teacher test class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 07.04.17
 */
public class TeacherTest {
    /**
     * StartLesson method test.
     */
    @Test
    public void whenStartThenStart() {
        Teacher teacher = new Teacher();
        assertThat(teacher.startLesson(), is("Lesson is started"));
    }

    /**
     * PutEstimate method test.
     */
    @Test
    public void whenPutEstimateThenEstimate() {
        Teacher teacher = new Teacher();
        assertThat(teacher.putEstimate("Petrov"), is("Petrov was evaluated"));
    }

    /**
     * endLesson method test.
     */
    @Test
    public void endLessonTest() {
        Teacher teacher = new Teacher();
        assertThat(teacher.endLesson(), is("Lesson is end"));
    }

    /**
     * AddStudent and getLastAddedStudent methods test.
     */
    @Test
    public void whenAddStudentThenGetStudent() {
        Teacher teacher = new Teacher();
        Student patrick = new Student("Patrick");
        teacher.addStudent(new Student("Bob"));
        teacher.addStudent(patrick);
        assertThat(teacher.getLastAddedStudent(), is(patrick));
    }

    /**
     * setSchcoolName and whereAreYouWork methods test.
     */
    @Test
    public void whenSchoolNameThenFromIsSomeValue() {
        Teacher teacher = new Teacher();
        teacher.setSchoolName("Alabama school");
        assertThat(teacher.whereAreYouWork(), is("Alabama school"));
    }

    /**
     * When there is not students call getLastAddedStudent throws exception.
     */
    @Test(expected = IllegalStateException.class)
    public void whenStudentIsEmptyThenException() {
        Teacher teacher = new Teacher();
        teacher.getLastAddedStudent();
    }

    /**
     * When there more then 20 students then throws exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIndexOutOfBoundsException() {
        Teacher teacher = new Teacher();
        for (int i = 0; i < 21; i++) {
            teacher.addStudent(new Student("Student#" + i));
        }
    }
}
