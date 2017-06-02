package ru.elazarev.generic;

/**
 * Base class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 29.05.17
 */
abstract class Base {

    protected String id;

    /**
     * Returns id of base.
     * @return string
     */
    String getId() {
        return id;
    }

    /**
     * Set id of element to specified.
     * @param id - new id
     */
    void setId(String id) {
        this.id = id;
    }

    /**
     * HasCode based on id.
     * @return
     */
    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
