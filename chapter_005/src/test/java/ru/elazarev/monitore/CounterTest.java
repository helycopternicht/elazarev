package ru.elazarev.monitore;

import org.junit.Test;
import ru.elazarev.monitore.counter.Counter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Counter class test.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 20.07.17
 */
public class CounterTest {

    /**
     * Counter test.
     * @throws InterruptedException if not join
     */
    @Test
    public void counterTest() throws InterruptedException {

        Counter counter = new Counter();

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10_000; i++) {
                    counter.increment();
                }

            }
        });

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10_000; i++) {
                    counter.increment();
                }

            }
        });

        Thread th3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10_000; i++) {
                    counter.increment();
                }

            }
        });

        Thread th4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10_000; i++) {
                    counter.increment();
                }

            }
        });

        th1.start();
        th2.start();
        th3.start();
        th4.start();

        th1.join();
        th2.join();
        th3.join();
        th4.join();

        System.out.println(counter.getCount());
        assertThat(counter.getCount(), is(40_000));
    }
}