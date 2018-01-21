package ru.elazarev.menu;

import ru.elazarev.input.Input;
import java.util.List;

/**
 * Interface for all menus in app.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 17.01.18
 */
public interface Menu {
    /**
     * Show menu to user.
     */
    void showMenu();

    /**
     * Requests number of action to do.
     */
    void selectAction();

    /**
     * Returns range of actions keys of menu.
     * @return range of keys.
     */
    int[] getRangeOfKeys();

    /**
     * Returns list of menu actions.
     * @return list of actions.
     */
    List<MenuAction> getMenuActions();

    /**
     * Returns menus input object.
     * @return input object.
     */
    Input getInput();
}
