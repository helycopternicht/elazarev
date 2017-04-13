package ru.elazarev;

import ru.elazarev.input.ConsoleInput;
import ru.elazarev.input.Input;
import ru.elazarev.menu.TrackerMenu;
import ru.elazarev.model.Tracker;

/**
 * Class for create user interface.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 09.04.17
 */
public class StartUI {

    /**
     * Field to get program input.
     */
    private Input input;

    /**
     * Tracker storage/ Simple database.
     */
    private Tracker tracker;

    /**
     * User menu class.
     */
    private TrackerMenu menu;

    /**
     * Default constructor.
     * @param input - Input interface implementation.
     */
    public StartUI(Input input) {
        this.input = input;
        this.tracker = new Tracker();
        this.menu = new TrackerMenu(this.tracker, this.input);
    }

    /**
     * Method to start user interface.
     */
    public void init() {

        printGreeting();

        do {
            this.menu.showMenu();
            this.menu.selectAction();
        } while (!"y".equals(this.input.ask("Do you want to exit? (y)")));

    }

    /**
     * Method prints greeting to user.
     */
    private void printGreeting() {
        System.out.println("/---------------Hello user!-------------------/");
        System.out.println("This program is tracker for any tasks. It can add, edit, delete, comment and find tasks.");
        System.out.println("To start work, Select one of the menu items. For example '0' to see all tasks in tracker");
        System.out.println("or '1' to add some one.");
        System.out.println("");
    }

    /**
     * Getter for tracker field. Used in testing classes.
     * @return tracker storage.
     */
    public Tracker getTracker() {
        return tracker;
    }

    /**
     * The main method to start app.
     * @param args - not used
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput()).init();
    }
}
