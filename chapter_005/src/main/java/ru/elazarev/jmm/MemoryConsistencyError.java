package ru.elazarev.jmm;

/**
 * Class illustrate memory consistency error with multi treading.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 14.07.17
 */
public class MemoryConsistencyError {
    /**
     * Count of iteration.
     */
    private static final int COUNT = 10_000;
    /**
     * Inner counter.
     */
    private static int counter;

    /**
     * Method start two threads that increment inner counter.
     * This example work with errors.
     * @param args args of app
     * @throws InterruptedException when thread is interrupted.
     */
    public static void main(String[] args) throws InterruptedException {

        Thread th0 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < COUNT; i++) {
                    increment();
                }
            }
        });

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < COUNT; i++) {
                    increment();
                }
            }
        });

        th0.start();
        th1.start();

        th0.join();
        th1.join();

        System.out.println(counter);
    }

    /**
     * Method increments inner counter.
     */
    public static void increment() {
        counter++;
    }

}
