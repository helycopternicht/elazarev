package ru.elazarev.figures;

import ru.elazarev.exceptions.ImpossibleMoveException;

/**
 * Abstract Figure class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 29.04.17
 */
public abstract class Figure {

    /**
     * Position on board.
     */
    private final Cell position;

    /**
     * Getter for position field.
     * @return - position value.
     */
    public Cell getPosition() {
        return position;
    }

    /**
     * Default constructor.
     * @param position - position field.
     */
    public Figure(Cell position) {
        this.position = position;
    }

    /**
     * Method should return array with Cell's from current position to dest position if it possible.
     * @param dest - destination cell on board.
     * @return - array with Cell's
     * @throws ImpossibleMoveException - throws when dest Cell isn't reachable.
     */
    public abstract Cell[] way(Cell dest) throws ImpossibleMoveException;

    /**
     * Returns new instance of same class with new position.
     * @param position - new position on board.
     * @return - new instance of Figure
     */
    public abstract Figure getInstance(Cell position);

}
