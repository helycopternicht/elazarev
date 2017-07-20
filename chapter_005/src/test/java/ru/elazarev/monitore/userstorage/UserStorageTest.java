package ru.elazarev.monitore.userstorage;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * UserStorage class test.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 19.07.17
 */
public class UserStorageTest {

    /**
     * Transfer test.
     * @throws InterruptedException if not join.
     */
    @Test
    public void transferTest() throws InterruptedException {

        UserStorage storage = new UserStorage();
        storage.add(1, "Name#1", 70);
        storage.add(2, "Name#2", 0);

        Thread th0 = new Thread(new Runnable() {
            @Override
            public void run() {
                storage.transfer(1, 2, 30);
            }
        });

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 11; i <= 20; i++) {
                    storage.transfer(1, 2, 30);
                }
            }
        });

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    storage.transfer(1, 2, 30);
                }
            }
        });

        th0.start();
        th1.start();
        th2.start();

        th0.join();
        th1.join();
        th2.join();

        assertThat(storage.getUser(1).getMoney(), is(10.));
        assertThat(storage.getUser(2).getMoney(), is(60.));
    }
}