package ru.elazarev.bomberman.secondversion.models;

import ru.elazarev.bomberman.secondversion.board.Board;
import ru.elazarev.bomberman.secondversion.board.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Bomberman monster class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 27.07.17
 */
public class Monster extends Unit {
    /**
     * Random for generate numbers to new moves.
     */
    private Random random;

    /**
     * Default constructor.
     * @param name name of hero
     * @param board game board
     * @param cell cell on the board
     */
    public Monster(String name, Board board, Cell cell) {
        super(name, board, cell);
        this.random = new Random();
    }

    /**
     * Monster game logic.
     */
    @Override
    public void run() {
        getCell().lock();
        while (!isInterrupted()) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Cell newCell = null;
            boolean successLock = false;
            while (!successLock) {
                newCell = getNextPossibleMove();
                System.out.println(Thread.currentThread().getName() + " try to lock " + newCell);
                try {
                    successLock = newCell.tryLock(5000, TimeUnit.MILLISECONDS);
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
            list.add(getBoard().getCell(x + 1, y));
        }
        if (inRangeOfBoard(x - 1)) {
            list.add(getBoard().getCell(x - 1, y));
        }
        if (inRangeOfBoard(y + 1)) {
            list.add(getBoard().getCell(x, y + 1));
        }
        if (inRangeOfBoard(y - 1)) {
            list.add(getBoard().getCell(x, y - 1));
        }
        return list;
    }
}
