package ru.elazarev.iterator;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Test for IteratorConverter class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 26.05.17
 */
public class IteratorsConverterTest {

    /**
     * Iterator returns elements in order from each iterator in paretn iterator.
     * And next() throws NoSuchElementException when elements ends.
     */
    @Test(expected = NoSuchElementException.class)
    public void convertTest() {
        List<Iterator<Integer>> list = new ArrayList<>();
        list.add(asList(1, 2, 3, 4).iterator());
        list.add(asList(5, 6, 7).iterator());
        list.add(asList(8).iterator());
        list.add(asList(9, 10, 11, 12).iterator());

        Iterator<Integer> it = new IteratorConverter().convert(list.iterator());

        assertEquals(Integer.valueOf(1), it.next());
        assertEquals(Integer.valueOf(2), it.next());
        assertEquals(Integer.valueOf(3), it.next());
        assertEquals(Integer.valueOf(4), it.next());
        assertEquals(Integer.valueOf(5), it.next());
        assertEquals(Integer.valueOf(6), it.next());
        assertEquals(Integer.valueOf(7), it.next());
        assertEquals(Integer.valueOf(8), it.next());
        assertEquals(Integer.valueOf(9), it.next());
        assertEquals(Integer.valueOf(10), it.next());
        assertEquals(Integer.valueOf(11), it.next());
        assertEquals(Integer.valueOf(12), it.next());
        it.next();
    }

    /**
     * If source iterator is empty then hasNext returns false.
     */
    @Test
    public void hasNextShouldReturnFalseFromEmptyIterator() {
        List<Iterator<Integer>> list = new ArrayList<>();
        Iterator<Iterator<Integer>> it = list.iterator();
        assertFalse(it.hasNext());
    }

    /**
     * If in source iterator two values then hasNext returns true 2 times and next returns
     * values two times. Third next throws NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void hasNextShouldReturnTrueFromIteratorWithTwoElementsOnlyTwoTimes() {
        List<Iterator<Integer>> list = new ArrayList<>();
        list.add(asList(1).iterator());
        list.add(asList(5).iterator());

        Iterator<Integer> it = new IteratorConverter().convert(list.iterator());

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(false));
        it.next();
    }
}