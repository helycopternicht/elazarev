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
        Index key = findByCell(source);
        if (key == null) {
            throw new FigureNotFoundException("Figure not found");
        }

        Cell[] wayToDest = key.getFigure().way(dest);

        for (Cell cell : wayToDest) {
            if (findByCell(cell) != null) {
                throw new OccupiedWayException("Way is not empty");
            }
        }

        figures[key.getIndex()] = figures[key.getIndex()].getInstance(dest);
        return true;
    }

    /**
     * Method returns Index instance with Figure and figure index.
     * @param cell - cell where figure searching.
     * @return - null if figure not found and Index instance if found.
     */
    private Index findByCell(Cell cell) {
        for (int i = 0; i < this.figures.length; i++) {
            if (figures[i] == null) {
                continue;
            }

            Cell pos = figures[i].getPosition();

            if (pos.equals(cell)) {
                return new Index(figures[i], i);
            }
        }
        return null;
    }

    /**
     * Inner class to contain data about figure and its position on board.
     */
    static class Index {

        /**
         * Figure.
         */
        private Figure figure;

        /**
         * Figures index.
         */
        private int index;

        /**
         * Default constructor.
         * @param figure - figure field.
         * @param index - index field.
         */
        Index(Figure figure, int index) {
            this.figure = figure;
            this.index = index;
        }

        /**
         * Getter for figure field.
         * @return figure field
         */
        public Figure getFigure() {
            return figure;
        }

        /**
         * Getter for index field.
         * @return - index
         */
        public int getIndex() {
            return index;
        }
    }
}
