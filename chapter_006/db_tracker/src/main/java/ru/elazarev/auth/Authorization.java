package ru.elazarev.auth;

import ru.elazarev.model.User;
import ru.elazarev.model.dao.UserDao;
import ru.elazarev.model.exceptions.NoSuchElementException;

/**
 * Class for user authorisation.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 26.12.17
 */
public class Authorization {
    /**
     * Contains current session user.
     */
    private static User currentUser;

    /**
     * Trying to log in app.
     * @param login users login.
     * @param password users password.
     * @return true if login and password are correct.
     */
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

    /**
     * Returns user of current session.
     * @return user.
     */
    public static User getUser() {
        return currentUser;
    }
}
