package ru.elazarev.waitnotify.lock;

/**
 * Simple lock implementation.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 25.07.17
 */
public class SimpleLock {
    /**
     * Lock owner.
     */
    private volatile Thread owner;

    /**
     * Stop any not owner threads.
     */
    public synchronized void lock() {
        Thread curThread = Thread.currentThread();
        while (!(owner == null) &&   (owner != curThread)) {
            try {
                this.wait();
            } catch (InterruptedException ignore) {
            }
        }
        owner = curThread;
    }

    /**
     * Do lock to be free.
     */
    public synchronized void unlock() {
        Thread curThread = Thread.currentThread();
        if (owner != curThread) {
            return;
        }
        owner = null;
        this.notify();
    }
}
