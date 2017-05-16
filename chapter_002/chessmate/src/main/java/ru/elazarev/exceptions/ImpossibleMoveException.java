package ru.elazarev.exceptions;

/**
 * Exception when incorrect destination is.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 29.04.17
 */
public class ImpossibleMoveException extends Exception {

    /**
     * Default constructor.
     * @param message - error message.
     */
    public ImpossibleMoveException(String message) {
        super(message);
    }
}
