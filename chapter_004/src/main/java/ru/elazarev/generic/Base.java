package ru.elazarev.generic;

/**
 * Base class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 29.05.17
 */
abstract class Base {
    /**
     * Returns id of base.
     * @return string
     */
    abstract String getId();

    /**
     * Set id of element to specified.
     * @param id - new id
     */
    abstract void setId(String id);
}
