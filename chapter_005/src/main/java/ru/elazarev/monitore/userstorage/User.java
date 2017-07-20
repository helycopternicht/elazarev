package ru.elazarev.monitore.userstorage;

import net.jcip.annotations.ThreadSafe;

/**
 * User class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 19.07.17
 */
@ThreadSafe
public class User {
    /**
     * User id.
     */
    private int id;
    /**
     * User name.
     */
    private String name;

    /**
     * Users money.
     */
    private double money;

    /**
     * Default constructor.
     * @param id users id
     * @param name users name
     * @param money users money
     */
    public User(int id, String name, double money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    /**
     * Getter for money field.
     * @return money
     */
    public synchronized double getMoney() {
        return this.money;
    }

    /**
     * Setter for money field.
     * @param money new user money
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * Getter for id field.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Default equals based on id.
     * @param o object to test.
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return getId() == user.getId();
    }

    /**
     * Hashcode based on users id.
     * @return int
     */
    @Override
    public int hashCode() {
        return getId();
    }

    /**
     * Default toString.
     * @return String
     */
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", money=" + money
                + '}';
    }
}
