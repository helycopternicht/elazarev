package ru.elazarev.generic;

/**
 * Store to contain Role based elements.
 * @param <T> - type to contain
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 29.05.17
 */
public class RoleStore<T extends Role> extends AbstractStore<T> {
    /**
     * Default constructor.
     */
    public RoleStore() {
        this.storage = new SimpleArray<>();
    }
}
