package ru.elazarev.waitnotify.threadpool;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Simple thread poll creates few threads to run some work.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 24.07.17
 */
public class SimpleThreadPool {
    /**
     * List of workers.
     */
    private Worker[] threadPool;
    /**
     * Tasks queue.
     */
    private Queue<Runnable> tasks;
    /**
     * If thread pool end work.
     */
    private volatile boolean finishFlag;

    /**
     * Default constructor.
     */
    public SimpleThreadPool() {
        threadPool = new Worker[Runtime.getRuntime().availableProcessors()];
        tasks = new LinkedList<>();

        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i] = new Worker();
            threadPool[i].start();
        }

    }

    /**
     * Add task to thread pool.
     * @param task task to add
     * @throws InterruptedException if notify is interrupted
     * @throws IllegalStateException if try to add task to closed thread pool
     */
    public void add(Runnable task) throws InterruptedException {
        if (finishFlag) {
            throw new IllegalStateException("Can't add worker to closed thread pool");
        }
        synchronized (tasks) {
            tasks.add(task);
            tasks.notify();
        }
    }

    /**
     * Interrupt workers.
     */
    public void close() {
        this.finishFlag = true;
        for (Worker worker : threadPool) {
            worker.interrupt();
        }
    }

    /**
     * Worker class to run tasks.
     */
    private class Worker extends Thread {
        /**
         * Runs tusks.
         */
        @Override
        public void run() {

            while (!isInterrupted()) {
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException ignore) {
                        }
                    }

                    Runnable r = tasks.poll();
                    if (r != null) {
                        r.run();
                    }
                }
            }
        }
    }

}
