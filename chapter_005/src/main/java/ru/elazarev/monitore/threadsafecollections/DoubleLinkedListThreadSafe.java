package ru.elazarev.monitore.threadsafecollections;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

/**
 * Thread safe List based on linked nodes.
 * @param <T>  type of elements to contain.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 02.06.17
 */
@ThreadSafe
public class DoubleLinkedListThreadSafe<T> implements Iterable<T> {
    /**
     * Count of elements.
     */
    private volatile int size;

    /**
     * First node in list.
     */
    @GuardedBy("this")
    private Node<T> head;

    /**
     * Last node in list.
     */
    @GuardedBy("this")
    private Node<T> tail;

    /**
     * Default constructor.
     */
    public DoubleLinkedListThreadSafe() {
    }

    /**
     * Adds element in list.
     * @param value - element to add.
     */
    public synchronized void add(T value) {
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
    public synchronized T get(int index) {
        check(index);
        return getNode(index).value;
    }

    /**
     * Returns node at specified index.
     * @param index index to find node
     * @return node
     */
    private Node<T> getNode(int index) {
        if (index < (size / 2)) {
            Node<T> start = this.head;
            for (int i = 0; i < index; i++) {
                start = start.next;
            }
            return start;
        } else {
            Node<T> start = this.tail;
            for (int i = size - 1; i > index; i--) {
                start = start.prev;
            }
            return start;
        }
    }

    /**
     * Returns count of elements in list.
     * @return count of elements
     */
    public int size() {
        return size;
    }

    /**
     * Removes element at index in list.
     * @param index index to find element
     * @return value of element at index
     */
    public synchronized T remove(int index) {
        check(index);
        Node<T> node = getNode(index);
        return unlink(node);
    }

    /**
     * Returns iterator.
     * @return list iterator
     */
    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    /**
     * Check list bounds.
     * @param index index to check
     */
    private void check(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Unlink node x from list.
     * @param x node to unlink
     * @return unlinked node value
     */
    private T unlink(Node<T> x) {
        final T element = x.value;
        final Node<T> next = x.next;
        final Node<T> prev = x.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.value = null;
        size--;
        return element;
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
