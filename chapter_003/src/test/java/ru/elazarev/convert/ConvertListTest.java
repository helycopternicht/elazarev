package ru.elazarev.convert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

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
}
