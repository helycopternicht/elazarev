package ru.elazarev.waitnotify.consumerproducer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Simple blocking queue based on java.util.LinkedList.
 * @param <E> type of elements to operate.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.07.17
 */
public class SimpleBlockingQueue<E> {
    /**
     * Queue to store elements.
     */
    private Queue<E> queue;

    /**
     * Maximum queue size.
     */
    private int maxSize;

    /**
     * Default constructor.
     * @param maxSize maximum queue size
     */
    public SimpleBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = new LinkedList();
    }

    /**
     * Method adds element to blocking queue.
     * @param el element to add.
     * @throws InterruptedException if thread is interrupted
     */
    public void push(E el) throws InterruptedException {
        synchronized (this) {
            if (queue.size() >= maxSize) {
                this.wait();
            }

            queue.add(el);
            this.notify();
        }
    }

    /**
     * Method get and remove first element from queue.
     * @return first element
     */
    public E pop() {
        synchronized (this) {
            while (queue.size() == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            E tmp = queue.remove();
            this.notify();
            return tmp;
        }
    }
}
