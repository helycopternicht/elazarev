package ru.elazarev;

import ru.elazarev.exceptions.FigureNotFoundException;
import ru.elazarev.exceptions.ImpossibleMoveException;
import ru.elazarev.exceptions.OccupiedWayException;
import ru.elazarev.figures.Cell;
import ru.elazarev.figures.Figure;

/**
 * Chess Board class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 29.04.17
 */
public class Board {

    /**
     * Size of board.
     */
    public static final int BOARD_SIZE = 8;

    /**
     * Figures on board.
     */
    private Figure[] figures;

    /**
     * Default constructor.
     * @param figures - array with figures.
     */
    public Board(Figure[] figures) {
        this.figures = figures;
    }

    /**
     * Move figure from source Cell to dest Cell.
     * @param source - begin of the way
     * @param dest - end of the way
     * @return - true if move is correct or throws some exception.
     * @throws ImpossibleMoveException - when dest Cell is unreachable
     * @throws OccupiedWayException - when some Figure on the way from source to dest
     * @throws FigureNotFoundException - when not found figure on the source Cell
     */
    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {

        boolean found = false;
        int index = 0;
        for (int i = 0; i < this.figures.length; i++) {
            if (figures[i] == null) {
                continue;
            }

            Cell pos = figures[i].getPosition();

            if (pos.equals(source)) {
                found = true;
                index = i;
                break;
            }
        }

        if (!found) {
            throw new FigureNotFoundException("Figure not found");
        }

        Cell[] wayToDest = figures[index].way(dest);

        for (int i = 0; i < wayToDest.length; i++) {
            for (int j = 0; j < figures.length; j++) {
                if (wayToDest[i].equals(figures[j].getPosition())) {
                    throw new OccupiedWayException("Way is not empty");
                }
            }
        }

        figures[index] = figures[index].getInstance(dest);
        return true;
    }
}
