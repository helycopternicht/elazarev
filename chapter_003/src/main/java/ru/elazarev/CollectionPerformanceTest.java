package ru.elazarev;

import java.util.Collection;
import java.util.Iterator;

/**
 * Benchmark for Collection implementations.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 17.05.17
 */
public class CollectionPerformanceTest {

    /**
     * Method adds line in collection amount times.
     * @param collection - collection where will added line
     * @param line - line to add
     * @param amount - count of times to add line
     * @return - time in millisecond to do operations
     */
    public long add(Collection<String> collection, String line, int amount) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.add(line);
        }
        return System.currentTimeMillis() - time;
    }

    /**
     * Method delete elements from begin of collection amount times.
     * @param collection - to delete from
     * @param amount - amount elements to delete
     * @return - time in millisecond
     */
    public long delete(Collection<String> collection, int amount) {
        long time = System.currentTimeMillis();
        Iterator<String> iterator = collection.iterator();
        int i = 0;
        while (iterator.hasNext() && i < amount) {
            iterator.next();
            iterator.remove();
            i++;
        }
        return System.currentTimeMillis() - time;
    }
}
