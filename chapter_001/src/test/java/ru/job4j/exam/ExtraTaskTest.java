package ru.job4j.exam;

import org.junit.Test;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class for testing ExtraTask class.
* @author Eugene Lazarev (mailto:helycopternicht@rambler.ru)
* @version $Id$
* @since 0.1
*/
public class ExtraTaskTest {
    /**
    * Test merge method.
    */
    @Test
    public void whenTwoSortedArraysThenOneSordetArray() {
        ExtraTask ex = new ExtraTask();
        int[] first = {1, 2, 6, 10, 16};
        int[] second = {3, 4, 7, 11, 20};
        int[] expect = {1, 2, 3, 4, 6, 7, 10, 11, 16, 20};
        System.out.println(Arrays.toString(ex.merge(first, second)));
        assertThat(ex.merge(first, second), is(expect));
    }

    /**
    * Test merge method.
    */
    @Test
    public void whenTwoSortedArraysWithDifferentLengthThenOneSordetArray() {
        ExtraTask ex = new ExtraTask();
        int[] first = {1, 2, 6, 10, 16};
        int[] second = {3, 4, 7};
        int[] expect = {1, 2, 3, 4, 6, 7, 10, 16};
        System.out.println(Arrays.toString(ex.merge(first, second)));
        assertThat(ex.merge(first, second), is(expect));
    }

    /**
    * Test merge method.
    */
    @Test
    public void whenOneArrayIsEmptyThenOneSordetArray() {
        ExtraTask ex = new ExtraTask();
        int[] first = {1, 2, 6, 10, 16};
        int[] second = {};
        int[] expect = {1, 2, 6, 10, 16};
        System.out.println(Arrays.toString(ex.merge(first, second)));
        assertThat(ex.merge(first, second), is(expect));
    }
}