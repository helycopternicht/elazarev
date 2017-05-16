package ru.elazarev.figures;

import org.junit.Test;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 16.05.17
 */
public class CellTest {
    /**
     * Setter test.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenRowOrColumnGreaterThanEightThenIncorrectArgumentException() {
        Cell cell = new Cell(1, 1);
        cell.setRow(9);
    }

    /**
     * Setter test.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenRowOrColumnLessThanOneThenIncorrectArgumentException() {
        Cell cell = new Cell(1, 1);
        cell.setColumn(0);
    }
}
