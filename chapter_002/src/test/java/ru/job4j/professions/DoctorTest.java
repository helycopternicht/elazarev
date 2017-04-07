package ru.job4j.professions;

import static org.hamcrest.core.Is.is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Test class for Doctor class.
 * @author Eugeme Lazarev mailto(helycopternicht@rambler.ru)
 * @since 07.04.17
 */
public class DoctorTest {
    /**
     * Wash hand method test.
     */
    @Test
    public void whenWashHandsThenWash() {
        Doctor doctor = new Doctor();
        assertThat(doctor.washHands(), is("washing hands"));
    }

    /**
     * DoOperation method test.
     */
    @Test
    public void whenDoOperationThenDoOperation() {
        Doctor doctor = new Doctor();
        assertThat(doctor.doOperation(), is("do operation..."));
    }

    /**
     * InterviewPatient method test.
     */
    @Test
    public void whenInterviewPatientThenInterview() {
        Doctor doctor = new Doctor();
        assertThat(doctor.interviewPatient(), is("What is your name?"));
    }
}
