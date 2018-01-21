package ru.elazarev.model;

import java.time.LocalDateTime;

/**
 * User table model.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 20.12.17
 */
public class User {
    /**
     * Users id.
     */
    private int id;
    /**
     * Users login.
     */
    private String login;
    /**
     * Users password.
     */
    private String password;
    /**
     * Creation date.
     */
    private LocalDateTime createDate;
    /**
     * Is user admin or not.
     */
    private boolean isAdmin;

    /**
     * Default constructor.
     * @param id id.
     * @param login login.
     * @param password password.
     * @param createDate creation date.
     * @param isAdmin is admin or not.
     */
    public User(int id, String login, String password, LocalDateTime createDate, boolean isAdmin) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createDate = createDate;
        this.isAdmin = isAdmin;
    }

    /**
     * Getter.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter.
     * @param id new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter.
     * @return login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Setter.
     * @param login new login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Getter.
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter.
     * @param password new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter.
     * @return creation date.
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Setter.
     * @param createDate new creation date.
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Getter.
     * @return is admin.
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Setter.
     * @param admin is admin.
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /**
     * Default toString.
     * @return string representation of user model.
     */
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + ", createDate=" + createDate
                + ", isAdmin=" + isAdmin
                + '}';
    }
}
