package ru.elazarev.figures;

import ru.elazarev.Board;
import ru.elazarev.exceptions.ImpossibleMoveException;

/**
 * Bishop chess figure.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 29.04.17
 */
public class Bishop extends Figure {

    /**
     * Default constructor.
     * @param position - position on board.
     */
    public Bishop(Cell position) {
        super(position);
    }

    /**
     * Returns way from curren position to dest Cell.
     * @param dest - destination cell on board.
     * @return - way to dest
     * @throws ImpossibleMoveException - throws when dest Cell isn't reachable.
     */
    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        Cell[] way = new Cell[Board.BOARD_SIZE];
        int rowStep = getPosition().getRow() < dest.getRow() ? 1 : -1;
        int columnStep = getPosition().getColumn() < dest.getColumn() ? 1 : -1;

        int currRow = getPosition().getRow();
        int currColumn = getPosition().getColumn();
        int index = 0;
        while (currRow != dest.getRow() || currColumn != dest.getColumn()) {

            if (currRow > Board.BOARD_SIZE || currRow < 1 || currColumn > Board.BOARD_SIZE || currColumn < 1) {
                throw new ImpossibleMoveException("This move is impossible");
            }

            currRow += rowStep;
            currColumn += columnStep;

            way[index++] = new Cell(currRow, currColumn);
        }

        Cell[] newWay = new Cell[index];
        System.arraycopy(way, 0, newWay, 0, index);
        return newWay;
    }

    /**
     * Returns new instance of Bishop class with new position.
     * @param position - new position on board.
     * @return - new instance of Bishop
     */
    @Override
    public Bishop getInstance(Cell position) {
        return new Bishop(position);
    }
}
