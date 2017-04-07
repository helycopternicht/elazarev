package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Teacher test class.
 * @author Eugeme Lazarev mailto(helycopternicht@rambler.ru)
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
     * EndLesson method test.
     */
    @Test
    public void whenEndLessonThenEnd() {
        Teacher teacher = new Teacher();
        assertThat(teacher.endLesson(), is("Lesson is end"));
    }
}
