package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test class for Max class.
* @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
* @since 22.03.2017
*/
public class MaxTest {
	/**
	* Test method for max method.
	*/
	@Test
	public void whenFirstIsOneAndSecondIsFive() {
		Max maximum = new Max();
		assertThat(maximum.max(1, 5), is(5));
	}

	/**
	* Test method for max method.
	*/
	@Test
	public void whenFirstIsTenAndSecondIsFive() {
		Max maximum = new Max();
		assertThat(maximum.max(10, 5), is(10));
	}

	/**
	* Test method for max method.
	*/
	@Test
	public void whenFirstIsTenAndSecondIsTen() {
		Max maximum = new Max();
		assertThat(maximum.max(10, 10), is(10));
	}
}