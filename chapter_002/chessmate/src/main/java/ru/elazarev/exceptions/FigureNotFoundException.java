package ru.elazarev.exceptions;

/**
 * Exception when figure not found on chess board.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 29.04.17
 */
public class FigureNotFoundException extends Exception {
    /**
     * Default constructor.
     * @param message - error message.
     */
    public FigureNotFoundException(String message) {
        super(message);
    }
}
