package ru.elazarev.convert;

/**
 * User class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 19.05.17
 */
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
     * Default constructor.
     * @param id - user id
     * @param name - user name
     */
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * If the identifier is the same then the objects are the same.
     * @param o - object to check
     * @return - true or false.
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

        return this.id == user.id;
    }

    /**
     * Returns id.
     * @return id
     */
    @Override
    public int hashCode() {
        return id;
    }

    /**
     * toString method.
     * @return
     */
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }

    /**
     * Getter for id field.
     * @return - id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id field.
     * @param id - new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for name field.
     * @return - name of User
     */
    public String getName() {
        return name;
    }

    /**
     * Setter to name field.
     * @param name - new name
     */
    public void setName(String name) {
        this.name = name;
    }
}
