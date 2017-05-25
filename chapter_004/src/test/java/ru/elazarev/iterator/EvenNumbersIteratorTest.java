package ru.elazarev.iterator;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Test for EvenNumbersIterator class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 25.05.17
 */
public class EvenNumbersIteratorTest {

    /**
     * If in source array have not even numbers then hasNext is false.
     */
    @Test
    public void whenInArrayHaveNotEvenNumbersHasNextReturnsFalse() {
        Iterator<Integer> it = new EvenNumbersIterator(new int[] {1, 3, 5});
        assertFalse(it.hasNext());
    }

    /**
     * next() returns only even numbers. If next() when have no even then NoSuchElementException
     */
    @Test(expected = NoSuchElementException.class)
    public void whenInArrayOnlyOneEvenNumberNextReturnOnlyOneNumber() {
        Iterator<Integer> it = new EvenNumbersIterator(new int[] {1, 2, 3, 5, 7, 9});

        assertEquals(Integer.valueOf(2), it.next());
        it.next();
    }
}