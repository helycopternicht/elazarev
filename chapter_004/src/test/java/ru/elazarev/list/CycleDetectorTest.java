package ru.elazarev.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for CycleDetector class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 03.06.17
 */
public class CycleDetectorTest {

    /**
     * When linked list has cycles then hasCycle should return true.
     */
    @Test
    public void whenLinkedListHasCycle() {
        Node<Integer> first = new Node(1);
        Node<Integer>  two = new Node(2);
        Node<Integer>  third = new Node(3);
        Node<Integer>  four = new Node(4);

        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(first);

        assertThat(CycleDetector.hasCycle(first), is(true));
    }

    /**
     * When linked list hasn't cycles then hasCycle should return false.
     */
    @Test
    public void whenLinkedListHasNotCycle() {
        Node<Integer> first = new Node(1);
        Node<Integer>  two = new Node(2);
        Node<Integer>  third = new Node(3);
        Node<Integer>  four = new Node(4);

        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(null);

        assertThat(CycleDetector.hasCycle(first), is(false));
    }
}