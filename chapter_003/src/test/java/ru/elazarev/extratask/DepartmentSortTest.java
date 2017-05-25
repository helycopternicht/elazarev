package ru.elazarev.extratask;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

/**
 * Test for DepartmentSort class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 25.05.17
 */
public class DepartmentSortTest {

    /**
     * sortAsc method test.
     */
    @Test
    public void sortAscTest() {

        String[] source = new String[] {
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2",
        };

        String[] expected = new String[] {
                "K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2",
        };

        assertArrayEquals(expected, new DepartmentSort().sortAsc(source));
    }

    /**
     * sortDesc method test.
     */
    @Test
    public void sortDescTest() {

        String[] source = new String[] {
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2",
        };

        String[] expected = new String[] {
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K1",
                "K1\\SK2",
                "K1\\SK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK1",
        };

        assertArrayEquals(expected, new DepartmentSort().sortDesc(source));
    }
}
