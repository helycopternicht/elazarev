package ru.elazarev.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 05.06.17
 */
public class SimpleLinkedSetTest {
    /**
     * When try to add duplicate values then stores only unique elements.
     * next() on end of iterator throws NoSuchElementException
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAddDuplicateThenStoredOnlyUniqueElements() {

        SimpleLinkedSet<String> set = new SimpleLinkedSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        set.add("one");

        Iterator<String> it = set.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("one"));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("two"));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("three"));

        assertThat(it.hasNext(), is(false));
        it.next();
    }
}