package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test for Calculator class.
*
* @author Eugene Lazarev (mailto:helycopternicht@rambler.ru)
* @version $Id$
* @since 0.1
*/
public class CalculatorTest {
	/**
	* Test for add method.
	*/
	@Test
	public void whenAddOneToOneThenTwo() {
		Calculator calc = new Calculator();
		calc.add(1, 1);
		assertThat(calc.getResult(), is(2.));
	}

	/**
	* Test for substruct method.
	*/
	@Test
	public void whenSubstructTwoFromThree() {
		Calculator calc = new Calculator();
		calc.substruct(3, 2);
		assertThat(calc.getResult(), is(1.));
	}

	/**
	* Test for divade method.
	*/
	@Test
	public void whenDivideTenOnFive() {
		Calculator calc = new Calculator();
		calc.divade(10, 5);
		assertThat(calc.getResult(), is(2.));
	}

	/**
	* Test for multiple method.
	*/
	@Test
	public void whenMultipleTenOnFive() {
		Calculator calc = new Calculator();
		calc.multiple(10, 5);
		assertThat(calc.getResult(), is(50.));
	}
}