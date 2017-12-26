package ru.elazarev.menu;

import ru.elazarev.input.Input;
import ru.elazarev.model.Comment;
import ru.elazarev.model.File;
import ru.elazarev.model.UserRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 26.12.17
 */
public class EditRequestMenu {

    private UserRequest request;

    private Input input;

    private List<MenuAction> actions;

    public EditRequestMenu(UserRequest request, Input input) {
        this.request = request;
        this.input = input;
        fillActions();
    }

    private void fillActions() {
        this.actions = new ArrayList<>();
        actions.add(new ExitAction());
        actions.add(new EditRequest());
        actions.add(new DeleteRequest());
        actions.add(new AddComment());
        actions.add(new AddFile());
    }

    public void showMenu() {
        showRequestDetails();
        for (MenuAction action : this.actions) {
            System.out.println(action.info());
        }
    }

    public void showRequestDetails() {
        System.out.printf("EDIT REQUEST #%d from %s\n", request.getId(), request.getDate().toString());
        System.out.printf("Author: %s\n", request.getAuthor().getLogin());
        System.out.printf("Title: %s\n", request.getTitle());
        System.out.printf("Description: %s\n", request.getDescription());
        System.out.printf("Status: %s\n", request.getStatus().toString());
        System.out.printf("Category: %s\n", request.getCategory().getName());

        System.out.println("FILES:");
        for (File f : request.getFiles()) {
            System.out.println(f.getUrl());
        }

        System.out.println("COMMENTS:");
        for (Comment c : request.getComments()) {
            System.out.println("-------------");
            System.out.printf("Author: %s\n", c.getAuthor().getLogin());
            System.out.printf("Text: %s\n", c.getAuthor().getLogin());
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
        }
    }

    class EditRequest extends AbstractMenuAction {

        @Override
        public int key() {
            return 1;
        }

        @Override
        public String name() {
            return "Edit request fields";
        }

        @Override
        public void performAction() {
        }
    }

    class DeleteRequest extends AbstractMenuAction {

        @Override
        public int key() {
            return 1;
        }

        @Override
        public String name() {
            return "Edit request fields";
        }

        @Override
        public void performAction() {
        }
    }

    class AddComment extends AbstractMenuAction {

        @Override
        public int key() {
            return 1;
        }

        @Override
        public String name() {
            return "Edit request fields";
        }

        @Override
        public void performAction() {
        }
    }

    class AddFile extends AbstractMenuAction {

        @Override
        public int key() {
            return 1;
        }

        @Override
        public String name() {
            return "Edit request fields";
        }

        @Override
        public void performAction() {
        }
    }

}
