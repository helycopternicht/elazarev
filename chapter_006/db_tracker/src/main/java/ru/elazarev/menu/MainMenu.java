package ru.elazarev.menu;

import ru.elazarev.auth.Authorization;
import ru.elazarev.input.Input;
import ru.elazarev.model.RequestCategory;
import ru.elazarev.model.RequestStatus;
import ru.elazarev.model.User;
import ru.elazarev.model.UserRequest;
import ru.elazarev.model.dao.RequestCategoryDao;
import ru.elazarev.model.dao.UserDao;
import ru.elazarev.model.dao.UserRequestDao;
import ru.elazarev.model.exceptions.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;

/**
 * Users main menu for Tracker app.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 13.04.17
 */
public class MainMenu extends AbstractMenu {

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
        this.input = input;
    }

    /**
     * Method fills menu actions.
     */
    public void fillActions() {
        this.actions = new ArrayList<>();
        this.actions.add(new ExitAction());
        this.actions.add(new AddAction());
        this.actions.add(new ShowAllAction());
        this.actions.add(new FindAction());
        this.actions.add(new ChangePasswordAction());
        if (Authorization.getUser().isAdmin()) {
            this.actions.add(new EditUsersAction());
        }
    }

    /**
     * Method shows menu to user.
     */
    @Override
    public void showMenu() {
        this.fillActions();
        for (MenuAction action : this.actions) {
            System.out.println(action.info());
        }
        selectAction();
    }

    /**
     * Returns range of menu keys.
     * @return - int[]
     */
    @Override
    public int[] getRangeOfKeys() {
        int[] range = new int[actions.size()];
        for (int i = 0; i < actions.size(); i++) {
            range[i] = this.actions.get(i).key();
        }
        return range;
    }

    /**
     * Returns all menu actions.
     * @return list of all menu actions.
     */
    @Override
    public List<MenuAction> getMenuActions() {
        return this.actions;
    }

    /**
     * User input object.
     * @return user input.
     */
    @Override
    public Input getInput() {
        return this.input;
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
            return "Add new request";
        }

        /**
         * Do adding to tracker.
         */
        @Override
        public void performAction() {
            System.out.println("ADDING NEW REQUEST");
            System.out.println("Enter request fields:");
            String title = input.ask("Title: ");
            String description = input.ask("Description: ");
            String category = input.ask("Category: ");

            RequestCategoryDao rcdao = new RequestCategoryDao();
            RequestCategory rc = null;
            try {
                rc = rcdao.getByName(category);
            } catch (NoSuchElementException e) {
                rc = rcdao.create(new RequestCategory(-1, category));
            }

            UserRequestDao urdao = new UserRequestDao();
            urdao.create(new UserRequest(-1, title, description, null, Authorization.getUser(), rc, RequestStatus.CREATED));
            System.out.println("added....");
            showMenu();
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
            return 2;
        }

        /**
         * Returns name of action.
         * @return String
         */
        @Override
        public String name() {
            return "Show all requests";
        }

        /**
         * Shows all items in tracker.
         */
        @Override
        public void performAction() {
            List<UserRequest> list = new UserRequestDao().getAll();
            System.out.printf("ALL REQUESTS: %s\n", list.size());
            for (UserRequest item : list) {
                System.out.println(item);
            }
            showMenu();
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
            return 3;
        }

        /**
         * Returns name of action.
         * @return String
         */
        @Override
        public String name() {
            return "Find some request";
        }

        /**
         * Performs filter action.
         */
        @Override
        public void performAction() {
            new SearchMenu(input, MainMenu.this).showMenu();
        }
    }

    /**
     * Class to perform exit from app action.
     */
    class ExitAction extends AbstractMenuAction {
        /**
         * Returns key of action.
         * @return key.
         */
        @Override
        public int key() {
            return 0;
        }

        /**
         * Returns name of action.
         * @return name of action.
         */
        @Override
        public String name() {
            return "Exit tracker";
        }

        /**
         * Performs exit action.
         */
        @Override
        public void performAction() {
            System.exit(0);
        }
    }

    /**
     * Class to perform change password action.
     */
    class ChangePasswordAction extends AbstractMenuAction {
        /**
         * Returns key of action.
         * @return key.
         */
        @Override
        public int key() {
            return 4;
        }
        /**
         * Returns name of action.
         * @return name of action.
         */
        @Override
        public String name() {
            return "Change password";
        }

        /**
         * Request new password and write it ro bd.
         */
        @Override
        public void performAction() {
            String newPassword = input.ask("enter new password: ");
            User curUser = Authorization.getUser();
            curUser.setPassword(newPassword);

            try {
                new UserDao().update(curUser);
            } catch (NoSuchElementException e) {
                /*there should be logging*/
            }
            System.out.println("updated...");
            showMenu();

        }
    }

    /**
     * Class to open users edit menu.
     */
    class EditUsersAction extends AbstractMenuAction {
        /**
         * Returns key of action.
         * @return key.
         */
        @Override
        public int key() {
            return 5;
        }
        /**
         * Returns name of action.
         * @return name of action.
         */
        @Override
        public String name() {
            return "Manage users";
        }

        /**
         * Opens Users edit menu.
         */
        @Override
        public void performAction() {
            new UsersMenu(input, MainMenu.this).showMenu();
        }
    }
}
