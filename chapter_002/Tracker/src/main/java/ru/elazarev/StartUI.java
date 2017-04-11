package ru.elazarev;

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
     * Default constructor.
     * @param input - Input interface implementation.
     */
    public StartUI(Input input) {
        this.input = input;
        this.tracker = new Tracker();
    }

    /**
     * Method to start user interface.
     */
    public void init() {

        printGreeting();
        while (true) {
            printMenu();
            String answer = input.ask("Select:");

            if ("6".equals(answer)) {
                System.out.println("Exit...");
                break;
            }

            switch (answer) {
                case "0":
                    addNewItem();
                    break;
                case "1":
                    printAllItems();
                    break;
                case "2":
                    editItem();
                    break;
                case "3":
                    deleteItem();
                    break;
                case "4":
                    findItemById();
                    break;
                case "5":
                    findItemByName();
                    break;
                default:
                    System.out.println("Incorrect input, repeat please");
            }
        }
    }

    /**
     * Method finds item by name in tracker storage and prints it if exist.
     */
    private void findItemByName() {
        System.out.println("------------------------------------");
        System.out.println("Finding item by NAME.");

        TrackerItem item = this.findByName();
        if (item == null) {
            System.out.println("Item with same name is not found");
        } else {
            processFounded(item);
        }
        System.out.println("------------------------------------");
    }

    /**
     * Method finds item by ID in tracker storage and prints it if exist.
     */
    private void findItemById() {
        System.out.println("------------------------------------");
        System.out.println("Finding item by ID.");

        TrackerItem item = this.findById();
        if (item == null) {
            System.out.println("Item with same id not found");
        } else {
            processFounded(item);
        }
        System.out.println("------------------------------------");
    }

    /**
     * Method encapsulates search by id.
     * @return founded item or null
     */
    private TrackerItem findById() {
        String id = this.input.ask("Enter item's id:");
        return tracker.findById(id);
    }

    /**
     * Method encapsulates search by name.
     * @return - founded item or null
     */
    private TrackerItem findByName() {
        String id = this.input.ask("Enter item's name:");
        return tracker.findByName(id);
    }

    /**
     * Render process item menu.
     * @param item - item to process
     */
    private void processFounded(TrackerItem item) {

        while (true) {
            System.out.println("Founded item :" + item);
            System.out.println("1. Edit fields");
            System.out.println("2. Add comment");
            System.out.println("3. Exit to main menu");
            String answer = this.input.ask("Select:");

            if (answer.equals("3")) {
                break;
            }

            switch (answer) {
                case "1":
                    editItem(item);
                    break;
                case "2":
                    addComment(item);
                    break;
                default:
                    System.out.println("Incorrect input");
            }
        }

    }

    /**
     * Method finds item by ID in tracker storage and delete it if exist.
     */
    private void deleteItem() {
        System.out.println("------------------------------------");
        System.out.println("Deleting item.");

        TrackerItem item = this.findById();
        if (item == null) {
            System.out.println("Item with same id not found");
        } else {
            tracker.delete(item);
            System.out.println("Deleted");
        }
        System.out.println("------------------------------------");
    }

    /**
     * Method finds item by ID in tracker storage and edit it if exist.
     */
    private void editItem() {
        System.out.println("------------------------------------");
        System.out.println("Editing item.");

        TrackerItem item = this.findById();
        if (item == null) {
            System.out.println("Item with same id not found");
        } else {
            editItem(item);
        }
        System.out.println("------------------------------------");
    }

    private void addComment(TrackerItem item) {
        String msg = this.input.ask("Write comment:");
        if ("".equals(msg)) {
            return;
        }
        item.addComment(msg);
        System.out.println("Comment added...");
    }

    private void editItem(TrackerItem item) {
        String newName = this.input.ask("Name is " + item.getName() +  ". Enter new name:");
        String newDesc = this.input.ask("Description is " + item.getDescription() + "Enter new description:");

        if ("".equals(newName)) {
            newName = item.getName();
        }

        if ("".equals(newDesc)) {
            newDesc = item.getDescription();
        }

        this.tracker.update(new TrackerItem(item.getId(), newName, newDesc, item.getCreatedAt(), item.getComments()));
        System.out.println("Edited");
    }

    /**
     * Method adding new item in tracker storage.
     */
    private void addNewItem() {
        System.out.println("------------------------------------");
        System.out.println("Adding new item.");
        String name = this.input.ask("Enter name:");
        String id = this.input.ask("Enter id:");
        String desc = this.input.ask("Enter description:");
        this.tracker.add(new TrackerItem(id, name, desc));
        System.out.println("New item added.");
        System.out.println("------------------------------------");
    }

    /**
     * Method prints all items in tracker storage.
     */
    private void printAllItems() {
        System.out.println("------------------------------------");
        System.out.println("All items:");
        for (TrackerItem item : this.tracker.findAll()) {
            System.out.println(item);
        }
        System.out.println("------------------------------------");
    }

    /**
     * Method prints menu to make selection.
     */
    private void printMenu() {
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    /**
     * Method prints greeting to user.
     */
    private void printGreeting() {
        System.out.println("/---------------Hello user!-------------------/");
        System.out.println("This program is tracker for any tasks. It can add, edit, delete, comment and find tasks.");
        System.out.println("To start work, Select one of the menu items. For example '1' to see all tasks in tracker");
        System.out.println("or '6' to exit from program.");
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
