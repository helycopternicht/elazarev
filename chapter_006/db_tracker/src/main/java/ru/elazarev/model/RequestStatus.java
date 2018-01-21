package ru.elazarev.model;

/**
 * Request status enum.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 22.12.17
 */
public enum RequestStatus {
    /**
     * When request only created.
     */
    CREATED,
    /**
     * When request in progress.
     */
    IN_PROGRESS,
    /**
     * When request is done.
     */
    DONE,
}
