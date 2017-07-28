package ru.elazarev.bomberman.firstversion.models;

import ru.elazarev.bomberman.firstversion.board.Board;
import ru.elazarev.bomberman.firstversion.board.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
     * Random for generate numbers to new moves.
     */
    private Random random;

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
        this.random = new Random();
    }

    /**
     * Unit game logic.
     */
    @Override
    public void run() {
        getCell().lock();
        while (!isInterrupted()) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Cell newCell = null;
            boolean successLock = false;
            while (!successLock) {
                newCell = getNextPossibleMove();
                System.out.println(Thread.currentThread().getName() + " try to lock " + newCell);
                try {
                    successLock = newCell.tryLock(500, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    getCell().unlock();
                }
            }
            getCell().unlock();
            setCell(newCell);
            System.out.println(Thread.currentThread().getName() + " success lock " + newCell);
        }
    }

    /**
     * Return random move from possible moves.
     * @return Cell where unit try to move.
     */
    protected Cell getNextPossibleMove() {
        List<Cell> list = calculatePossibleWays();
        return list.get(random.nextInt(list.size() - 1));
    }

    /**
     * Calculates all possible ways where unit can move.
     * @return list of possible calls to move
     */
    protected List<Cell> calculatePossibleWays() {
        List<Cell> list = new ArrayList<>();
        int x = getCell().getX();
        int y = getCell().getY();

        if (inRangeOfBoard(x + 1)) {
            list.add(board.getCell(x + 1, y));
        }
        if (inRangeOfBoard(x - 1)) {
            list.add(board.getCell(x - 1, y));
        }
        if (inRangeOfBoard(y + 1)) {
            list.add(board.getCell(x, y + 1));
        }
        if (inRangeOfBoard(y - 1)) {
            list.add(board.getCell(x, y - 1));
        }
        return list;
    }

    /**
     * Checks value to be correct.
     * @param value value to check
     * @return true if value in range and false else
     */
    protected boolean inRangeOfBoard(int value) {
        if (value >= 0 && value <= this.board.size() - 1) {
            return true;
        }
        return false;
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
}
