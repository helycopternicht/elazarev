package ru.elazarev.extra;

import org.junit.Test;
import java.util.Comparator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for ExtraTask class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 03.07.17
 */
public class ExtraTaskTest {

    /**
     * Test merge method.
     */
    @Test
    public void whenTwoSortedArraysThenOneSortedArray() {
        ExtraTask ex = new ExtraTask();
        Integer[] first = {1, 2, 6, 10, 16};
        Integer[] second = {7, 11, 3, 4, 20};
        Integer[] expect = {1, 2, 3, 4, 6, 7, 10, 11, 16, 20};

        assertThat(ex.merge(first, second, new IntComparator()), is(expect));
    }

    /**
     * Test merge method.
     */
    @Test
    public void whenTwoSortedArraysWithDifferentLengthThenOneSortedArray() {
        ExtraTask ex = new ExtraTask();
        Integer[] first = {1, 2, 6, 10, 16};
        Integer[] second = {3, 7, 4};
        Integer[] expect = {1, 2, 3, 4, 6, 7, 10, 16};

        assertThat(ex.merge(first, second, new IntComparator()), is(expect));
    }

    /**
     * Test merge method.
     */
    @Test
    public void whenOneArrayIsEmptyThenOneSortedArray() {
        ExtraTask ex = new ExtraTask();
        Integer[] first = {1, 2, 6, 10, 16};
        Integer[] second = {};
        Integer[] expect = {1, 2, 6, 10, 16};

        assertThat(ex.merge(first, second, new IntComparator()), is(expect));
    }

    /**
     * Integer comparator.
     */
    class IntComparator implements Comparator<Integer> {
        /**
         * Compares its two arguments for order.  Returns a negative integer,
         * zero, or a positive integer as the first argument is less than, equal
         * to, or greater than the second.
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return a negative integer, zero, or a positive integer as the
         *         first argument is less than, equal to, or greater than the
         *         second.
         */
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

}