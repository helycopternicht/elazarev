package ru.elazarev.map;

import java.util.Calendar;

/**
 * User class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 06.06.17
 */
public class User implements Comparable<User> {
    /**
     * User name.
     */
    private String name;
    /**
     * Count of children.
     */
    private int children;
    /**
     * Date of birthday.
     */
    private Calendar birthday;

    /**
     * Default constructor.
     * @param name users name
     * @param children count of children
     * @param birthday date of birthday
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * Getter for name field.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name field.
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for children field.
     * @return children field
     */
    public int getChildren() {
        return children;
    }

    /**
     * Setter for children field.
     * @param children new count of children
     */
    public void setChildren(int children) {
        this.children = children;
    }

    /**
     * Getter for birthday field.
     * @return birthday field
     */
    public Calendar getBirthday() {
        return birthday;
    }

    /**
     * Setter for birthday field.
     * @param birthday new birthday
     */
    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    /**
     * Default toString.
     * @return string representation of user.
     */
    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday.getTime()
                + '}';
    }

    /**
     * Equals based on all field of user.
     * @param o other user
     * @return true of false
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
        if (getChildren() != user.getChildren()) {
            return false;
        }
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) {
            return false;
        }
        return getBirthday() != null ? getBirthday().equals(user.getBirthday()) : user.getBirthday() == null;
    }

    /**
     * HashCode base on all fields of user.
     * @return int
     */
    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getChildren();
        result = 31 * result + (getBirthday() != null ? getBirthday().hashCode() : 0);
        return result;
    }

    /**
     * Compares this user to other.
     * @param o other user to compare
     * @return int
     */
    @Override
    public int compareTo(User o) {
        return this.children - o.getChildren();
    }
}
