package ru.elazarev.monitore.counter;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Threadsafe counter class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 19.07.17
 */
@ThreadSafe
public class Counter {

    /**
     * Count.
     */
    @GuardedBy("this")
    private volatile int count;

    /**
     * Method to increment count by one.
     */
    public synchronized void increment() {
        count++;
    }

    /**
     * Getter for count field.
     * @return int
     */
    public int getCount() {
        return count;
    }
}
