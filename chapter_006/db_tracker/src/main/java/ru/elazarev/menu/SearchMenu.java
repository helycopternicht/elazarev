package ru.elazarev.menu;

import ru.elazarev.input.Input;
import ru.elazarev.model.UserRequest;
import ru.elazarev.model.dao.UserRequestDao;
import ru.elazarev.model.exceptions.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;

/**
 * Users search requst menu.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 26.12.17
 */
public class SearchMenu extends AbstractMenu {
    /**
     * User input object.
     */
    private Input input;
    /**
     * List of menu actions.
     */
    private List<MenuAction> actions;
    /**
     * Parent menu.
     */
    private Menu parentMenu;

    /**
     * Default constructor.
     * @param input input object.
     * @param parentMenu paren menu.
     */
    public SearchMenu(Input input, Menu parentMenu) {
        this.input = input;
        this.parentMenu = parentMenu;
        fillActions();
    }

    /**
     * Method fills actions of menu.
     */
    private void fillActions() {
        this.actions = new ArrayList<>();
        actions.add(new FindByIdAction());
        actions.add(new FindByTitle());
        actions.add(new ExitAction());
    }

    /**
     * Show menu to user.
     */
    @Override
    public void showMenu() {
        for (MenuAction ma : actions) {
            System.out.println(ma.info());
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
     * Returns input object of menu.
     * @return input object.
     */
    @Override
    public Input getInput() {
        return this.input;
    }

    /**
     * Class to find request by id.
     */
    class FindByIdAction extends AbstractMenuAction {
        /**
         * Returns key of cation.
         * @return key.
         */
        @Override
        public int key() {
            return 1;
        }

        /**
         * Returns name of action.
         * @return name.
         */
        @Override
        public String name() {
            return "Find request by id";
        }

        /**
         * Requests id of request and trying to find it.
         */
        @Override
        public void performAction() {
            String stId = input.ask("Enter id to open:");
            try {
                int id = Integer.valueOf(stId);
                UserRequest userRequest = new UserRequestDao().getById(id);
                new EditRequestMenu(userRequest, input, SearchMenu.this).showMenu();
            } catch (NumberFormatException e) {
                System.out.println("Id should be int");
                showMenu();
            } catch (NoSuchElementException e) {
                System.out.printf("Item with id = %s not found.", stId);
                showMenu();
            }
        }
    }

    /**
     * Class to find requests by title.
     */
    class FindByTitle extends AbstractMenuAction {
        /**
         * Returns key of cation.
         * @return key.
         */
        @Override
        public int key() {
            return 2;
        }
        /**
         * Returns name of action.
         * @return name.
         */
        @Override
        public String name() {
            return "Find request by name";
        }

        /**
         * Requests title of request ond find all match in bd. Shows all match.
         */
        @Override
        public void performAction() {
            String title = input.ask("Enter title to find:");
            List<UserRequest> userRequests = new UserRequestDao().getByTitle(title);
            System.out.printf("Founded requests: %s\n", userRequests.size());
            for (UserRequest r : userRequests) {
                System.out.println(r);
            }
            showMenu();
        }
    }

    /**
     * Perform exit to parent menu.
     */
    class ExitAction extends AbstractMenuAction {
        /**
         * Returns key of cation.
         * @return key.
         */
        @Override
        public int key() {
            return 0;
        }
        /**
         * Returns name of action.
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
}
