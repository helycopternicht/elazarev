package ru.job4j.exam;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test for ExamTask class.
*
* @author Eugene Lazarev (mailto:helycopternicht@rambler.ru)
* @version $Id$
* @since 0.1
*/
public class ExamTest {
	/**
	* Test contains method.
	*/
	@Test
	public void whenOriginIsBittlesAndSubIsTTLThenTrue() {
		Exam exam = new Exam();
		assertThat(exam.contains("bittles", "ttl"), is(true));
	}

	/**
	* Test contains method.
	*/
	@Test
	public void whenOriginIsTitanicAndSubIsLeoThenFalse() {
		Exam exam = new Exam();
		assertThat(exam.contains("titanic", "leo"), is(false));
	}

	/**
	* Test contains method.
	*/
	@Test
	public void whenOriginIsLeoAndSubIsVodkaThenFalse() {
		Exam exam = new Exam();
		assertThat(exam.contains("leo", "vodka"), is(false));
	}
}