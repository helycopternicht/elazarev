package ru.elazarev.menu;

/**
 * Class contain implementation of some methods same for all menus in app.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 18.01.18
 */
public abstract class AbstractMenu implements Menu {
    /**
     * Returns range of menu actions keys.
     * @return array with all menu actions keys.
     */
    @Override
    public int[] getRangeOfKeys() {
        int[] range = new int[getMenuActions().size()];
        for (int i = 0; i < getMenuActions().size(); i++) {
            range[i] = getMenuActions().get(i).key();
        }
        return range;
    }

    /**
     * Method ask user about action and perform it.
     */
    @Override
    public void selectAction() {
        int key = getInput().ask("Select action:", getRangeOfKeys());
        for (MenuAction action : getMenuActions()) {
            if (action.key() == key) {
                action.performAction();
                break;
            }
        }
    }
}
