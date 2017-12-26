package ru.elazarev.menu;

import ru.elazarev.input.Input;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Menu for Tracker app.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 13.04.17
 */
public class MainMenu {

    /**
     * Object to getting user input.
     */
    private Input input;

    /**
     * List of menu actions.
     */
    private List<MenuAction> actions;

    /**
     * Default constructor.
     * @param input - user input object
     */
    public MainMenu(Input input) {
        this.actions = new ArrayList<>();
        this.input = input;
        this.fillActions();
    }

    /**
     * Method fills menu actions.
     */
    public void fillActions() {
        this.actions.add(new ExitAction());
        this.actions.add(new ShowAllAction());
        this.actions.add(new AddAction());
        this.actions.add(new FindAction());
    }

    /**
     * Method shows menu to user.
     */
    public void showMenu() {
        for (MenuAction action : this.actions) {
            System.out.println(action.info());
        }
    }

    /**
     * Me  thod ask user about action and perform it.
     */
    public void selectAction() {
        int key = this.input.ask("Select action:", this.getRangeOfKeys());
        for (MenuAction action : this.actions) {
            if (action.key() == key) {
                action.performAction();
                break;
            }
        }
    }

    /**
     * Returns range of menu keys.
     * @return - int[]
     */
    private int[] getRangeOfKeys() {
        int[] range = new int[actions.size()];
        for (int i = 0; i < actions.size(); i++) {
            range[i] = this.actions.get(i).key();
        }
        return range;
    }

    /**
     * Add action class. Performs add new item to tracker.
     */
    class AddAction extends AbstractMenuAction {

        /**
         * Returns key of action.
         * @return int
         */
        @Override
        public int key() {
            return 1;
        }

        /**
         * Returns name of action.
         * @return String
         */
        @Override
        public String name() {
            return "Add new task";
        }

        /**
         * Do adding to tracker.
         */
        @Override
        public void performAction() {
            String name = input.ask("Please enter name of the task: ");
            String desc = input.ask("Please enter description of the task: ");

        }
    }

    /**
     * Class to show all items in tracker.
     */
    class ShowAllAction extends AbstractMenuAction {
        /**
         * Returns key of action.
         * @return int
         */
        @Override
        public int key() {
            return 0;
        }

        /**
         * Returns name of action.
         * @return String
         */
        @Override
        public String name() {
            return "Show all tasks";
        }

        /**
         * Shows all items in tracker.
         */
        @Override
        public void performAction() {
//            System.out.println("All tasks:");
//            for (TrackerItem item : tracker.findAll()) {
//                System.out.println(item);
//            }
        }
    }

    /**
     * Class to perform filter action.
     */
    class FindAction extends AbstractMenuAction {

        /**
         * Returns key of action.
         * @return int
         */
        @Override
        public int key() {
            return 4;
        }

        /**
         * Returns name of action.
         * @return String
         */
        @Override
        public String name() {
            return "Find tasks by name";
        }

        /**
         * Performs filter action.
         */
        @Override
        public void performAction() {
//            System.out.println("Filtering...");
//            String substr = input.ask("Enter substring for search:");
//            for (TrackerItem item : tracker.filterByName(substr)) {
//                System.out.println(item);
//            }
        }
    }

    class ExitAction extends AbstractMenuAction {

        @Override
        public int key() {
            return 0;
        }

        @Override
        public String name() {
            return "Exit";
        }

        @Override
        public void performAction() {
            System.exit(0);
        }
    }

    class ChangePasswordAction extends AbstractMenuAction {

        @Override
        public int key() {
            return 0;
        }

        @Override
        public String name() {
            return "Exit";
        }

        @Override
        public void performAction() {
            System.exit(0);
        }
    }

    class EditUsersAction extends AbstractMenuAction {

        @Override
        public int key() {
            return 0;
        }

        @Override
        public String name() {
            return "Exit";
        }

        @Override
        public void performAction() {
            System.exit(0);
        }
    }
}
