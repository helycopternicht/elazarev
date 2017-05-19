package ru.elazarev.convert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


/**
 * Test class for ConvertList class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 18.05.17
 */
public class ConvertListTest {

    /**
     * ConvertList.toList method test.
     */
    @Test
    public void convertToList() {
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 0, 0}};
        List<Integer> expect = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0));

        ConvertList convert = new ConvertList();
        List<Integer> actual = convert.toList(array);

        assertArrayEquals(expect.toArray(), actual.toArray());
    }

    /**
     * ConvertList.toArray method test.
     */
    @Test
    public void convertToArray() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        int[][] expect = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 0, 0}};

        ConvertList convert = new ConvertList();
        int[][] actual = convert.toArray(list, 3);

        assertArrayEquals(expect, actual);
    }

    /**
     * If try to convert null then throws IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenNullToConvertThenIllegalArgumentException() {
        ConvertList convert = new ConvertList();
        convert.convert(null);
    }

    /**
     * Convert method test.
     */
    @Test
    public void whenConvertCorrectDataThenOneDimensionList() {

        List<int[]> list = Arrays.asList(new int[] {1, 2, 3, 4}, new int[] {5, 6}, new int[] {7, 8, 9, 10, 11});

        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        ConvertList convert = new ConvertList();
        List<Integer> actual = convert.convert(list);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    /**
     * listToMap method test.
     */
    @Test
    public void convertToMapTest() {

        List<User> users = new ArrayList<>(Arrays.asList(
                new User(1, "Vasja"), new User(2, "Petja"), new User(3, "Masha")));

        Map<Integer, User> expected = new HashMap<>();
        expected.put(1, new User(1, "Vasja"));
        expected.put(2, new User(2, "Petja"));
        expected.put(3, new User(3, "Masha"));
        assertEquals(expected, new ConvertList().listToMap(users));
    }
}
