package ru.elazarev.model.exceptions;

/**
 * Exception when entity not found in data base.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.12.17
 */
public class NoSuchElementException extends Exception {
    /**
     * Default constructor.
     * @param message message for show.
     */
    public NoSuchElementException(String message) {
        super(message);
    }
}
