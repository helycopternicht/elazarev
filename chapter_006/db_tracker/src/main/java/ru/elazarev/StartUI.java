package ru.elazarev;

import ru.elazarev.auth.Authorization;
import ru.elazarev.input.ConsoleInput;
import ru.elazarev.input.Input;
import ru.elazarev.menu.MainMenu;
import ru.elazarev.model.database.CreateDataBase;

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
     * User menu class.
     */
    private MainMenu menu;

    /**
     * Default constructor.
     * @param input - Input interface implementation.
     */
    public StartUI(Input input) {
        this.input = input;
        this.menu = new MainMenu(this.input);
    }

    /**
     * Method to start user interface.
     */
    public void init() {
        CreateDataBase.createDbIfNotExist();
        printGreeting();
        menu.showMenu();
    }

    /**
     * Method prints greeting to user.
     */
    private void printGreeting() {
        System.out.println("__________SIMPLE TRACKER___________\n");
        System.out.println("Program for manage any task.");

        System.out.println("Authorization. Please enter:");

        String login = input.ask("login:");
        String password = input.ask("password:");
        boolean result = Authorization.login(login, password);
        if (!result) {
            System.out.println("Credential is incorrect!");
            System.exit(1);
        }
        System.out.println("Welcome dear " + login);

    }

    /**
     * The main method to start app.
     * @param args - not used
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput()).init();
    }
}
