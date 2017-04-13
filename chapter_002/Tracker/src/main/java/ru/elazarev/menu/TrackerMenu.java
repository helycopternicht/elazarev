package ru.elazarev.menu;

import ru.elazarev.input.Input;
import ru.elazarev.model.Tracker;
import ru.elazarev.model.TrackerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Menu for Tracker app.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 13.04.17
 */
public class TrackerMenu {

    /**
     * Storage for tracker items.
     */
    private Tracker tracker;

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
     * @param tracker - tracker items storage
     * @param input - user input object
     */
    public TrackerMenu(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.actions = new ArrayList<>();
        this.input = input;
        this.fillActions();
    }

    /**
     * Method fills menu actions.
     */
    public void fillActions() {
        this.actions.add(new ShowAllAction());
        this.actions.add(new AddAction());
        this.actions.add(new EditAction());
        this.actions.add(new DeleteAction());
        this.actions.add(new FilterAction());
        this.actions.add(new CommentAction());
        this.actions.add(new FindByIdAction());
        this.actions.add(new FindByNameAction());
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
     * Method ask user about action and perform it.
     */
    public void selectAction() {
        int key = Integer.valueOf(this.input.ask("Select action:"));
        for (MenuAction action : this.actions) {
            if (action.key() == key) {
                action.performAction();
                break;
            }
        }
    }

    /**
     * Class to implement method info for other classes.
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

            TrackerItem newItem = new TrackerItem();
            newItem.setName(name);
            newItem.setDescription(desc);
            tracker.add(newItem);

            System.out.println("Task added...");
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
            System.out.println("All tasks:");
            for (TrackerItem item : tracker.findAll()) {
                System.out.println(item);
            }
        }
    }

    /**
     * Class to do edit action.
     */
    class EditAction extends AbstractMenuAction {

        /**
         * Returns key of action.
         * @return int
         */
        @Override
        public int key() {
            return 2;
        }

        /**
         * Returns name of action.
         * @return String
         */
        @Override
        public String name() {
            return "Edit task";
        }

        /**
         * Find item and perform edit action.
         */
        @Override
        public void performAction() {
            System.out.println("Edition...");
            String id = input.ask("Enter the task ID:");
            TrackerItem item = tracker.findById(id);
            if (item != null) {
                edit(item);
            } else {
                System.out.println("Task not Found");
            }
        }

        /**
         * Perform edit action.
         * @param item - item to edit.
         */
        private void edit(TrackerItem item) {
            String newName = input.ask("Old name is " + item.getName() + ". Enter new name:");
            String newDesc = input.ask("Old description is " + item.getDescription() + ". Enter new description:");

            if ("".equals(newName)) {
                newName = item.getName();
            }

            if ("".equals(newDesc)) {
                newDesc = item.getDescription();
            }

            TrackerItem freshItem = new TrackerItem(item);
            freshItem.setName(newName);
            freshItem.setDescription(newDesc);

            tracker.update(freshItem);
            System.out.println("Edited");
        }
    }

    /**
     * Class to perform delete action.
     */
    class DeleteAction extends AbstractMenuAction {

        /**
         * Returns key of action.
         * @return int
         */
        @Override
        public int key() {
            return 3;
        }

        /**
         * Returns name of action.
         * @return String
         */
        @Override
        public String name() {
            return "Delete task";
        }

        /**
         * Perform delete action.
         */
        @Override
        public void performAction() {
            System.out.println("Deletion...");
            String id = input.ask("Enter the task ID:");
            TrackerItem item = tracker.findById(id);
            if (item != null) {
                tracker.delete(item);
                System.out.println("Deleted");
            } else {
                System.out.println("Task not Found");
            }
        }
    }

    /**
     * Class to perform filter action.
     */
    class FilterAction extends AbstractMenuAction {

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
            return "Filter tasks by name";
        }

        /**
         * Performs filter action.
         */
        @Override
        public void performAction() {
            System.out.println("Filtering...");
            String substr = input.ask("Enter substring for search:");
            for (TrackerItem item : tracker.filterByName(substr)) {
                System.out.println(item);
            }
        }
    }

    /**
     * Class to perform comment of item.
     */
    class CommentAction extends AbstractMenuAction {

        /**
         * Returns key of action.
         * @return int
         */
        @Override
        public int key() {
            return 5;
        }

        /**
         * Returns name of action.
         * @return String
         */
        @Override
        public String name() {
            return "Comment task";
        }

        /**
         * Performs comment item.
         */
        @Override
        public void performAction() {
            System.out.println("Commenting...");
            String id = input.ask("Enter the task ID:");
            TrackerItem item = tracker.findById(id);
            if (item != null) {
                String msg = input.ask("Enter text of comment:");
                item.addComment(msg);
                System.out.println("Commented");
            } else {
                System.out.println("Task not Found");
            }
        }
    }

    /**
     * Class to find by name action.
     */
    class FindByNameAction extends AbstractMenuAction {

        /**
         * Returns key of action.
         * @return int
         */
        @Override
        public int key() {
            return 6;
        }

        /**
         * Returns name of action.
         * @return String
         */
        @Override
        public String name() {
            return "Find task by name";
        }

        /**
         * Performs find by name action.
         */
        @Override
        public void performAction() {
            String name = input.ask("Enter the task name:");
            TrackerItem item = tracker.findByName(name);
            if (item != null) {
                System.out.println(String.format("Found task: %s", item.toString()));
            } else {
                System.out.println("Task not Found");
            }
        }
    }

    /**
     * Class to find by id action.
     */
    class FindByIdAction extends AbstractMenuAction {

        /**
         * Returns key of action.
         * @return int
         */
        @Override
        public int key() {
            return 7;
        }

        /**
         * Returns name of action.
         * @return String
         */
        @Override
        public String name() {
            return "Find task by ID";
        }

        /**
         * Performs find by id action.
         */
        @Override
        public void performAction() {
            String id = input.ask("Enter the task ID:");
            TrackerItem item = tracker.findById(id);
            if (item != null) {
                System.out.println(String.format("Found task %s", item.toString()));
            } else {
                System.out.println("Task not Found");
            }
        }
    }
}
