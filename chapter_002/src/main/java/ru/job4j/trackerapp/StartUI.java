package ru.job4j.trackerapp;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

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

        printHello();
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

        String name = this.input.ask("Enter item's name:");
        TrackerItem item = tracker.findByName(name);
        if (item == null) {
            System.out.println("Item with same name is not found");
        } else {
            System.out.println("Founded item :" + item);
        }
        System.out.println("------------------------------------");
    }

    /**
     * Method finds item by ID in tracker storage and prints it if exist.
     */
    private void findItemById() {
        System.out.println("------------------------------------");
        System.out.println("Finding item by ID.");

        String id = this.input.ask("Enter item's id:");
        TrackerItem item = tracker.findById(id);
        if (item == null) {
            System.out.println("Item with same id not found");
        } else {
            System.out.println("Founded item :" + item);
        }
        System.out.println("------------------------------------");
    }

    /**
     * Method finds item by ID in tracker storage and delete it if exist.
     */
    private void deleteItem() {
        System.out.println("------------------------------------");
        System.out.println("Deleting item.");

        String id = this.input.ask("Enter item's id:");
        TrackerItem item = tracker.findById(id);
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

        String id = this.input.ask("Enter item's id:");
        TrackerItem item = tracker.findById(id);
        if (item == null) {
            System.out.println("Item with same id not found");
        } else {
            String newName = this.input.ask("Name is " + item.getName() +  ". Enter new name:");
            String newDesc = this.input.ask("Description is " + item.getDescription() + "Enter new description:");

            this.tracker.update(new TrackerItem(id, newName, newDesc, item.getCreatedAt(), item.getComments()));
            System.out.println("Edited");
        }
        System.out.println("------------------------------------");
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

    private void printHello() {
        System.out.println("Hello user! This program is tracker for any tasks. It can add, edit, delete, comment and find tasks.");
        System.out.println("To start work, Select one of the menu items. For example '1' to see all tasks in tracker");
        System.out.println("or '6' to exit from program.");
        System.out.println("");
    }

    /**
     * The main method to start app.
     * @param args - not used
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput()).init();
    }
}
