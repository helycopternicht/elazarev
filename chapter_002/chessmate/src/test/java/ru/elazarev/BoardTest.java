package ru.elazarev;

import org.junit.Assert;
import org.junit.Test;
import ru.elazarev.exceptions.FigureNotFoundException;
import ru.elazarev.exceptions.ImpossibleMoveException;
import ru.elazarev.exceptions.OccupiedWayException;
import ru.elazarev.figures.Bishop;
import ru.elazarev.figures.Cell;
import ru.elazarev.figures.Figure;

/**
 * Tests for Board class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 13.05.17
 */
public class BoardTest {

    /**
     * Test to throw FigureNotFoundException when figure not found on cell.
     * @throws FigureNotFoundException - throws in this test.
     * @throws ImpossibleMoveException - not throws.
     * @throws OccupiedWayException - not throws.
     */
    @Test(expected = FigureNotFoundException.class)
    public void whenCellIsEmptyThenFigureNotFound() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {

        Figure[] figures = new Figure[3];
        figures[0] = new Bishop(new Cell(4, 4));
        figures[1] = new Bishop(new Cell(1, 1));
        figures[2] = new Bishop(new Cell(2, 7));

        Board board = new Board(figures);

        board.move(new Cell(5, 5), new Cell(8, 8));
    }

    /**
     * Test to throw ImpossibleMoveException when destination of move is unreachable.
     * @throws FigureNotFoundException - not throws.
     * @throws ImpossibleMoveException - throws in this test.
     * @throws OccupiedWayException - not throws.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenIncorrectCellThenImpossibleMoveException() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {

        Figure[] figures = new Figure[3];
        figures[0] = new Bishop(new Cell(4, 4));
        figures[1] = new Bishop(new Cell(1, 1));
        figures[2] = new Bishop(new Cell(2, 7));

        Board board = new Board(figures);

        board.move(new Cell(4, 4), new Cell(3, 8));
    }

    /**
     * Test to throw OccupiedWayException when some figure on way from source to dest.
     * @throws FigureNotFoundException - not throws.
     * @throws ImpossibleMoveException - not throws.
     * @throws OccupiedWayException - throws in this test.
     */
    @Test(expected = OccupiedWayException.class)
    public void whenWayIsNotEmptyThenOccupiedWayException() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        Figure[] figures = new Figure[3];
        figures[0] = new Bishop(new Cell(4, 4));
        figures[1] = new Bishop(new Cell(6, 6));
        figures[2] = new Bishop(new Cell(2, 7));

        Board board = new Board(figures);

        board.move(new Cell(4, 4), new Cell(8, 8));
    }

    /**
     * Test to move figure from source to dest.
     * @throws FigureNotFoundException - not throws.
     * @throws ImpossibleMoveException - not throws.
     * @throws OccupiedWayException - throws in this test.
     */
    @Test
    public void whenWayIsEmptyAndCorrectThenReturnArrayWithWay() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        Figure[] figures = new Figure[3];
        figures[0] = new Bishop(new Cell(4, 4));
        figures[1] = new Bishop(new Cell(6, 6));
        figures[2] = new Bishop(new Cell(7, 4));

        Board board = new Board(figures);
        Assert.assertTrue(board.move(new Cell(4, 4), new Cell(1, 7)));
    }
}
