package ru.elazarev.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Set implementation based on single linked list.
 * @param <T> type of element to store
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 05.06.17
 */
public class SimpleLinkedSet<T> implements Iterable<T> {
    /**
     * First element in linked list.
     */
    private Node head;

    /**
     * Default constructor.
     */
    public SimpleLinkedSet() {
    }

    /**
     * Adds element into set.
     * @param e element to add
     */
    public void add(T e) {

        if (head == null) {
            head = new Node(e);
            return;
        }

        Node first = head;
        Node last = null;
        while (first != null) {
            if (first.value.equals(e)) {
                return;
            }
            last = first;
            first = first.next;
        }

        last.next = new Node(e);
    }

    /**
     * Default iterator.
     * @return iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new SimpleLinkedSetIterator();
    }

    /**
     * Liked list node.
     */
    class Node {
        /**
         * Value of node.
         */
        private T value;
        /**
         * Next node.
         */
        private Node next;

        /**
         * Default constructor.
         * @param value node value
         */
        Node(T value) {
            this.value = value;
        }
    }

    /**
     * Iterator for linked list set.
     */
    class SimpleLinkedSetIterator implements Iterator<T> {

        /**
         * Current node.
         */
        private Node cursor;

        /**
         * Default constructor.
         */
        SimpleLinkedSetIterator() {
            cursor = head;
        }

        /**
         * Returns true if iterator has next element.
         * @return true or false
         */
        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        /**
         * Returns next element in iterator.
         * @throws NoSuchElementException if there is not more elements
         * @return next element
         */
        @Override
        public T next() {
            if (cursor == null) {
                throw new NoSuchElementException();
            }
            T val = cursor.value;
            cursor = cursor.next;
            return val;
        }
    }
}
