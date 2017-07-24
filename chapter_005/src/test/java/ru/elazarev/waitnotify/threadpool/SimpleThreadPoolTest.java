package ru.elazarev.waitnotify.threadpool;

import org.junit.Test;

/**
 * Class to test SimpleThreadPool class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 24.07.17
 */
public class SimpleThreadPoolTest {

    /**
     * Test.
     * @throws InterruptedException if some error in add method.
     */
    @Test
    public void test() throws InterruptedException {

        SimpleThreadPool thpool = new SimpleThreadPool();
        for (int i = 0; i < 100; i++) {
            final int n = i;
            thpool.add(new Runnable() {
                @Override
                public void run() {
                    System.out.println(n);
                }
            });
        }

    }

}