package ru.elazarev.bomberman.firstversion;

import ru.elazarev.bomberman.firstversion.board.Board;
import ru.elazarev.bomberman.firstversion.board.Cell;
import ru.elazarev.bomberman.firstversion.models.Hero;
import ru.elazarev.bomberman.firstversion.models.Monster;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Bobmerman game class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 27.07.17
 */
public class Game {
    /**
     * Game board.
     */
    private Board board;

    /**
     * Default constructor.
     */
    public Game() {
        this.board = new Board();
    }

    /**
     * Constructor with board size.
     * @param size size of game board
     */
    public Game(int size) {
        this.board = new Board(size);
    }

    /**
     * Start new game.
     */
    public void start() {
        int unitCount = board.size();
        ExecutorService service = Executors.newFixedThreadPool(unitCount);
        service.execute(new Hero("Hero", board, new Cell(9, 1)));

        for (int x = 1, y = 9; x < unitCount; x++, y--) {
            service.execute(new Monster("Monster" + x, board, new Cell(x, y)));
        }
    }

    /**
     * Main to test.
     * @param args args
     */
    public static void main(String[] args) {
        new Game().start();
    }
}
