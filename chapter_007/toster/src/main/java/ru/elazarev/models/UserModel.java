package ru.elazarev.models;

/**
 * Model for users.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 24.01.18
 */
public class UserModel {

    private int id;

    private String login;

    private String password;

    private String email;

    private boolean isAdmin;

    public UserModel(int id, String login, String password, String email, boolean isAdmin) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public UserModel(String login, String password, String email, boolean isAdmin) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
