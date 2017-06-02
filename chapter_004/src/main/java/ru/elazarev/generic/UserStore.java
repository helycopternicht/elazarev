package ru.elazarev.generic;

/**
 * Store to contain User based elements.
 * @param <T> - type of elements to contain
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 29.05.17
 */
public class UserStore<T extends User> extends AbstractStore<T> {
    /**
     * Default constructor.
     */
    public UserStore() {
        super(new SimpleArray<>());
    }
}
