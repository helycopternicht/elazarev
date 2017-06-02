package ru.elazarev.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * List based on linked nodes.
 * @param <T>  type of elements to contain.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 02.06.17
 */
public class DoubleLinkedList<T> implements Iterable<T> {
    /**
     * Count of elements.
     */
    private int size;

    /**
     * First node in list.
     */
    private Node<T> head;

    /**
     * Last node in list.
     */
    private Node<T> tail;

    /**
     * Default constructor.
     */
    public DoubleLinkedList() {
    }

    /**
     * Adds element in list.
     * @param value - element to add.
     */
    public void add(T value) {
        Node<T> last = this.tail;
        Node<T> newNode = new Node<>(this.tail, null, value);

        this.tail = newNode;
        if (last == null) {
            this.head = newNode;
        } else {
            last.next = newNode;
        }
        size++;
    }

    /**
     * Returns element at specified index.
     * @param index - index to find.
     * @return element/
     */
    public T get(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        if (index < (size / 2)) {
            Node<T> start = this.head;
            for (int i = 0; i < index; i++) {
                start = start.next;
            }
            return start.value;
        } else {
            Node<T> start = this.tail;
            for (int i = size - 1; i > index; i--) {
                start = start.prev;
            }
            return start.value;
        }
    }

    /**
     * Returns iterator.
     * @return list iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    /**
     * Iterator for DoublyLinkedList.
     */
    private class ListIterator implements Iterator<T> {

        /**
         * Cursor.
         */
        private Node<T> curNode;

        /**
         * Default constructor.
         */
        ListIterator() {
            this.curNode = head;
        }

        /**
         * Returns true if iterator has next element.
         * @return true or false.
         */
        @Override
        public boolean hasNext() {
            return curNode != null;
        }

        /**
         * Return next element in iterator.
         * @return next element.
         */
        @Override
        public T next() {
            if (curNode == null) {
                throw new NoSuchElementException();
            }
            T value = curNode.value;
            curNode = curNode.next;
            return value;
        }
    }

    /**
     * Inner node for DoublyLinkedList.
     * @param <T> - type of element to contain.
     */
    private static class Node<T> {
        /**
         * Previous node in list.
         */
        private Node prev;
        /**
         * Next node in list.
         */
        private Node next;

        /**
         * Value of node.
         */
        private T value;

        /**
         * Default constructor.
         * @param prev prev element
         * @param next next element
         * @param value value of element
         */
        Node(Node prev, Node next, T value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }
}
