package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test class for ArrayDuplicate class.
* @author Eugene Lazarev mailto(helycoptrenicht@rambler.ru)
* @since 29.03.2017
*/
public class ArrayDuplicateTest {
    /**
    * Remove method test.
    */
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate ad = new ArrayDuplicate();
        String[] source = {"Hi", "Hello", "Hi", "World", "Hi", "World", "Unique", "Hello", "World"};
		String[] expected = {"Hi", "Hello", "World", "Unique"};
		assertThat(ad.remove(source), is(expected));
    }
}

