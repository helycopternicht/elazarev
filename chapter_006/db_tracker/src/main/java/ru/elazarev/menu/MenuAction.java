package ru.elazarev.menu;

/**
 * Interface for user menu actions.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 13.04.17
 */
public interface MenuAction {

    /**
     * Returns key of action.
     * @return - int key
     */
    int key();

    /**
     * Returns name of action.
     * @return - String name
     */
    String name();

    /**
     * Returns string info about action.
     * @return String
     */
    String info();

    /**
     * Do action.
     */
    void performAction();

}
