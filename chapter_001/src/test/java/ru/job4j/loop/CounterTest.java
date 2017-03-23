package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test for Counter class.
*
* @author Eugene Lazarev (mailto:helycopternicht@rambler.ru)
* @version $Id$
* @since 0.1
*/
public class CounterTest {
	/**
	* Test for add method.
	*/
	@Test
	public void whenAddStartIsOneAndFinishIsTen() {
		Counter counter = new Counter();

		assertThat(counter.add(1, 10), is(30));
	}
}