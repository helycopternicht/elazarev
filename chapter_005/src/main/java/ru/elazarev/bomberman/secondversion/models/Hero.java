package ru.elazarev.bomberman.secondversion.models;

import ru.elazarev.bomberman.secondversion.board.Board;
import ru.elazarev.bomberman.secondversion.board.Cell;

import java.util.concurrent.TimeUnit;

/**
 * Bomberman hero class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 27.07.17
 */
public class Hero extends Unit {
    /**
     * Next cell to go.
     */
    private Cell nextCell;

    /**
     * Default constructor.
     * @param name name of hero
     * @param board game board
     * @param cell cell on the board
     */
    public Hero(String name, Board board, Cell cell) {
        super(name, board, cell);
    }

    /**
     * Hero control logic.
     */
    @Override
    public void run() {
        getCell().lock();
        while (!isInterrupted()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                getCell().unlock();
            }

            if (nextCell == null || nextCell == getCell()) {
                continue;
            }

            boolean successLock = false;
            while (!successLock) {

                try {
                    successLock = nextCell.tryLock(500, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    getCell().unlock();
                }
            }
            System.out.println("Hero go to " + getCell());
            getCell().unlock();
            setCell(nextCell);
        }
    }

    /**
     * Try move hero to top.
     */
    public void goTop() {
        int newY = getCell().getY() - 1;
        final Cell cell = getCell();
        if (inRangeOfBoard(newY)) {
            nextCell = getBoard().getCell(cell.getX(), newY);
        }
    }

    /**
     * Try move hero to bottom.
     */
    public void goBottom() {
        int newY = getCell().getY() + 1;
        final Cell cell = getCell();
        if (inRangeOfBoard(newY)) {
            nextCell = getBoard().getCell(cell.getX(), newY);
        }
    }

    /**
     * Try move hero to left.
     */
    public void goLeft() {
        int newX = getCell().getX() - 1;
        final Cell cell = getCell();
        if (inRangeOfBoard(newX)) {
            nextCell = getBoard().getCell(newX, cell.getY());
        }
    }

    /**
     * Try move hero to right.
     */
    public void goRight() {
        int newX = getCell().getX() + 1;
        final Cell cell = getCell();
        if (inRangeOfBoard(newX)) {
            nextCell = getBoard().getCell(newX, cell.getY());
        }
    }
}
