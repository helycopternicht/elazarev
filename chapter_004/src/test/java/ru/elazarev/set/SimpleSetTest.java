package ru.elazarev.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for SimpleSet class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 05.06.17
 */
public class SimpleSetTest {
    /**
     * When try to add two equals elements will added only one.
     */
    @Test
    public void whenAddTwoEqualsElementsThenAddedOnlyOne() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("one");
        set.add("one");

        assertThat(set.size(), is(1));
    }

    /**
     * Iterator returns elements in order as they added.
     */
    @Test(expected = NoSuchElementException.class)
    public void  whenAddTwoEqualsElementsThenIteratorNextReturnElementOnlyOneTime() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("one");
        set.add("two");
        set.add("one");

        Iterator<String> it = set.iterator();

        assertThat(set.size(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("one"));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("two"));

        assertThat(it.hasNext(), is(false));
        it.next();
    }
}