package ru.elazarev.sort;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Test for SortUser class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 22.05.17
 */
public class SortUserTest {

    /**
     * Sort method test without comparator.
     */
    @Test
    public void sortMethodTest() {
        List<User> list = new ArrayList<>();
        list.add(new User("Eugene", 30));
        list.add(new User("Ivan", 29));
        list.add(new User("Ekaterina", 28));

        Set<User> sortedUsers = new SortUser().sort(list);
        Iterator<User> iter = sortedUsers.iterator();

        assertEquals(new User("Ekaterina", 28), iter.next());
        assertEquals(new User("Ivan", 29), iter.next());
        assertEquals(new User("Eugene", 30), iter.next());
    }

    /**
     * HashSort method test.
     */
    @Test
    public void hashSortTest() {
        List<User> list = new ArrayList<>();
        list.add(new User("Eugene", 30));
        list.add(new User("Ivan", 29));
        list.add(new User("Ekaterina", 28));

        List<User> sortedUsers = new SortUser().sortHash(list);
        Iterator<User> iter = sortedUsers.iterator();

        assertEquals(new User("Ekaterina", 28), iter.next());
        assertEquals(new User("Ivan", 29), iter.next());
        assertEquals(new User("Eugene", 30), iter.next());
    }

    /**
     * nameLengthSort test.
     */
    @Test
    public void nameLengthSort() {
        List<User> list = new ArrayList<>();
        list.add(new User("Eugene", 30));
        list.add(new User("Ivan", 29));
        list.add(new User("Ekaterina", 28));

        List<User> sortedUsers = new SortUser().sortLength(list);
        Iterator<User> iter = sortedUsers.iterator();

        assertEquals(new User("Ivan", 29), iter.next());
        assertEquals(new User("Eugene", 30), iter.next());
        assertEquals(new User("Ekaterina", 28), iter.next());
    }
}
