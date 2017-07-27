package ru.elazarev.bomberman.firstversion.board;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Cell represent cell of game board.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 27.07.17
 */
public class Cell extends ReentrantLock {
    /**
     * Horizontal axis.
     */
    private final int x;
    /**
     * Vertical axis.
     */
    private final int y;

    /**
     * Default constructor.
     * @param x horizontal axis
     * @param y vertical axis
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x field.
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y field.
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Default toString.
     * @return string representation of cell.
     */
    @Override
    public String toString() {
        return "Cell{" + "x=" + x + ", y=" + y + '}';
    }
}
