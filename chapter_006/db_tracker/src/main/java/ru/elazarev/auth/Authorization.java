package ru.elazarev.auth;

import ru.elazarev.model.User;
import ru.elazarev.model.dao.UserDao;
import ru.elazarev.model.exceptions.NoSuchElementException;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 26.12.17
 */
public class Authorization {

    private static User currentUser;

    public static boolean loggedIn() {
        if (currentUser != null) {
            return true;
        }
        return false;
    }

    public static boolean login(String login, String password) {
        UserDao udao = new UserDao();
        try {
            User user = udao.getByLogin(login);
            if (password.equals(user.getPassword())) {
                currentUser = user;
                return true;
            }
        } catch (NoSuchElementException e) {
           return false;
        }
        return false;
    }

}
