package ru.elazarev.tree;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for Tree class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 16.06.17
 */
public class TreeTest {

    /**
     * When try to add element to empty tree be added parent and child.
     * when add to non empty to non existing parent then false.
     */
    @Test(expected = NoSuchElementException.class)
    public void addAndIteratorTest() {

        Tree<String> tree = new Tree<>();
        tree.add("1", "2");
        tree.add("1", "3");
        tree.add("1", "4");
        tree.add("2", "10");
        tree.add("10", "12");

        assertThat(tree.add("999", "888"), is(false));

        Iterator<Tree.Node<String>> it = tree.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.next().getValue(), is("1"));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next().getValue(), is("2"));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next().getValue(), is("10"));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next().getValue(), is("12"));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next().getValue(), is("3"));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next().getValue(), is("4"));

        assertThat(it.hasNext(), is(false));
        it.next();
    }

    /**
     * isBinary test.
     */
    @Test
    public void whenSomeOfNodeInTreeHasMoreThanTwoElementsThenIsBinaryIsFalseAndViceVersa() {
        Tree<String> tree = new Tree<>();
        tree.add("1", "2");
        tree.add("1", "3");
        tree.add("1", "4");
        tree.add("2", "10");
        tree.add("10", "12");

        assertThat(tree.isBinary(), is(false));

        Tree<String> treeOne = new Tree<>();
        treeOne.add("1", "2");
        treeOne.add("1", "3");
        treeOne.add("2", "10");
        treeOne.add("10", "12");

        assertThat(treeOne.isBinary(), is(true));
    }
}