package ru.elazarev.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for two-dimension array.
 * @author Lezarev Eugene
 * @since 25/05/2017
 * @param <T>
 */
public class TwoDimensionArrayIterator<T> implements Iterator<T> {
    /**
     * Collection to iterate.
     */
    private T[][] storage;
    /**
     * Index for first dimension.
     */
    private int rowIndex;
    /**
     * Index for second dimension.
     */
    private int cellIndex;

    /**
     * Default constructor.
     * @param storage - storage value
     */
    public TwoDimensionArrayIterator(T[][] storage) {
        this.storage = storage;
    }

    /**
     * If indexes are greater then lengths then false else true.
     * @return true or false
     */
    @Override
    public boolean hasNext() {
        if (rowIndex >= storage.length) {
            return false;
        }

        if (rowIndex == storage.length - 1 && cellIndex >= storage[storage.length - 1].length) {
            return false;
        }
        return true;
    }

    /**
     * Method returns next element in storage in order.
     * @return - next element
     * @throws - NoSuchElementException if here no next element
     */
    @Override
    public T next() {

        if (cellIndex == storage[rowIndex].length) {
            rowIndex++;
            cellIndex = 0;
        }

        if (rowIndex >= storage.length) {
            throw new NoSuchElementException("No such element");
        }

        T result = storage[rowIndex][cellIndex++];
        return result;
    }
}
