package ru.elazarev.bomberman.firstversion.models;

import ru.elazarev.bomberman.firstversion.board.Board;
import ru.elazarev.bomberman.firstversion.board.Cell;

/**
 * Bomberman monster class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 27.07.17
 */
public class Monster extends Unit {
    /**
     * Default constructor.
     * @param name name of hero
     * @param board game board
     * @param cell cell on the board
     */
    public Monster(String name, Board board, Cell cell) {
        super(name, board, cell);
    }
}
