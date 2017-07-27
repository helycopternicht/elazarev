package ru.elazarev.nonblocking;

/**
 * Exception throws when parallel threads rewrite some data.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 27.07.17
 */
public class OptimisticException extends RuntimeException {
    /**
     * Default constructor.
     * @param message message for logging.
     */
    public OptimisticException(String message) {
        super(message);
    }
}
