package ru.elazarev.exceptions;

/**
 * Exception when board have figure on way from source to dest cell.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 29.04.17
 */
public class OccupiedWayException extends Exception {

    /**
     * Default constructor.
     * @param message - error message.
     */
    public OccupiedWayException(String message) {
        super(message);
    }
}
