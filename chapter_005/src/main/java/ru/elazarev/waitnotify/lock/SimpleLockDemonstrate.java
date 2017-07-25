package ru.elazarev.waitnotify.lock;

/**
 * Class to demonstrate SimpleLock class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 25.07.17
 */
public class SimpleLockDemonstrate {

    /**
     * Main. Starts two threads the should work synchronised.
     * @param args app arguments
     */
    public static void main(String[] args) {

        SimpleLock lock = new SimpleLock();

        Work w1 = new Work("Thread_1", lock);
        Work w2 = new Work("Thread_2", lock);
        w1.start();
        w2.start();
    }

    /**
     * Thread to demonstrate SimpleLock.
     */
    static class Work extends Thread {
        /**
         * Shared lock.
         */
        private SimpleLock lock;

        /**
         * default constructor.
         * @param name name of thread
         * @param lock shared lock
         */
        Work(String name, SimpleLock lock) {
            super(name);
            this.lock = lock;
        }

        /**
         * Run task.
         */
        @Override
        public void run() {
            lock.lock();
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
            lock.unlock();
        }
    }
}
