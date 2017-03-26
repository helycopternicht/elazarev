package ru.job4j.array;

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
public class TurnTest {
	/**
	* Test for back method.
	*/
	@Test
	public void whenArrayLengthIsFive() {
		Turn t = new Turn();
		int[] source = {1, 2, 3, 4, 5};
		int[] etalon = {5, 4, 3, 2, 1};
		t.back(source);
		assertThat(source, is(etalon));
	}
	/**
	* Test for back method.
	*/
	@Test
	public void whenArrayLengthIsSix() {
		Turn t = new Turn();
		int[] source = {1, 2, 3, 4, 5, 6};
		int[] etalon = {6, 5, 4, 3, 2, 1};
		t.back(source);
		assertThat(source, is(etalon));
	}
}
