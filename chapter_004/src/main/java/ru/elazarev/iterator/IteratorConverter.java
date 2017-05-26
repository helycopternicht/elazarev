package ru.elazarev.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class to convert iterators.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 26.05.17
 */
public class IteratorConverter {

    /**
     * Method convert Iterator with iterators in one iterator where contains
     * all element from all iterators in order as in parent iterator.
     * @param it - Iterator with other iterators
     * @param <T> - type of elements in iterators
     * @return - iterator with all elements
     */
    public static <T> Iterator<T> convert(Iterator<Iterator<T>> it) {
        List<T> list = new ArrayList<>();
        while (it.hasNext()) {
            Iterator<T> innerIterator = it.next();
            while (innerIterator.hasNext()) {
                list.add(innerIterator.next());
            }
        }
        return list.iterator();
    }
}
