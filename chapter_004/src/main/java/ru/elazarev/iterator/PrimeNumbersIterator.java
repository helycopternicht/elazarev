package ru.elazarev.iterator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 25.05.17
 */
public class PrimeNumbersIterator implements Iterator<Integer> {
    /**
     * Storage for prime numbers.
     */
    private List<Integer> storage;
    /**
     * Current index of storage.
     */
    private int index;

    /**
     * Default constructor.
     * @param sourceArr - for iterate prime numbers
     */
    public PrimeNumbersIterator(int[] sourceArr) {
        this.storage = new ArrayList<>();
        for (Integer num : sourceArr) {
            if (isPrime(num)) {
                this.storage.add(num);
            }
        }
    }

    /**
     * Returns true if number is prime and false else.
     * @param number - number to test
     * @return true or false
     */
    private boolean isPrime(int number) {
        BigInteger bigInteger = BigInteger.valueOf(number);
        return bigInteger.isProbablePrime((int) Math.log(number));
    }

    /**
     * If index out of range prime numbers then false else true.
     * @return true or false
     */
    @Override
    public boolean hasNext() {
        return index < this.storage.size();
    }

    /**
     * Returns next prime number.
     * @throws NoSuchElementException if have not more elements.
     * @return next prime number
     */
    @Override
    public Integer next() {
        if (index >= this.storage.size()) {
            throw new NoSuchElementException("No such element");
        }
        return this.storage.get(index++);
    }
}
