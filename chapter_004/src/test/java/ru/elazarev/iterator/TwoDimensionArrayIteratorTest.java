package ru.elazarev.iterator;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Test for TwoDimensionsArrayIterator class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 25.05.17
 */
public class TwoDimensionArrayIteratorTest {

    /**
     * If data is here each next() method returns next value.
     */
    @Test
    public void whenNextThenCursorMoveForward() {

        Integer[][] source = new Integer[][] {
                {1, 2},
                {3, 4, 5},
                {6, 7}
        };

        Iterator<Integer> it = new TwoDimensionArrayIterator<>(source);

        assertEquals(Integer.valueOf(1), it.next());
        assertEquals(Integer.valueOf(2), it.next());
        assertEquals(Integer.valueOf(3), it.next());
        assertEquals(Integer.valueOf(4), it.next());
        assertEquals(Integer.valueOf(5), it.next());
        assertEquals(Integer.valueOf(6), it.next());
        assertEquals(Integer.valueOf(7), it.next());
    }

    /**
     * hasNext() method returns true if next element is available.
     */
    @Test
    public void whenDataIsHereThenHasNextIsTrueAndViceVersa() {
        Iterator<Integer> it = new TwoDimensionArrayIterator<>(new Integer[][] {{1}});

        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }

    /**
     * If here is not available data then next method throws NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenNextOnEmptyDataThenNoSuchElementException() {
        Iterator<Integer> it = new TwoDimensionArrayIterator<>(new Integer[][] {{1}});

        it.next();
        it.next();
    }
}