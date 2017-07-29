package ru.elazarev.bomberman.secondversion;

import ru.elazarev.bomberman.secondversion.board.Board;
import ru.elazarev.bomberman.secondversion.board.Cell;
import ru.elazarev.bomberman.secondversion.models.Hero;

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
     * Pool of units threads.
     */
    private ExecutorService threadPool;
    /**
     * Count of monsters.
     */
    private int countOfMonsters;

    /**
     * Hero of game.
     */
    private Hero hero;

    /**
     * Constructor with board size.
     * @param fieldSize size of game board
     * @param countOfMonsters number of monsters to add on the game field
     */
    public Game(int fieldSize, int countOfMonsters) {
        this.board = new Board(fieldSize);
        this.countOfMonsters = countOfMonsters;
        this.threadPool = Executors.newFixedThreadPool(fieldSize);
    }

    /**
     * Start new game.
     */
    public void start() {
        placeUnitsOnTheBoard();
    }

    /**
     * Method places units on game board.
     */
    private void placeUnitsOnTheBoard() {
        hero = new Hero("Hero", board, new Cell(board.size() - 1, 0));
        threadPool.execute(hero);
        placeBariers();
        placeMonsters();
    }

    /**
     * Method places bariers on game board.
     */
    private void placeBariers() {
        //todo:
    }

    /**
     * Method places monsters on game board.
     */
    private void placeMonsters() {
        //todo:
    }

    /**
     * Main to test.
     * @param args args
     * @throws InterruptedException nop
     */
    public static void main(String[] args) throws InterruptedException {
        Game g = new Game(10, 10);
        g.start();
        g.hero.goBottom();
        Thread.sleep(1000);
        g.hero.goLeft();
        Thread.sleep(1000);
        g.hero.goRight();
        Thread.sleep(1000);
        g.hero.goTop();

    }
}
