package ru.elazarev.exceptions;

/**
 * Exception when user enter integer, but in menu dose not clause with same key.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 14.04.17
 */
public class OutOfMenuException extends Exception {

    /**
     * Default constructor.
     * @param message - message to print.
     */
    public OutOfMenuException(String message) {
        super(message);
    }
}
