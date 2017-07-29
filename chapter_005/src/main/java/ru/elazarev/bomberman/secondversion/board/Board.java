package ru.elazarev.bomberman.secondversion.board;

/**
 * Class represent game board.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 27.07.17
 */
public class Board {
    /**
     * Default size of board.
     */
    private static final int DEFAULT_FIELD_SIZE = 10;
    /**
     * Inner storage for board cells.
     */
    private Cell[][] field;

    /**
     * Default constructor.
     */
    public Board() {
        this(DEFAULT_FIELD_SIZE);
    }

    /**
     * Constructor with board size.
     * @param size size of board
     */
    public Board(int size) {
        field = new Cell[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                field[x][y] = new Cell(x, y);
            }
        }
    }

    /**
     * Returns size of board.
     * @return size of board
     */
    public int size() {
        return this.field.length;
    }

    /**
     * Returns Lock by coordinates.
     * @param x x axis
     * @param y y axis
     * @return lock object
     */
    public Cell getCell(int x, int y) {
        return field[x][y];
    }
}
