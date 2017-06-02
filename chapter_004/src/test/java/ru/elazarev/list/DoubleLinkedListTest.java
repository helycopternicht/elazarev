package ru.elazarev.list;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for DoublyLinkedList class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 02.06.17
 */
public class DoubleLinkedListTest {

    /**
     * size() test.
     */
    @Test
    public void whenSizeOfListEqualsToCountOfAddedElements() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        assertThat(list.size(), is(3));
    }

    /**
     * When remove at index then returns removed elements value and removes element.
     * And when remove nonexistent index then throws IndexOutOfBoundsException.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveElementThenSizeIsLessThanWas() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        assertThat(list.remove(0), is("one"));
        assertThat(list.remove(0), is("two"));
        assertThat(list.remove(0), is("three"));
        list.remove(0);
    }

    /**
     * add() and get() methods test.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddElementThenGetIt() {

        DoubleLinkedList<String> dll = new DoubleLinkedList<>();
        dll.add("test");
        dll.add("test1");
        dll.add("test2");

        assertThat(dll.get(0), is("test"));
        assertThat(dll.get(1), is("test1"));
        assertThat(dll.get(2), is("test2"));
        dll.get(3);
    }

    /**
     * iterator() test.
     */
    @Test(expected = NoSuchElementException.class)
    public void iteratorTest() {
        DoubleLinkedList<String> dll = new DoubleLinkedList<>();
        dll.add("test");
        dll.add("test1");
        dll.add("test2");
        Iterator<String> it = dll.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test"));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test1"));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test2"));
        it.next();
    }
}