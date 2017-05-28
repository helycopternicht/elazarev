package ru.elazarev.iterator;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 25.05.17
 */
public class PrimeNumbersIterator implements Iterator<Integer> {
    /**
     * Ref on source array.
     */
    private int[] storage;
    /**
     * Current index of storage.
     */
    private int index;

    /**
     * Default constructor.
     * @param sourceArr - for iterate prime numbers
     */
    public PrimeNumbersIterator(int[] sourceArr) {
        this.storage = sourceArr;
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
        for (int i = index; i < this.storage.length; i++) {
            if (isPrime(this.storage[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns next prime number.
     * @throws NoSuchElementException if have not more elements.
     * @return next prime number
     */
    @Override
    public Integer next() {

        for (; this.index < this.storage.length; this.index++) {
            if (isPrime(this.storage[index])) {
                return this.storage[index++];
            }
        }
        throw new NoSuchElementException("No such element");
    }
}
