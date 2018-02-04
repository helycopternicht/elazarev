package ru.elazarev.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;

/**
 * User java bean. User of application.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 24.01.18
 */
@Entity
@Table(name = "users")
public class User {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;
    /**
     * Users login.
     */
    @Column(name = "login")
    private String login;
    /**
     * Users password.
     */
    @Column(name = "password")
    private String password;
    /**
     * Users email.
     */
    @Column(name = "email")
    private String email;
    /**
     * Is user admin.
     */
    @Column(name = "is_admin")
    private boolean isAdmin;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Parametrized constructor.
     * @param login login.
     * @param password password.
     * @param email email.
     * @param isAdmin is user admin.
     */
    public User(String login, String password, String email, boolean isAdmin) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    /**
     * Getter for id field.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id field.
     * @param id new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for login field.
     * @return login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Setter for login field.
     * @param login new login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Getter for password field.
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password field.
     * @param password new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for email field.
     * @return users email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email field.
     * @param email new email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for is admin field.
     * @return isAdmin.
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Setter for is admin field.
     * @param admin new admin.
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
