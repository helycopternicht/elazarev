package ru.elazarev.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class to convert iterators.
 * @param <T> type that iterator contains.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 26.05.17
 */
public class IteratorConverter<T> {
    /**
     * Source iterator to convert.
     */
    private Iterator<Iterator<T>> sourceIterator;

    /**
     * Method convert Iterator with iterators in one iterator where contains
     * all element from all iterators in order as in parent iterator.
     * @param it - Iterator with other iterators
     * @return - iterator with all elements
     */
    public Iterator<T> convert(Iterator<Iterator<T>> it) {
        this.sourceIterator = it;
        return new InnerIterator();
    }

    /**
     * Iterator implementation.
     */
    private class InnerIterator implements Iterator<T> {
        /**
         * Current iterator for getting values.
         */
        private Iterator<T> currentIterator;

        /**
         * Default constructor.
         */
        InnerIterator() {
            hasNextCurrentIterator();
        }

        /**
         * Returns true when end of sourceIterator is not reached and false else.
         * @return true or false
         */
        @Override
        public boolean hasNext() {
            while (!this.currentIterator.hasNext()) {
                boolean haveNewIterator = hasNextCurrentIterator();
                if (!haveNewIterator) {
                    return false;
                }
            }
            return currentIterator.hasNext();
        }

        /**
         * Change currentIterator to next if it exist.
         * @return true if end is not reached and false else
         */
        private boolean hasNextCurrentIterator() {
            if (!sourceIterator.hasNext()) {
                return false;
            }
            this.currentIterator = sourceIterator.next();
            return true;
        }

        /**
         * Return next item.
         * @return next element
         * @throws NoSuchElementException if end of iterator is reached.
         */
        @Override
        public T next() {
            while (!this.currentIterator.hasNext()) {
                boolean haveNewIterator = hasNextCurrentIterator();
                if (!haveNewIterator) {
                    throw new NoSuchElementException("No such element");
                }
            }
            return currentIterator.next();
        }
    }
}
