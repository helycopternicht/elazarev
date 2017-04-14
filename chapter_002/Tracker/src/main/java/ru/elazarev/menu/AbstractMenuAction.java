package ru.elazarev.menu;

/**
 * Class to implement method info for other MenuAction classes.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 14.04.17
 */
abstract class AbstractMenuAction implements MenuAction {

    /**
     * Returns info obout action.
     * @return
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key(), this.name());
    }
}
