package ru.elazarev.menu;

/**
 * Class to implement method info for other MenuAction classes.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 14.04.17
 */
abstract class AbstractMenuAction implements MenuAction {

    /**
     * Returns info about action.
     * @return formatted string about current action.
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key(), this.name());
    }
}
