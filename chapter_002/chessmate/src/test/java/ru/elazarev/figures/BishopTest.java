package ru.elazarev.figures;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;
import ru.elazarev.exceptions.ImpossibleMoveException;

/**
 * Bishop classes tests.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 16.05.17
 */
public class BishopTest {

    /**
     * Way method test.
     * @throws ImpossibleMoveException - throws here
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenIncorrectCellThenImposibleMoveException() throws ImpossibleMoveException {
        Bishop bish = new Bishop(new Cell(1, 1));
        bish.way(new Cell(1, 8));
    }

    /**
     * Way method test.
     * @throws ImpossibleMoveException - not throws.
     */
    @Test
    public void whenCellIsCorrectThenReturnsWay() throws ImpossibleMoveException {
        Bishop bish = new Bishop(new Cell(1, 1));
        Cell[] way = bish.way(new Cell(8, 8));

        Cell[] expect = new Cell[7];
        expect[0] = new Cell(2, 2);
        expect[1] = new Cell(3, 3);
        expect[2] = new Cell(4, 4);
        expect[3] = new Cell(5, 5);
        expect[4] = new Cell(6, 6);
        expect[5] = new Cell(7, 7);
        expect[6] = new Cell(8, 8);

        assertArrayEquals(expect, way);
    }
}
