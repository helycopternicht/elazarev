package ru.elazarev.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for Queue class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 02.06.17
 */
public class QueueTest {

    /**
     * Push method adds element to the end of the Queue and pop()
     * removes from the begin of the Queue. If call pop() in empty
     * Queue then throws IndexOutOfBoundsException.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void firstInFirstOut() {

        Queue<String> queue = new Queue<>();
        queue.push("1");
        queue.push("2");
        queue.push("3");

        assertThat(queue.pop(), is("1"));
        assertThat(queue.pop(), is("2"));
        assertThat(queue.pop(), is("3"));
        queue.pop();
    }
}