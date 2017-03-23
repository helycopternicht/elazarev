package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test for Factorial class.
*
* @author Eugene Lazarev (mailto:helycopternicht@rambler.ru)
* @version $Id$
* @since 0.1
*/
public class FactorialTest {
	/**
	* Test for calc method.
	*/
	@Test
	public void whenCalcNIsFive() {
		Factorial fact = new Factorial();
		assertThat(fact.calc(5), is(120));
	}

	/**
	* Test for calc method.
	*/
	@Test
	public void whenCalcNIsZero() {
		Factorial fact = new Factorial();
		assertThat(fact.calc(0), is(1));
	}
}