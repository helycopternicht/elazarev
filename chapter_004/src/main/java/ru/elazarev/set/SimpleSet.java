package ru.elazarev.set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple set to contain only unique elements.
 * Based on array.
 * @param <T> type of elements to contain
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 05.06.17
 */
public class SimpleSet<T> implements Iterable<T> {
    /**
     * Inner array to store elements.
     */
    private T[] storage;

    /**
     * Count of elements in storage.
     */
    private int size;

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * Factor to grow inner storage.
     */
    private static final int GROW_FACTOR = 2;

    /**
     * Default constructor.
     */
    public SimpleSet() {
        this.storage = (T[]) new Object[DEFAULT_SIZE];
    }

    /**
     * Adds element in set only if it not contain it yet.
     * @param e element to add
     */
    public void add(T e) {
        int position = Arrays.binarySearch(storage, 0, size, e);
        if (position > -1) {
            return;
        }

        checkCapacity();
        position = position * (-1) - 1;
        if (position < size) {
            System.arraycopy(storage, position, storage, position + 1, size - position);
        }
        storage[position] = e;
        size++;
    }

    /**
     * Return count of elements in set.
     * @return count of added elements
     */
    public int size() {
        return size;
    }

    /**
     * Default iterator.
     * @return iterator for set.
     */
    @Override
    public Iterator<T> iterator() {
        return new SimpleSetIterator();
    }

    /**
     * Method grow inner storage if it needed.
     */
    private void checkCapacity() {
        if (this.size == this.storage.length) {
            this.storage = Arrays.copyOf(this.storage, this.storage.length * GROW_FACTOR);
        }
    }

    /**
     * DynamicArray iterator.
     */
    class SimpleSetIterator implements Iterator<T> {
        /**
         * Cursor position.
         */
        private int cursor;

        /**
         * Default constructor.
         */
        SimpleSetIterator() {
        }

        /**
         * Returns true if cursor have not reach end of the list.
         * @return true or false
         */
        @Override
        public boolean hasNext() {
            return this.cursor < size;
        }

        /**
         * Returns next element in list.
         * @return next element
         */
        @Override
        public T next() {
            if (cursor == size) {
                throw new NoSuchElementException();
            }
            return storage[cursor++];
        }
    }
}


