package ru.elazarev.menu;

import ru.elazarev.auth.Authorization;
import ru.elazarev.input.Input;
import ru.elazarev.model.UserRequest;
import ru.elazarev.model.File;
import ru.elazarev.model.Comment;
import ru.elazarev.model.RequestStatus;
import ru.elazarev.model.RequestCategory;
import ru.elazarev.model.dao.CommentDao;
import ru.elazarev.model.dao.FileDao;
import ru.elazarev.model.dao.RequestCategoryDao;
import ru.elazarev.model.dao.UserRequestDao;
import ru.elazarev.model.exceptions.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;

/**
 * Console menu for editing user request.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 26.12.17
 */
public class EditRequestMenu extends AbstractMenu {
    /**
     * User request to edit.
     */
    private UserRequest request;
    /**
     * Input stream from user.
     */
    private Input input;
    /**
     * Menu actions.
     */
    private List<MenuAction> actions;
    /**
     * Parent menu.
     */
    private Menu parentMenu;

    /**
     * Default constructor.
     * @param request editing request.
     * @param input user input stream.
     * @param parentMenu parent menu.
     */
    public EditRequestMenu(UserRequest request, Input input, Menu parentMenu) {
        this.request = request;
        this.input = input;
        this.parentMenu = parentMenu;
        fillActions();
    }

    /**
     * Fills all menu actions.
     */
    private void fillActions() {
        this.actions = new ArrayList<>();
        actions.add(new ExitAction());
        actions.add(new EditRequest());
        actions.add(new DeleteRequest());
        actions.add(new AddComment());
        actions.add(new AddFile());
    }

    /**
     * Method show menu to user.
     */
    @Override
    public void showMenu() {
        showRequestDetails();
        for (MenuAction action : this.actions) {
            System.out.println(action.info());
        }
        selectAction();
    }

    /**
     * Returns all menu actions.
     * @return list of a=menu actions.
     */
    @Override
    public List<MenuAction> getMenuActions() {
        return this.actions;
    }

    /**
     * Return user input.
     * @return user input.
     */
    @Override
    public Input getInput() {
        return this.input;
    }

    /**
     * Shows request details to user.
     */
    public void showRequestDetails() {
        System.out.printf("EDIT REQUEST #%d from %s\n", request.getId(), request.getDate().toString());
        System.out.printf("Author: %s\n", request.getAuthor().getLogin());
        System.out.printf("Title: %s\n", request.getTitle());
        System.out.printf("Description: %s\n", request.getDescription());
        System.out.printf("Status: %s\n", request.getStatus().toString());
        System.out.printf("Category: %s\n", request.getCategory().getName());

        System.out.println("FILES:");
        for (File f : request.getFiles()) {
            System.out.println("-------------");
            System.out.println(f.getUrl());
        }

        System.out.println("COMMENTS:");
        for (Comment c : request.getComments()) {
            System.out.println("-------------");
            System.out.printf("Date: %s\n", c.getCreateDate().toString());
            System.out.printf("Author: %s\n", c.getAuthor().getLogin());
            System.out.printf("Text: %s\n", c.getComment());
        }
    }

    /**
     * Class for perform exit action.
     */
    class ExitAction extends AbstractMenuAction {
        /**
         * Return key of this action.
         * @return key.
         */
        @Override
        public int key() {
            return 0;
        }

        @Override
        public String name() {
            return "Exit";
        }

        /**
         * Shows main menu to user.
         */
        @Override
        public void performAction() {
            parentMenu.showMenu();
        }
    }

    /**
     * Class to perform edit of request.
     */
    class EditRequest extends AbstractMenuAction {
        /**
         * Return key of this action.
         * @return key.
         */
        @Override
        public int key() {
            return 1;
        }

        /**
         * Returns name of this action.
         * @return name.
         */
        @Override
        public String name() {
            return "Edit request fields";
        }

        /**
         * Shows edit request form and edit request in bd.
         */
        @Override
        public void performAction() {
            System.out.printf("EDIT REQUEST #%d from %s\n", request.getId(), request.getDate().toString());
            System.out.println("Enter new fields. Set empty to save old value");
            String title = input.ask("Title: ");
            String desc = input.ask("Description: ");
            String category = input.ask("Category: ");

            String status = input.ask("Status.: (c = create, p = in progress, any other letter = done): ");
            RequestStatus st = RequestStatus.DONE;
            if (status.equals("c")) {
                st = RequestStatus.CREATED;
            } else if (status.equals("p")) {
                st = RequestStatus.IN_PROGRESS;
            }

            if (title.length() > 0) {
                request.setTitle(title);
            }

            if (desc.length() > 0) {
                request.setDescription(desc);
            }

            if (category.length() > 0) {
                RequestCategoryDao dao = new RequestCategoryDao();
                RequestCategory requestCategory = null;
                try {
                    requestCategory = dao.getByName(category);
                } catch (NoSuchElementException e) {
                    requestCategory = dao.create(new RequestCategory(-1, category));
                }
                request.setCategory(requestCategory);
            }

            if (request.getStatus() != st) {
                request.setStatus(st);
            }

            UserRequestDao urdao = new UserRequestDao();
            try {
                urdao.update(request);
            } catch (NoSuchElementException e) {
                /*logging should be here*/
            }

            System.out.println("updated...");
            showMenu();
        }
    }

    /**
     * Class to perform request delete.
     */
    class DeleteRequest extends AbstractMenuAction {
        /**
         * Return key of this action.
         * @return key.
         */
        @Override
        public int key() {
            return 2;
        }
        /**
         * Returns name of this action.
         * @return name.
         */
        @Override
        public String name() {
            return "Delete request";
        }

        /**
         * Deletes request in bd.
         */
        @Override
        public void performAction() {

            System.out.println("DELETING REQUEST");
            UserRequestDao urdao = new UserRequestDao();
            try {
                urdao.delete(request);
            } catch (NoSuchElementException e) {
                // there should be logging
            }
            System.out.println("deleted...");
            parentMenu.showMenu();
        }
    }

    /**
     * Class to perform comment adding.
     */
    class AddComment extends AbstractMenuAction {
        /**
         * Return key of this action.
         * @return key.
         */
        @Override
        public int key() {
            return 3;
        }
        /**
         * Returns name of this action.
         * @return name.
         */
        @Override
        public String name() {
            return "Add comment";
        }

        /**
         * Adds comment to request.
         */
        @Override
        public void performAction() {
            System.out.println("ADDING COMMENT");
            String text = input.ask("Enter comment text: ");
            Comment comment = new Comment(-1, Authorization.getUser(), text, null, request.getId());

            CommentDao dao = new CommentDao();
            request.addComment(dao.create(comment));
            System.out.println("created...");
            showMenu();
        }
    }

    /**
     * Class to perform file adding.
     */
    class AddFile extends AbstractMenuAction {
        /**
         * Return key of this action.
         * @return key.
         */
        @Override
        public int key() {
            return 4;
        }
        /**
         * Returns name of this action.
         * @return name.
         */
        @Override
        public String name() {
            return "Add file";
        }

        /**
         * Adds file to request.
         */
        @Override
        public void performAction() {
            System.out.println("ADDING FILE");
            String text = input.ask("Enter file url: ");
            File file = new File(-1, text);
            FileDao dao = new FileDao();
            request.addFile(dao.create(file));
            System.out.println("created...");
            showMenu();
        }
    }

}
