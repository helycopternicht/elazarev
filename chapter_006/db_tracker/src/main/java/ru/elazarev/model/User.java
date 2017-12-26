package ru.elazarev.model;

import java.time.LocalDateTime;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 20.12.17
 */
public class User {

    private int id;

    private String login;

    private String password;

    private LocalDateTime createDate;

    private boolean isAdmin;

    public User(int id, String login, String password, LocalDateTime createDate, boolean isAdmin) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createDate = createDate;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createDate=" + createDate +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
