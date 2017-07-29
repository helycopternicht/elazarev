package ru.elazarev.bomberman.secondversion.models;

import ru.elazarev.bomberman.secondversion.board.Board;
import ru.elazarev.bomberman.secondversion.board.Cell;

/**
 * Class represent barier blocks on game board.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 28.07.17
 */
public class Barier extends Unit {
    /**
     * Default constructor.
     *
     * @param name  name of thread
     * @param board game board
     * @param c     start cell
     */
    protected Barier(String name, Board board, Cell c) {
        super(name, board, c);
    }

    /**
     * Lock cell.
     */
    @Override
    public void run() {
        getCell().lock();
    }
}
