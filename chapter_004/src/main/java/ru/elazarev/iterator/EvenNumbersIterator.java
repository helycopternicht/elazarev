package ru.elazarev.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for even numbers.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 25.05.17
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    /**
     * ref to array that we should iterate.
     */
    private int[] storage;
    /**
     * Current index of storage.
     */
    private int index;

    /**
     * Default constructor.
     * @param sourceArr - for iterate even numbers
     */
    public EvenNumbersIterator(int[] sourceArr) {
        this.storage = sourceArr;
    }

    /**
     * If index out of range eben numbers then false else true.
     * @return true or false
     */
    @Override
    public boolean hasNext() {
        return index < this.storage.length && arrayHasEvenNumbersBeginFromIndex();
    }

    /**
     * Returns true if in source array in range from cur index to end of array have
     * at least one eben element.
     * @return - true or false
     */
    private boolean arrayHasEvenNumbersBeginFromIndex() {
        for (int i = this.index; i < this.storage.length; i++) {
            if (isEven(this.storage[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if num is even or false if it is not.
     * @param num - number to check
     * @return true or false
     */
    private boolean isEven(int num) {
        return num % 2 == 0;
    }

    /**
     * Returns next even number.
     * @throws NoSuchElementException if have not more elements.
     * @return next even number
     */
    @Override
    public Integer next() {
        for (; this.index < this.storage.length; this.index++) {
            if (isEven(this.storage[this.index])) {
                return this.storage[this.index++];
            }
        }

        throw new NoSuchElementException("Has no more even number");
    }
}
