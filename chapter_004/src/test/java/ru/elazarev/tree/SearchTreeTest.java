package ru.elazarev.tree;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for SearchTree class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 16.06.17
 */
public class SearchTreeTest {

    /**
     * Add method tests. Iterator test.
     */
    @Test(expected = NoSuchElementException.class)
    public void addAndIteratorTest() {

        SearchTree<Integer> tree = new SearchTree<>();
        assertThat(tree.add(10), is(true));
        assertThat(tree.add(15), is(true));
        assertThat(tree.add(15), is(false));
        assertThat(tree.add(7), is(true));
        assertThat(tree.add(4), is(true));
        assertThat(tree.add(3), is(true));
        assertThat(tree.add(9), is(true));
        assertThat(tree.add(1), is(true));


        Iterator<Integer> it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(9));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(10));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(15));

        assertThat(it.hasNext(), is(false));
        it.next();
    }

}