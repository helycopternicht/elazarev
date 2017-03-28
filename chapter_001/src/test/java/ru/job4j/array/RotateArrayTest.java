package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test for RotateArray class.
*
* @author Eugene Lazarev (mailto:helycopternicht@rambler.ru)
* @version $Id$
* @since 0.1
*/
public class RotateArrayTest {
	/**
	* Test for rotate method.
	*/
	@Test
	public void whenArrayLengthIsTwoThenRotate() {
		RotateArray rotator = new RotateArray();
		int[][] source = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		int[][] etalon = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
		assertThat(rotator.rotate(source), is(etalon));
	}
	/**
	* Test for rotate method.
	*/
	@Test
	public void whenArrayLengthIsThreeThenRotate() {
		RotateArray rotator = new RotateArray();
		int[][] source = {{1, 2}, {3, 4}};
		int[][] etalon = {{3, 1}, {4, 2}};
		assertThat(rotator.rotate(source), is(etalon));
	}
}
