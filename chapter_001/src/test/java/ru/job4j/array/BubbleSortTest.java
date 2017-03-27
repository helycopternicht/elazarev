package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test for BubbleSort class.
*
* @author Eugene Lazarev (mailto:helycopternicht@rambler.ru)
* @version $Id$
* @since 0.1
*/
public class BubbleSortTest {
	/**
	* Test for sort method.
	*/
	@Test
	public void whenArrayResultSortedArray() {
		BubbleSort b = new BubbleSort();
		int[] source = {5, 4, 3, 2, 1};
		int[] etalon = {1, 2, 3, 4, 5};
		assertThat(b.sort(source), is(etalon));
	}

	/**
	* Test for sort method.
	*/
	@Test
	public void whenArrayResultSortedArrayTwo() {
		BubbleSort b = new BubbleSort();
		int[] source = {5, 7, 9, 122, 13, 9, 7};
		int[] etalon = {5, 7, 7, 9, 9, 13, 122};
		assertThat(b.sort(source), is(etalon));
	}
}
