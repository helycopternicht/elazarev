package ru.elazarev.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test for SimpleArray class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 26.05.17
 */
public class SimpleArrayTest {

    /**
     * When add elements in order then getting elements in the same order. For ints.
     */
    @Test
    public void addAndGetMethodTest() {
        SimpleArray<Integer> list = new SimpleArray<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
        assertThat(list.get(3), is(4));
    }

    /**
     * When add elements in order then getting elements in the same order. For Strings.
     */
    @Test
    public void addAndGetMethodTestWithStrings() {
        SimpleArray<String> list = new SimpleArray<>(2);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        assertThat(list.get(0), is("1"));
        assertThat(list.get(1), is("2"));
        assertThat(list.get(2), is("3"));
        assertThat(list.get(3), is("4"));
    }

    /**
     * When get incorrect index throws IndexOutOfBoundsException.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenUpdateIncorrectIndexThenThrowsIndexOutOfBoundsException() {
        SimpleArray<Integer> list = new SimpleArray<>(6);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list.get(5);
    }

    /**
     * When update method returns old value and update new.
     */
    @Test
    public void updateShouldReturnOldValueAndUpdateValueAtIndex() {
        SimpleArray<Integer> list = new SimpleArray<>(5);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        boolean result = list.update(3, 999);
        assertThat(result, is(true));
        assertThat(list.get(2), is(999));
    }

    /**
     * Delete method return false if not find element and true if find and delete.
     */
    @Test
    public void whenDeleteElementNotFoundReturnsFalseElseTrue() {
        SimpleArray<Integer> list = new SimpleArray<>(5);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        assertFalse(list.delete(999));
        assertThat(list.get(4), is(5));
        assertTrue(list.delete(5));
        list.add(999);
        assertThat(list.get(4), is(999));
    }
}