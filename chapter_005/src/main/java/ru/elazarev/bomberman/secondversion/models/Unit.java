package ru.elazarev.bomberman.secondversion.models;

import ru.elazarev.bomberman.secondversion.board.Board;
import ru.elazarev.bomberman.secondversion.board.Cell;

/**
 * Unit representation. Parent class for units on field.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 27.07.17
 */
public abstract class Unit extends Thread {
    /**
     * Game board.
     */
    private Board board;

    /**
     * Current cell of unit.
     */
    private Cell cell;

    /**
     * Default constructor.
     * @param name name of thread
     * @param board game board
     * @param c start cell
     */
    protected Unit(String name, Board board, Cell c) {
        super(name);
        this.board = board;
        this.cell = c;
    }

    /**
     * Getter fo cell field.
     * @return current unit cell
     */
    protected Cell getCell() {
        return cell;
    }

    /**
     * Setter for cell field.
     * @param cell new cell field.
     */
    protected void setCell(Cell cell) {
        this.cell = cell;
    }

    /**
     * Returns game bord.
     * @return game board
     */
    protected Board getBoard() {
        return board;
    }

    /**
     * Checks value to be correct.
     * @param value value to check
     * @return true if value in range and false else
     */
    protected boolean inRangeOfBoard(int value) {
        if (value >= 0 && value <= getBoard().size() - 1) {
            return true;
        }
        return false;
    }
}
