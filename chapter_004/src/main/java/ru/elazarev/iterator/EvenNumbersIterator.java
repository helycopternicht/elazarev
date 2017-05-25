package ru.elazarev.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Iterator for even numbers.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 25.05.17
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    /**
     * Storage for even numbers.
     */
    private List<Integer> storage;
    /**
     * Current index of storage.
     */
    private int index;

    /**
     * Default constructor.
     * @param sourceArr - for iterate even numbers
     */
    public EvenNumbersIterator(int[] sourceArr) {
        this.storage = new ArrayList<>();
        for (int i = 0; i < sourceArr.length; i++) {
            if (sourceArr[i] % 2 == 0) {
                this.storage.add(sourceArr[i]);
            }
        }
    }

    /**
     * If index out of range eben numbers then false else true.
     * @return true or false
     */
    @Override
    public boolean hasNext() {
        return index < this.storage.size();
    }

    /**
     * Returns next even number.
     * @throws NoSuchElementException if have not more elements.
     * @return next even number
     */
    @Override
    public Integer next() {
        if (index >= this.storage.size()) {
            throw new NoSuchElementException("No such element");
        }
        return this.storage.get(index++);
    }
}
