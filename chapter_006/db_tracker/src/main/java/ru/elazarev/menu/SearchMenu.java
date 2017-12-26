package ru.elazarev.menu;

import ru.elazarev.input.Input;
import ru.elazarev.model.UserRequest;
import ru.elazarev.model.dao.UserRequestDao;
import ru.elazarev.model.exceptions.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 26.12.17
 */
public class SearchMenu {

    private Input input;

    private MainMenu mainMenu;

    private List<MenuAction> actions;

    public SearchMenu(Input input, MainMenu menu) {
        this.input = input;
        this.mainMenu = menu;
    }

    private void fillActions() {
        this.actions = new ArrayList<>();
        actions.add(null);
    }

    class FindByIdAction extends AbstractMenuAction {

        @Override
        public int key() {
            return 1;
        }

        @Override
        public String name() {
            return "Find request by id";
        }

        @Override
        public void performAction() {
            String stId = input.ask("Enter id to find:");
            try {
                int id = Integer.valueOf(stId);
                UserRequest userRequest = new UserRequestDao().getById(id);

            } catch (NumberFormatException e) {
                System.out.println("Id should by int");
            } catch (NoSuchElementException e) {
                System.out.printf("Item with id = %d not found.", stId);
            }
        }
    }
}
