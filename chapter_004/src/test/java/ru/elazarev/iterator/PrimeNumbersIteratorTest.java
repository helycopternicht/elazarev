package ru.elazarev.iterator;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Test for PrimeNumbersIterator class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 25.05.17
 */
public class PrimeNumbersIteratorTest {

    /**
     * When array is not contain prime numbers then hasNext() should return false.
     */
    @Test
    public void whenNoPrimeNumbersInArrayThenHasNextReturnFalse() {
        Iterator<Integer> it = new PrimeNumbersIterator(new int[] {6, 4, 12, 18});
        assertFalse(it.hasNext());
    }

    /**
     * next() return only prime numbers in order as in array.
     */
    @Test
    public void whenArrayHasPrimeAndNoPrimeNextReturnsOnlyPrimeNumbers() {
        Iterator<Integer> it = new PrimeNumbersIterator(new int[] {2, 1, 4, 3, 5, 6, 7, 8, 9, 10, 11, 13});

        assertEquals(Integer.valueOf(2), it.next());
        assertEquals(Integer.valueOf(1), it.next());
        assertEquals(Integer.valueOf(3), it.next());
        assertEquals(Integer.valueOf(5), it.next());
        assertEquals(Integer.valueOf(7), it.next());
        assertEquals(Integer.valueOf(11), it.next());
        assertEquals(Integer.valueOf(13), it.next());
    }

    /**
     * if next() in end of iterator then throw NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenIteratorAtTheEndNextThrowNoSuchElementException() {
        Iterator<Integer> it = new PrimeNumbersIterator(new int[] {2, 1, 4});

        it.next();
        it.next();
        it.next();
    }
}