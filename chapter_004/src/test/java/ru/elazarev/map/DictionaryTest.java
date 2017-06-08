package ru.elazarev.map;

import org.junit.Test;

import java.util.List;
import java.util.Iterator;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;

/**
 * Test for Dictionary class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 07.06.17
 */
public class DictionaryTest {

    /**
     * insert() and get() methods test.
     */
    @Test
    public void whenAddElementThenSizeGrowingAndWeCanGetElementByKey() {
        Dictionary<String, Integer> map = new Dictionary<>();

        assertThat(map.insert("1", 1), is(true));
        assertThat(map.insert("2", 2), is(true));
        assertThat(map.insert("3", 3), is(true));
        assertThat(map.size(), is(3));
        assertThat(map.get("1"), is(1));
    }

    /**
     * insert() method test.
     */
    @Test
    public void whenInsetOneKeyTwiceThenSizeIsOneAndValueFromLastInsert() {
        Dictionary<String, Integer> map = new Dictionary<>();

        assertThat(map.insert("1", 1), is(true));
        assertThat(map.insert("1", 999), is(true));
        assertThat(map.size(), is(1));
        assertThat(map.get("1"), is(999));
    }

    /**
     * Delete() method test.
     */
    @Test
    public void whenDeleteThenSizeDecreasesAndThatKeyIsNotExistInMap() {
        Dictionary<String, Integer> map = new Dictionary<>();

        assertThat(map.insert("1", 1), is(true));
        assertThat(map.insert("2", 2), is(true));
        assertThat(map.size(), is(2));
        assertThat(map.delete("1"), is(true));
        assertThat(map.size(), is(1));
        assertNull(map.get("1"));
    }

    /**
     * Delete method test.
     */
    @Test
    public void whenDeleteNotExistingKeyThenReturnsFalse() {
        Dictionary<String, Integer> map = new Dictionary<>();

        assertThat(map.insert("1", 1), is(true));
        assertThat(map.insert("2", 2), is(true));
        assertThat(map.delete("3"), is(false));
    }

    /**
     * Iterator test.
     */
    @Test(expected = NoSuchElementException.class)
    public void iteratorTest() {
        Dictionary<Integer, Integer> map = new Dictionary<>();

        for (int i = 0; i < 20; i++) {
            map.insert(i, i);
        }

        for (int i = 0; i < 20; i++) {
            assertThat(map.get(i), is(i));
        }

        List<Integer> keys = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        Iterator<Dictionary.Entry<Integer, Integer>> it = map.iterator();

        for (int i = 0; i < 20; i++) {
            assertThat(it.hasNext(), is(true));
            Dictionary.Entry<Integer, Integer> next = it.next();
            keys.add(next.getKey());
            values.add(next.getValue());
        }

        List<Integer> expected = Arrays.asList(19, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 1);

        assertThat(keys.containsAll(expected), is(true));
        assertThat(values.containsAll(expected), is(true));

        assertThat(it.hasNext(), is(false));
        it.next();
    }
}