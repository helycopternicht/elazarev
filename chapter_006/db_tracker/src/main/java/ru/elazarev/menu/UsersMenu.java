package ru.elazarev.menu;

import ru.elazarev.input.Input;
import ru.elazarev.model.User;
import ru.elazarev.model.dao.UserDao;
import ru.elazarev.model.exceptions.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;

/**
 * Menu to manage users in app.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 18.01.18
 */
public class UsersMenu extends AbstractMenu {
    /**
     * Users input class.
     */
    private Input input;
    /**
     * Parent menu.
     */
    private Menu parentMenu;
    /**
     * List of menu actions.
     */
    private List<MenuAction> actions;

    /**
     * Default constructor.
     * @param input usrs input.
     * @param parentMenu parent menu.
     */
    public UsersMenu(Input input, Menu parentMenu) {
        this.input = input;
        this.parentMenu = parentMenu;
        fillActions();
    }

    /**
     * Method fills menu actions.
     */
    private void fillActions() {
        actions = new ArrayList<>();
        actions.add(new ExitAction());
        actions.add(new AddAction());
        actions.add(new EditAction());
        actions.add(new DeleteAction());

    }

    /**
     * Shows menu to user.
     */
    @Override
    public void showMenu() {
        UserDao userDao = new UserDao();
        List<User> users = userDao.getAll();
        System.out.printf("All users: %s\n", users.size());
        for (User u : users) {
            System.out.println(u);
        }

        for (MenuAction action : this.actions) {
            System.out.println(action.info());
        }

        selectAction();
    }

    /**
     * Returns list of menu actions.
     * @return list of actions.
     */
    @Override
    public List<MenuAction> getMenuActions() {
        return this.actions;
    }

    /**
     * Returns user input object.
     * @return user object.
     */
    @Override
    public Input getInput() {
        return this.input;
    }

    /**
     * Returns user by id.
     * @return user witch id match to requested id.
     * @throws NumberFormatException if id is not a number.
     * @throws NoSuchElementException if user with such id is not found.
     */
    private User getUserById() throws NumberFormatException, NoSuchElementException {
        int id = -1;
        id = Integer.valueOf(input.ask("Enter id of user:"));
        UserDao dao = new UserDao();
        return dao.getById(id);
    }

    /**
     * Class to perform exit action.
     */
    class ExitAction extends AbstractMenuAction {
        /**
         * Return key of action.
         * @return key.
         */
        @Override
        public int key() {
            return 0;
        }

        /**
         * Return name of action.
         * @return name.
         */
        @Override
        public String name() {
            return "Exit to main menu";
        }

        /**
         * Shows parent menu.
         */
        @Override
        public void performAction() {
            parentMenu.showMenu();
        }
    }

    /**
     * Performs add user action.
     */
    class AddAction extends AbstractMenuAction {
        /**
         * Return key of action.
         * @return key.
         */
        @Override
        public int key() {
            return 1;
        }
        /**
         * Return name of action.
         * @return name.
         */
        @Override
        public String name() {
            return "Add user";
        }

        /**
         * Requests new user fields and create user in bd.
         */
        @Override
        public void performAction() {
            System.out.println("ADDING USER");
            System.out.println("Enter fields for new user:");
            String login = input.ask("Login:");

            String strIsAdmin = input.ask("Is this user admin? (y if true)");
            boolean isAdmin = false;
            if ("y".equals(strIsAdmin.toLowerCase())) {
                isAdmin = true;
            }

            String pwd = input.ask("password:");

            new UserDao().create(new User(-1, login, pwd, null, isAdmin));
            System.out.println("created...");
            showMenu();
        }
    }

    /**
     * Performs edit action of users menu.
     */
    class EditAction extends AbstractMenuAction {
        /**
         * Return key of action.
         * @return key.
         */
        @Override
        public int key() {
            return 2;
        }
        /**
         * Return name of action.
         * @return name.
         */
        @Override
        public String name() {
            return "Edit user";
        }

        /**
         * Request id of user to edit. Requests fields to edit.
         * Update user in db.
         */
        @Override
        public void performAction() {

            User editingUser = null;
            try {
                editingUser = getUserById();
            } catch (Exception e) {
                System.out.println("Incorrect id");
                showMenu();
                return;
            }

            System.out.println("EDITING USER");

            System.out.println("Enter:");
            String newPassword = input.ask("New password:");
            String strIsAdmin = input.ask("Is this user admin? (y if true)");
            boolean isAdmin = false;
            if ("y".equals(strIsAdmin.toLowerCase())) {
                isAdmin = true;
            }
            editingUser.setPassword(newPassword);
            editingUser.setAdmin(isAdmin);

            try {
                new UserDao().update(editingUser);
            } catch (NoSuchElementException e) {
                /*Here should be logging*/
            }
            System.out.println("Edited...");
            showMenu();
        }
    }

    /**
     * Performs delete user action.
     */
    class DeleteAction extends AbstractMenuAction {
        /**
         * Return key of action.
         * @return key.
         */
        @Override
        public int key() {
            return 3;
        }
        /**
         * Return name of action.
         * @return name.
         */
        @Override
        public String name() {
            return "Delete user";
        }

        /**
         * Requests user id. Try to delete user with that id.
         */
        @Override
        public void performAction() {
            User deletingUser = null;
            try {
                deletingUser = getUserById();
            } catch (Exception e) {
                System.out.println("Incorrect id for deleting user");
                showMenu();
                return;
            }

            new UserDao().delete(deletingUser);
            System.out.println("deleted....");
            showMenu();
        }
    }

}
